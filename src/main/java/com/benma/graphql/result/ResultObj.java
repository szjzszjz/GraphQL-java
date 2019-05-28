package com.benma.graphql.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * author:szjz
 * date:2019/5/28
 */
@Data
public class ResultObj<T> {

    private static final long serialVersionUID = 1578762596998649599L;
    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String message;

    /** 返回的数据 */
    @JsonProperty("data")
    private T data;
}
