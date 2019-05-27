package com.benma.graphql.Utils;

import com.benma.graphql.enums.CodeEnum;
import org.aspectj.apache.bcel.classfile.Code;

/**
 * @author szjz
 * @date 2019/5/14 15:06
 * 枚举工具类
 */

public class EnumUtil {

    /**
     * 通过枚举code 获取枚举类型
     *
     * @param code      枚举代码
     * @param enumClass 枚举类class
     * @param <T>       泛型 继承与CodeEnum
     * @return
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }

    /**
     * 通过枚举类型 获取枚举类型 （主要用于schema中的枚举类和项目中的枚举类之间的映射）
     * @param ele
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getByMsg(String ele, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if(each.toString().equals(ele)) {
                return each;
            }
        }
        return null;
    }

}
