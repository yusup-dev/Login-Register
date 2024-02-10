package com.latihan.service;

import com.latihan.dto.UserDto;
import com.latihan.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    User findByUsername(String username);

    User save(UserDto userDto);

    public User getUserId(int id);

    public User save(User user);
}
