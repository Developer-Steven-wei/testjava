package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.demo.model.User;
import com.example.demo.service.impl.UserService;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserService userService;

    @Test
    public void testDatabaseConnection() {
        // 尝试执行一条简单的查询语句
		System.out.println("Trying to connect to the database...");
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        System.out.println("connected to the database successfully. Result: " + result);
    }	

	@Test
	void contextLoads() {
		System.out.println("Hello World");
		//this.testDatabaseConnection();
		// List<User> users = this.getAllUsers();
		// System.out.println(users);
		// System.out.println("Hello World");
		User user = this.getUserName("steven");
		System.out.println(user);
	}

	public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

	public User getUserName(String username) {
        return userService.getUserByUsername(username);
    }
}
