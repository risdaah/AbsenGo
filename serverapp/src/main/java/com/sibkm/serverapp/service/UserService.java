package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.DetailAbsensi;
import com.sibkm.serverapp.entity.Role;
import com.sibkm.serverapp.entity.User;
import com.sibkm.serverapp.repository.DetailAbsensiRepository;
import com.sibkm.serverapp.repository.RoleRepository;
import com.sibkm.serverapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DetailAbsensiRepository detailAbsensiRepository;

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User saveUser(User user, Set<Long> roleIds) {

        if (roleIds == null || roleIds.isEmpty()) {
            throw new RuntimeException("Role IDs cannot be null or empty");
        }

        Set<Role> role = roleRepository.findAllById(roleIds).stream().collect(Collectors.toSet());
        if (role.isEmpty()) {
            throw new RuntimeException("No valid roles found for the provided IDs");
        }

        user.setRole(role);

        return userRepository.save(user);
    }

    @Transactional
    public User getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public List<DetailAbsensi> getAbsensiRekap(Long userId) {
        return detailAbsensiRepository.findByUserId(userId);
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Long findIdByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username); // Ganti ke findByUsername
        return user.map(User::getId_user).orElse(null); // Gunakan map untuk menghindari NullPointerException
    }

}
