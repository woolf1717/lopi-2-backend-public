package com.liftoff.project.service.impl;


import com.liftoff.project.mapper.UserDetailsSecurityMapper;
import com.liftoff.project.model.User;
import com.liftoff.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;
    private final UserDetailsSecurityMapper userDetailsMapper;


    @Override
    public UserDetails loadUserByUsername(String username) {


        Optional<User> myOptionalUser = userRepository.findByUsername(username);

        return myOptionalUser.map(this.userDetailsMapper::mapUserToUserSecurityDetails).orElseThrow(() ->
             new UsernameNotFoundException("Authenticated User not found")
        );


    }


}
