package com.latihan.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @Column(name = "username_user")
    private String username;

    @Column(name = "password_user")
    private String password;

    @Column(name = "full_name_user")
    private String fullName;

    @Column(name = "npm_user")
    private String npm;

    @Column(name = "university_user")
    private String university;

    public User(){

    }

    public User(String username, String password, String fullName, String npm, String university) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.npm = npm;
        this.university = university;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
