package org.example.testspringcsdl.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.example.testspringcsdl.entity.Role;
import org.example.testspringcsdl.entity.User;
import org.example.testspringcsdl.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassWord(), mapRolesToAuthorities(user.getRole()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Role role) {
        List<String> rolePermissions = new ArrayList<>();
        rolePermissions.add("ROLE_" + role.getRoleName());

        if (role.getPermissions() != null) {
            role.getPermissions().stream()
                    .map(permission -> permission.getPermissionName())
                    .forEach(rolePermissions::add);
        }

        return rolePermissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
