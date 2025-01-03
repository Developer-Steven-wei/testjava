package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String postCode; //新增属性post_code 开启mybatis自动映射驼峰命名规则到下划线命名规则

    // 使用lombok的同时手动添加的构造方法，仅包含 username 和 email 参数
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, String postCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.postCode = postCode;
    }

    //使用lombok注解
    // @Data注解会生成get/set方法，toString方法，equals方法，hashCode方法
    // @AllArgsConstructor注解会生成全参数构造方法
    // @NoArgsConstructor注解会生成无参构造方法
    // @Builder注解会生成Builder模式的构造方法
    // @Getter注解会生成get方法
    // @Setter注解会生成set方法
    // @ToString注解会生成toString方法
    // @EqualsAndHashCode注解会生成equals方法和hashCode方法
    // public User() {

    // }

    // public User(String username, String password, String email) {
    //     this.username = username;
    //     this.password = password;
    //     this.email = email;
    // }

    // public User(Integer id, String username, String password, String email) {
    //     this.id = id;
    //     this.username = username;
    //     this.password = password;
    //     this.email = email;
    // }

    // public Integer getId() {
    //     return id;
    // }

    // public void setId(Integer id) {
    //     this.id = id;
    // }

    // public String getUsername() {
    //     return username;
    // }

    // public void setUsername(String username) {
    //     this.username = username;
    // }

    // public String getPassword() {
    //     return password;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // @Override
    // public String toString() {
    //     return "User{" +
    //             "id=" + id +                
    //             ", username='" + username + '\'' +
    //             ", password='" + password + '\'' +
    //             ", email='" + email + '\'' +
    //             '}';
    // }
    
}
