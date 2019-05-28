package com.benma.graphql.enums;

import lombok.Getter;

/**
 * @author szjz
 * @date 2019/5/8 17:46
 */

@Getter
public enum ResultEnum {
    /** shift + ctrl + u 大小写相互转换*/

    REGISTER_FAIL(120,"注册失败"),
    REGISTER_SUCCESS(121,"注册成功"),
    USER_EXIST(122,"用户已存在"),
    USER_IS_NOT_EXIST(123,"用户不存在"),

    LOGIN_ERROR_USER_NOT_EXIST(130,"账号或密码错误"),
    LOGIN_SUCCESS(133,"登录成功"),
    LOGOUT_SUCCESS(131,"退出成功"),
    LOGOUT(132,"已经退出"),

    AUTHORIZE_FAIL(140,"认证失败，请重新登录！"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
