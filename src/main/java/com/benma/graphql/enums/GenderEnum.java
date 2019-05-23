package com.benma.graphql.enums;

import lombok.Getter;

/**
 * author:szjz
 * date:2019/5/23
 * 用户性别枚举
 */

@Getter
public enum GenderEnum implements CodeEnum{
    UNKNOWN(0,"未知"),
    MAN(1,"男"),
    WOMAN(2,"女")
    ;

    private Integer code;

    private String msg;

    GenderEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
