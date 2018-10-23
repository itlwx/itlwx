package com.itlwx.core.service;

import com.itlwx.core.bo.UserBO;

public interface UserService {
    void addUser();
    void deleteUser();
    void updateUser();
    UserBO verifyUser(String username, String password);
}
