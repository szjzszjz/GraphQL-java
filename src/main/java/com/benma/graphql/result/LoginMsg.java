package com.benma.graphql.result;

import lombok.Data;

/**
 * author:szjz
 * date:2019/5/28
 */

@Data
public class LoginMsg {

    /** 登录返回信息 */
    private String message;

    /** token */
    private String token;

    public LoginMsg(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
