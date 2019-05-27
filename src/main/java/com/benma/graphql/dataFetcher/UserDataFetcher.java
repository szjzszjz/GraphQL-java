package com.benma.graphql.dataFetcher;

import com.benma.graphql.Utils.EnumUtil;
import com.benma.graphql.Utils.ToEntityUtil;
import com.benma.graphql.entity.User;
import com.benma.graphql.enums.GenderEnum;
import com.benma.graphql.enums.ResultEnum;
import com.benma.graphql.service.UserService;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * author:szjz
 * date:2019/5/22
 */

@Component
@Slf4j
public class UserDataFetcher {

    @Autowired
    private UserService userService;

    /**
     * 平台用户注册
     */
    public DataFetcher save() {
        return dataFetchingEvn -> {
            LinkedHashMap linkedHashMap = dataFetchingEvn.getArgument("user");
            String username = (String) linkedHashMap.get("username");
            if (userService.findByUsername(username) == null) {

                String gender = (String) linkedHashMap.get("gender");
                GenderEnum genderEnum = EnumUtil.getByMsg(gender, GenderEnum.class);
                linkedHashMap.put("gender", genderEnum.getCode().intValue());
                User user = (User) ToEntityUtil.entity(new User(), linkedHashMap);

                User responseUser = userService.save(user);
                if (responseUser == null) {
                    log.error("【用户注册】 {}", ResultEnum.REGISTER_FAIL.getMessage());
                    return ResultEnum.REGISTER_FAIL.getMessage();
                }
                return ResultEnum.REGISTER_SUCCESS.getMessage();
            }
            log.error("【用户注册】 {}", ResultEnum.USER_EXIST.getMessage());
            return ResultEnum.USER_EXIST.getMessage();
        };
    }


    /**
     * 平台用户登录
     */
    public DataFetcher login() {
        return dataFetchingEvn -> {
            String username = dataFetchingEvn.getArgument("username");
            String password = dataFetchingEvn.getArgument("password");
            User user = userService.findByUsernameAndPassword(username, password);
            if (user == null) {
                log.error("【用户登录】 username={},password={},{} ", username, password, ResultEnum.LOGIN_ERROR_USER_NOT_EXIST.getMessage());
                return ResultEnum.LOGIN_ERROR_USER_NOT_EXIST.getMessage();
            }
            return ResultEnum.LOGIN_SUCCESS.getMessage();
        };
    }


    /** 平台用户退出 */

    /**
     * 根据id查询
     */
    public DataFetcher userById() {
        return dataFetchingEnvironment -> {
            String userId = dataFetchingEnvironment.getArgument("id");
            User user = userService.findById(Integer.valueOf(userId));
            return user;
        };
    }

    /**
     * 根据性别查询
     */
    public DataFetcher userByGender() {
        return dataFetchingEnvironment -> {
            String gender = dataFetchingEnvironment.getArgument("gender");
            GenderEnum genderEnum = EnumUtil.getByMsg(gender, GenderEnum.class);
            List userList = userService.userByGender(genderEnum.getCode().intValue());
            return userList;
        };
    }
}
