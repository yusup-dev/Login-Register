package com.latihan.service;

import com.latihan.dto.UserDto;
import com.latihan.entity.User;
import com.latihan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getFullName(), userDto.getNpm(), userDto.getUniversity());
        return userRepository.save(user);
    }

    @Override
    public User getUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public User save(User user) {
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public boolean deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }
}
