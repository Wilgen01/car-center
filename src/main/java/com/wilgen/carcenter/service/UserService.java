package com.wilgen.carcenter.service;

import com.wilgen.carcenter.model.User;

public interface UserService {

    User findByEmail(String email);
    String save(User user) throws Exception;
}
