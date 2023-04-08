package com.example.jwtWithDatabase.service;

import com.example.jwtWithDatabase.dao.UserRepository;
import com.example.jwtWithDatabase.modal.DaoUser;
import com.example.jwtWithDatabase.modal.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DaoUser daoUser = userRepository.findByUsername(username);
//        if ("javainuse".equals(username)) {
//            return new User("javainuse",
//                    "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
//        }
        if (daoUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(daoUser.getUsername(), daoUser.getPassword(), new ArrayList<>());
    }

    public DaoUser saveUser(UserDto user) {
        DaoUser daoUser = new DaoUser();
        daoUser.setUsername(user.getUsername());
        daoUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(daoUser);
    }
}
