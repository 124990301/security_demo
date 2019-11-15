package com.example;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="user")
public class UserEntity implements java.io.Serializable{
    @Id
    @Column
    private Long id;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String role;
    // 省略get set 等
}