package com.latihan.service;

import com.latihan.entity.User;
import com.latihan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Email or password not found");
        }

        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                authorities()
        );
    }


    public Collection<? extends GrantedAuthority> authorities(){
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(app.name);
//        return Collection.singletonList(athority);
        return List.of(new SimpleGrantedAuthority("USER"));
    }
}
