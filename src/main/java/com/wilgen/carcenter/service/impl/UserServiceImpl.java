package com.wilgen.carcenter.service.impl;

import com.wilgen.carcenter.model.User;
import com.wilgen.carcenter.repository.UserRepository;
import com.wilgen.carcenter.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("El usuario " + email + " no existe."));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
