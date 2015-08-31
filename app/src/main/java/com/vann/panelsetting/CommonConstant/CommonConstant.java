package com.vann.panelsetting.CommonConstant;

/**
 * @Author: wenlong.bian 2015-08-28
 * @E-mail: bxl049@163.com
 */
public interface CommonConstant {

    public static final String  SET_ADDRESS_TIP="设置面板地址为【%s】,【%s】为组地址，【%s】为子地址";
    public static  final String SET_SENCE_TIP_K="例如：A6 03 03 05 cc 02 31 64 00 00 EE,设置地址03 03 的调光面板的第一路场景为第一路灯光亮度值100，第二路关。";
    public static final String  CANCEL_SENCE_TIP_K="例如：A6 00 01 05 cc 01 21 00 00 00 EE,取消地址00 01 的开关面板的第一路场景设置";
    public static final String SET_MONITOR_TIP_K="例如：A6 00 01  05 cc 01 14  00 00 00 EE,设置地址00 01 的开关面板的第四路路场景为监视";
    public static final String CANCEL_MONITOR_TIP_K="例如：A6 00 01  05 cc 02 01 00 00 00 EE取消地址00 01 的开关面板的监视功能。";
    public static final String SET_SENCE_TIP_T="例如：A6 03 03 05 cc 02 31 64 00 00 EE,设置地址03 03 的调光面板的第一路场景为第一路灯光亮度值100，第二路关。";
    public static final String CANCEL_SENCE_TIP_T="例如：A6 00 01 05 cc 01 21 00 00 00 EE,取消地址00 01 的开关面板的第一路场景设置";
    public static final String  SET_MONITOR_TIP_T="例如：A6 00 01  05 cc 02 11  00 00 00 EE,设置地址00 01 的开关面板的第一路场景为监视";
    public static final String CANCEL_MONITOR_TIP_T="例如：A6 00 01  05 cc 02 01 00 00 00 EE ,取消地址00 01 的开关面板的监视功能";
    public static final String SET_SENCE_TIP_C="例如：A6 03 03 05 cc 03 31 03 00 00 EE,设置地址03 03 的窗帘面板的第一路场景为第一路开，第二路开。";
    public static final String CANCEL_SENCE_TIP_C="例如：A6 00 01 05 cc 03 21 00 00 00 EE,取消地址00 01 的窗帘面板的第一路场景设置";

    public static final String SET_SENCE_TIP_GROUP="例如：A6 01  xx  05 cc 04 31 01 00 00 EE,将组地址为01的所有面板当前模块的状态设置为第一路场景";
    public static final String CANCEL_SENCE_TIP_GROUP="例如：A6 01  xx  05 cc 04 21 00 00 00 EE,将组地址为01的所有面板的第一路场景取消";

}
