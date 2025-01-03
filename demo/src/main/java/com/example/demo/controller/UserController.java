package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserServiceInterface;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    //private static Logger log = LoggerFactory.getLogger(UserController.class); //use @Slf4j

    // @GetMapping
    // public List<User> getAllUsers() {
    //     return userService.getAllUsers();
    // }

    // @GetMapping("/{username}")
    // public User getUserByUsername(@PathVariable String username) {
    //     return userService.getUserByUsername(username);
    // }

    // // @GetMapping
    // // public ResponseEntity<User> getUserInfo(@RequestParam String username) {
    // //     User user = userService.getUserByUsername(username);
    // //     if (user == null) {
    // //         return ResponseEntity.status(404).body(null);  // 如果找不到用户，返回 404
    // //     }
    // //     return ResponseEntity.ok(user);  // 返回 200 和用户信息
    // // }

    // @PostMapping
    // public void createUser(@RequestBody User user) {
    //     userService.insertUser(user);
    // }


    /*
     *   GET /users：获取所有用户
     *   GET /users/{id}：根据 ID 获取用户
     *   GET /users/username/{username}：根据用户名获取用户
     *   POST /users：创建新用户
     *   PUT /users/{id}：更新用户信息
     *   DELETE /users/{id}：删除用户
     */
    // 获取所有用户
    @GetMapping
    public List<User> getAllUsers() {
        log.info("Get all users");
        return userService.getAllUsers();
    }

    // 根据 ID 获取用户
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // 根据用户名获取用户
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
    // // 处理 GET 请求：/users?username={username}
    // @GetMapping
    // public User getUserByUsername(@RequestParam String username) {
    //     return userService.getUserByUsername(username);
    // }

    // 创建新用户
    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    // 更新用户信息
    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
