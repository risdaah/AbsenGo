package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.DTO.UserDTO;
import com.sibkm.serverapp.entity.*;
import com.sibkm.serverapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(user -> new UserDTO(
                        user.getId_user(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getEmail(),
                        user.getRole().stream().map(Role::getId_role).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDTO userDTO = new UserDTO(
                    user.getId_user(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getRole().stream().map(Role::getId_role).collect(Collectors.toSet()));
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getRoleIds() == null || userDTO.getRoleIds().isEmpty()) {
            throw new RuntimeException("Role IDs cannot be null or empty");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        User savedUser = userService.saveUser(user, userDTO.getRoleIds());

        UserDTO savedUserDTO = new UserDTO(
                savedUser.getId_user(),
                savedUser.getUsername(),
                savedUser.getPassword(),
                savedUser.getEmail(),
                savedUser.getRole().stream().map(role -> role.getId_role()).collect(Collectors.toSet()));

        return ResponseEntity.ok(savedUserDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());

            if (userDTO.getRoleIds() != null && !userDTO.getRoleIds().isEmpty()) {
                Set<Long> roleIds = userDTO.getRoleIds();
                user.setRole(userService.saveUser(user, roleIds).getRole());
            }

            User updatedUser = userService.updateUser(id, user);

            UserDTO updatedUserDTO = new UserDTO(
                    updatedUser.getId_user(),
                    updatedUser.getUsername(),
                    updatedUser.getPassword(),
                    updatedUser.getEmail(),
                    updatedUser.getRole().stream().map(role -> role.getId_role()).collect(Collectors.toSet()));

            return ResponseEntity.ok(updatedUserDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasRole('ROLE_PESERTA')")
    public User getUserProfile(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }

    @GetMapping("/absensi/{userId}")
    @PreAuthorize("hasRole('ROLE_PESERTA')")
    public List<DetailAbsensi> getAbsensiRekap(@PathVariable Long userId) {
        return userService.getAbsensiRekap(userId);
    }

    @GetMapping("/id-by-username")
    public ResponseEntity<Long> getIdByUsername(@RequestParam String username) {
        Long userId = userService.findIdByUsername(username);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        }
        return ResponseEntity.notFound().build(); // Kembalikan 404 jika user tidak ditemukan
    }
}