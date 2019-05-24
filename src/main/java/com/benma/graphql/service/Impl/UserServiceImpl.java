package com.benma.graphql.service.Impl;

import com.benma.graphql.entity.User;
import com.benma.graphql.repository.UserRepository;
import com.benma.graphql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:szjz
 * date:2019/5/23
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> userByGender(int gender) {
        return userRepository.findByGender(gender);
    }
}
