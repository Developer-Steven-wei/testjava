package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

@Mapper
public interface UserMapper {

    // 查询所有用户
    // @Select("SELECT * FROM users")
    // List<User> getAllUsers();

    // 根据用户名查询用户
    // @Select("SELECT * FROM users WHERE username = #{username}")
    // @Select("SELECT * FROM users WHERE username like  concat('%', #{username}, '%')")
    // User getUserByUsername(String username);

    // 插入用户  
    //@Options(useGeneratedKeys = true, keyProperty = "id") // 获取自增的主键值
    // @Insert("INSERT INTO users(username, password, email) VALUES(#{username}, #{password}, #{email})")
    // void insertUser(User user);

    //使用注解的方式，可以省去xml文件，但是需要在启动类上添加@Mapper注解
    // 查询所有用户
    List<User> getAllUsers();

    // 根据 ID 查询用户
    User getUserById(int id);

    // 根据用户名查询用户 //单独使用mybatis时需要使用param
    User getUserByUsername(@Param("username") String username);

    /**
     * 插入用户
     * @param user
     */
    void insertUser(User user);

    // 更新用户信息
    /**
     * 
     * @param user
     */
    void updateUser(User user);

    // 删除用户
    void deleteUser(int id);
}
