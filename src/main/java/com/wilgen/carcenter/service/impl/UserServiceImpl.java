package com.wilgen.carcenter.service.impl;

import com.wilgen.carcenter.model.User;
import com.wilgen.carcenter.repository.UserRepository;
import com.wilgen.carcenter.security.jwt.JwtUtils;
import com.wilgen.carcenter.service.UserService;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("El usuario " + email + " no existe."));
    }

    @Override
    public String save(User user) throws Exception {

        if (userRepository.existsByEmail(user.getEmail())){
            throw new EntityExistsException("The email is already taken");
        }

        try {
            User userCreated = userRepository.save(user);
            return jwtUtils.generateAccesToken(userCreated.getEmail());
        }catch (Exception e){
            throw new Exception("Unexpected error while creating user");
        }

    }

}
