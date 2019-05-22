package com.benma.graphql.result;

import com.benma.graphql.enums.ResultEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * author:szjz
 * date:2019/5/22
 * 响应结果
 */

@Data
public class ResultObject<T> implements Serializable {


    private static final long serialVersionUID = 6144539475197103721L;
    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String message;

    /** 返回的数据 */
    @JsonProperty("data")
    private T data;


    /**
     * 成功获取数据返回
     */
    public static ResultObject success(Object data) {
        ResultObject<Object> ro = new ResultObject<>();
        ro.setMessage("成功");
        ro.setCode(200);
        ro.setData(data);
        return ro;
    }

    /**
     * 成功 返回无数据的结果
     */
    public static ResultObject success() {
        ResultObject<Object> ro = new ResultObject<>();
        ro.setCode(200);
        ro.setMessage("成功");
        return ro;
    }

    /**
     * 成功 返回枚举信息
     */
    public static ResultObject success(ResultEnum resultEnum) {
        ResultObject<Object> ro = new ResultObject<>();
        ro.setCode(resultEnum.getCode());
        ro.setMessage(resultEnum.getMessage());
        return ro;
    }

    /**
     * 返回错误 错误代码 信息
     */
    public static ResultObject error(Integer code, String msg) {
        ResultObject<Object> ro = new ResultObject<>();
        ro.setCode(code);
        ro.setMessage(msg);
        return ro;
    }

    /** 返回错误 枚举信息 */
    public static ResultObject error(ResultEnum resultEnum){
        ResultObject<Object> ro = new ResultObject<>();
        ro.setCode(resultEnum.getCode());
        ro.setMessage(resultEnum.getMessage());
        return ro;
    }


}
