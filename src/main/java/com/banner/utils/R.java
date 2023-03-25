package com.banner.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rjj
 * @date 2022/9/16 - 9:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {
    //编码：1成功，0和其它数字为失败
    private int code;
    private String msg;
    private T data;

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    public static <T> R<T> success(String msg){
        R r = new R();
        r.msg = msg;
        r.code = 200;
        return r;
    }

    public static <T> R<T> success(String msg, T data){
        R<T> r = new R<T>();
        r.data = data;
        r.code = 200;
        r.msg = msg;
        return r;
    }


    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 500;
        return r;
    }

}
