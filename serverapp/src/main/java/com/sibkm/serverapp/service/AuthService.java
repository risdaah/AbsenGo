package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.Bidang;
import com.sibkm.serverapp.entity.DetailMbkm;
import com.sibkm.serverapp.entity.DetailUser;
import com.sibkm.serverapp.entity.Role;
import com.sibkm.serverapp.entity.User;
import com.sibkm.serverapp.model.request.LoginRequest;
import com.sibkm.serverapp.model.request.RegistrationRequest;
import com.sibkm.serverapp.model.response.LoginResponse;
import com.sibkm.serverapp.repository.BidangRepository;
import com.sibkm.serverapp.repository.DetailMbkmRepository;
import com.sibkm.serverapp.repository.DetailUserRepository;
import com.sibkm.serverapp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final DetailUserRepository detailUserRepository;
    private final DetailMbkmRepository detailMbkmRepository;
    private final BidangRepository bidangRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final AppUserDetailService appUserDetailService;
    private final SecurityContextHolderStrategy securityContextHolderStrategy;
    private final SecurityContextRepository securityContextRepository;
    private final RoleService roleService;

    public User registration(RegistrationRequest registrationRequest) {

        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        DetailUser detailUser = new DetailUser();
        DetailMbkm detailMbkm = new DetailMbkm();
        Bidang bidang = new Bidang();

        BeanUtils.copyProperties(registrationRequest, user);
        BeanUtils.copyProperties(registrationRequest, detailUser);
        BeanUtils.copyProperties(registrationRequest, detailMbkm);
        BeanUtils.copyProperties(registrationRequest, bidang);

        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        bidangRepository.save(bidang);

        detailMbkm.setBidang(bidang);
        detailMbkmRepository.save(detailMbkm);

        detailUser.setDetailMbkm(detailMbkm);
        detailUserRepository.save(detailUser);

        Role pesertaRole = roleService.getByRole("Peserta")
                .orElseThrow(() -> new IllegalArgumentException("Default role 'Peserta' not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(pesertaRole);
        user.setRole(roles);

        user.setDetailUser(detailUser);

        return userRepository.save(user);
    }

    public LoginResponse login(
            LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        Authentication auth = authManager.authenticate(authReq);

        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(auth);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        UserDetails userDetails = appUserDetailService.loadUserByUsername(loginRequest.getUsername());

        User user = userRepository
                .findByUsernameOrEmail(loginRequest.getUsername(), loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> authorities = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(userDetails.getUsername());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setAuthorities(authorities);

        return loginResponse;
    }

    public User addRole(Long userId, Role role) {
        return updateUserRoles(userId, Collections.singleton(role), false);
    }

    public User updateRole(Long userId, Long roleId) {
        Role role = roleService.getById(roleId);
        return updateUserRoles(userId, Collections.singleton(role), true);
    }

    private User updateUserRoles(Long userId, Set<Role> newRoles, boolean replaceExisting) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> roles = user.getRole();
        if (roles == null) {
            roles = new HashSet<>();
        }

        if (replaceExisting) {
            roles.clear();
        }

        roles.addAll(newRoles);
        user.setRole(roles);

        return userRepository.save(user);
    }
}
