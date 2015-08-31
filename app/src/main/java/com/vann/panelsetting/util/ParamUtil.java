package com.vann.panelsetting.util;


/**
 * @Author: wenlong.bian 2015-08-31
 * @E-mail: bxl049@163.com
 */
public class ParamUtil {

    /**
     * 字符串是否为空
     * @param params
     * @return
     */
    public static boolean isEmpty(String params){
        if(params == null || "".equals(params)){
            return true;
        }
        return false;
    }
}
