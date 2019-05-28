package com.benma.graphql.Utils;

import com.benma.graphql.enums.ResultEnum;
import com.benma.graphql.result.ResultObj;

/**
 * @author szjz
 * @date 2019/5/8 15:09
 * 调用该类获取最终的返回对象
 */
public class ResultObjUtil {

    /**
     * 成功获取数据返回
     */
    public static ResultObj success(Object data) {
        ResultObj<Object> ro = new ResultObj<>();
        ro.setMessage("成功");
        ro.setCode(200);
        ro.setData(data);
        return ro;
    }

    /**
     * 成功 返回无数据的结果
     */
    public static ResultObj success() {
        ResultObj<Object> ro = new ResultObj<>();
        ro.setCode(200);
        ro.setMessage("成功");
        return ro;
    }

    /**
     * 成功 返回枚举信息
     */
    public static ResultObj success(ResultEnum resultEnum) {
        ResultObj<Object> ro = new ResultObj<>();
        ro.setCode(resultEnum.getCode());
        ro.setMessage(resultEnum.getMessage());
        return ro;
    }

    /**
     * 返回错误 错误代码 信息
     */
    public static ResultObj error(Integer code, String msg) {
        ResultObj<Object> ro = new ResultObj<>();
        ro.setCode(code);
        ro.setMessage(msg);
        return ro;
    }

    /** 返回错误 枚举信息 */
    public static ResultObj error(ResultEnum resultEnum){
        ResultObj<Object> ro = new ResultObj<>();
        ro.setCode(resultEnum.getCode());
        ro.setMessage(resultEnum.getMessage());
        return ro;
    }


}
