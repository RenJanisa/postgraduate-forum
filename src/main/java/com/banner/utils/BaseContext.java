package com.banner.utils;

/**
 * @author rjj
 * @date 2022/9/16 - 9:03
 */


/**
 * 基于ThreadLocal的封装工具类,用于保存和获取当前登陆用户信息
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    //作用在同一线程范围内
    public static void setUser(Long id){
        threadLocal.set(id);
    }

    public static Long getUserId(){
        return threadLocal.get();
    }

    public static void removeUser(){threadLocal.remove();}
}
