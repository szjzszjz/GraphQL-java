package com.benma.graphql.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author szjz
 * @date 2019/5/13 15:17
 * 对象json 互转工具
 */
public class JsonUtil {

    /** 对象转json */
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);

    }
}
