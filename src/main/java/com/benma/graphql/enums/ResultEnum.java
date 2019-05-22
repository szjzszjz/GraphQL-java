package com.benma.graphql.enums;

import lombok.Getter;

/**
 * @author szjz
 * @date 2019/5/8 17:46
 */

@Getter
public enum ResultEnum {
    /** shift + ctrl + u 大小写相互转换*/
    PRODUCT_NOT_EXIST(0 ,"商品不存在"),
    PRODUCT_STOCK_ERROR(1, "商品库存不足"),
    PRODUCT_STATUS_ERROR(2, "商品状态异常"),

    ORDER_NOT_EXIST(10,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(11,"订单详情不存在"),
    ORDER_STATUS_ERROR(12,"订单状态异常"),
    ORDER_UPDATE_FAIL(13,"订单更新失败"),
    ORDER_PAY_STATUS_ERROR(14,"订单支付状态异常"),
    ORDER_CREATE_FAIL(15,"创建订单失败"),
    ORDER_CANCEL_SUCCESS(16,"订单取消成功"),
    ORDER_FINISH_SUCCESS(17,"订单完结成功"),

    PARAMETER_ERROR(110,"参数不正确"),
    OWNER_IS_ERROR(111,"非当前登录用户"),
    WX_MP_ERROR(112,"微信公众号方面出现错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(113 ,"微信支付通知金额验证异常"),

    CAR_IS_EMPTY(120,"购物车为空"),
    RESULT_IS_EMPTY(121,"查询结果为空"),
    INDEX_IS_REPEAT(122,"索引键重复"),

    LOGIN_ERROR_USER_NOT_EXIST(130,"登录异常用户不存在"),
    LOGOUT_SUCCESS(131,"退出成功"),
    LOGOUTED(132,"已经退出")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
