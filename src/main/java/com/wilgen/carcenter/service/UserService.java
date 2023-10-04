package com.wilgen.carcenter.service;

import com.wilgen.carcenter.model.User;

public interface UserService {

    User findByEmail(String email);
    User save(User user);
}
