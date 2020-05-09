package com.unionman.springbootelasticsearchdemo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rong.Jia
 * @description 判断工具类
 * @date 2019/01/15 09:18
 */
@Slf4j
public class AssertUtils {



    /**
     * @param obj 判断值
     * @return boolean
     * @description: 判断obj 是否为空
     */
    public static boolean isNull(Object obj) {

        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            if (StringUtils.isBlank(obj.toString())) {
                return true;
            }
        }

        if (obj instanceof Optional) {
            return !((Optional) obj).isPresent();
        }

        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return CollectionUtils.isEmpty((Collection<?>) obj);
        }
        if (obj instanceof Map) {
            return MapUtils.isEmpty((Map) obj);
        }

        return false;

    }

    /**
     * @param obj 判断值
     * @return boolean
     * @description: 判断obj 是否不为空
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * @param values 判断值
     * @return boolean
     * @description: 判断obj 全部不为空
     */
    public static boolean allNotNull(final Object... values) {
        if (values == null) {
            return false;
        }

        for (final Object val : values) {
            if (val == null) {
                return false;
            }
        }

        return true;
    }



    public static boolean isEquals(Object obj, Object obj1) {

        if (obj instanceof Number && obj1 instanceof Number) {
            return obj.equals(obj1);
        }

        if (obj instanceof String && obj1 instanceof String) {

            return StringUtils.equals(obj1.toString(), obj.toString());

        }

        return false;

    }


    /**
     * @return
     * @description: 判断第一个不为空， 第二个为空
     */
    public static boolean isFirstNotNullAndSecondNull(Object obj, Object obj2) {

        if (isNotNull(obj) && isNull(obj2)) {
            return true;
        }

        return false;

    }


}
