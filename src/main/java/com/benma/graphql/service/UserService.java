package com.benma.graphql.service;

import com.benma.graphql.entity.User;

/**
 * author:szjz
 * date:2019/5/23
 */
public interface UserService {

    User save(User user);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
