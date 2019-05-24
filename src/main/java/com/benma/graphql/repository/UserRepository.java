package com.benma.graphql.repository;

import com.benma.graphql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author:szjz
 * date:2019/5/23
 */
public interface UserRepository extends JpaRepository<User, Integer> {


    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    List<User> findByGender(int gender);
}

