package com.plsnogod.jmboot.service.Impl;

import com.plsnogod.jmboot.dao.UserRepository;
import com.plsnogod.jmboot.model.Role;
import com.plsnogod.jmboot.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserServiceImpl userService;
    private  UserRepository userRepository;

    public UserDetailsServiceImpl(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.getUserByName(name);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRolesSet()
        ) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getNameRoles()));

        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
