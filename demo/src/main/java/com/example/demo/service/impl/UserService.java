package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserMapper userMapper;

    // 获取所有用户
    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    // 根据 ID 获取用户
    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    // 根据用户名获取用户
    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    // 插入新用户
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    // 更新用户信息
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    // 删除用户
    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
}
