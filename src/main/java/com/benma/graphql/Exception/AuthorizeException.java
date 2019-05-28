package com.benma.graphql.Exception;

import com.benma.graphql.enums.ResultEnum;

/**
 * author:szjz
 * date:2019/5/28
 *
 * 授权认证异常
 */
public class AuthorizeException extends RuntimeException{
    public Integer code;

    public AuthorizeException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());//将message传到父类的构造方法中
        this.code = resultEnum.getCode();
    }

    public AuthorizeException(Integer code, String msg) {
        super(msg);//将message传到父类的构造方法中
        this.code = code;
    }
}
