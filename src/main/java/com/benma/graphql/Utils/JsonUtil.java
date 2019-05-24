package com.benma.graphql.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Excluder;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author szjz
 * @date 2019/5/13 15:17
 * 对象json 互转工具
 */
public class JsonUtil {

    public static void main(String[] args) {
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("money",1900);
            String expression="money>=2000&&money<=4000";
            Object code = convertToCode(expression,map);
            System.err.println(code);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Object convertToCode(String jexlExp,Map<String,Object> map){
        JexlEngine jexl=new JexlEngine();
        Expression e = jexl.createExpression(jexlExp);
        JexlContext jc = new MapContext();
        for(String key:map.keySet()){
            jc.set(key, map.get(key));
        }
        if(null==e.evaluate(jc)){
            return "";
        }
        return e.evaluate(jc);
    }
}
