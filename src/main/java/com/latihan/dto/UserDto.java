package com.latihan.dto;

import jakarta.persistence.Column;

public class UserDto {

    private String username;
    private String password;
    private String fullName;

    private String npm;

    private String university;

    public UserDto(){
    }

    public UserDto(String username, String password, String fullName, String npm, String university) {
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
