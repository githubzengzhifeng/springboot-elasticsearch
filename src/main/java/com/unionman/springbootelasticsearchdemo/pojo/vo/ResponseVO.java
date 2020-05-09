package com.unionman.springbootelasticsearchdemo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;


/**
 * @description 数据返回统一格式vo对象
 * @author Zhifeng.Zeng
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -437839076132402939L;

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 描述
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<T>(200,"success", data);
    }

    public static <T> ResponseVO<T> success() {
        return new ResponseVO<T>(200,"success", null);
    }

}
