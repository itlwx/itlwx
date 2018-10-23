package com.itlwx.core.service.impl;

import com.itlwx.common.utils.MapperUtil;
import com.itlwx.core.bean.User;
import com.itlwx.core.bean.UserExample;
import com.itlwx.core.bo.UserBO;
import com.itlwx.core.mapper.UserMapper;
import com.itlwx.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public UserBO verifyUser(String username, String password) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andLoginNameEqualTo(username)
                .andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return MapperUtil.map(users.get(0),UserBO.class);
        }
        return null;
    }
}
