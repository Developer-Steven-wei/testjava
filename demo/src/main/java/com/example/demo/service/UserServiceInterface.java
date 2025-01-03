package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;


public interface UserServiceInterface {
    // 获取所有用户
    public List<User> getAllUsers();

    // 根据 ID 获取用户
    public User getUserById(int id);

    // 根据用户名获取用户
    public User getUserByUsername(String username);

    // 插入新用户
    public void insertUser(User user);

    // 更新用户信息
    public void updateUser(User user);

    // 删除用户
    public void deleteUser(int id);
}
