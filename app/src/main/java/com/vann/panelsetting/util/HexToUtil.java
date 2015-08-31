package com.vann.panelsetting.util;

/**
 * @Author: wenlong.bian 2015-08-24
 * @E-mail: bxl049@163.com
 */
public class HexToUtil {
    /**
     * 16进制字符串转化为字节数组
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * bytes转换成十六进制字符串
     */
    public static String byte2HexStr(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            // if (n<b.length-1) hs=hs+":";
        }
        return hs.toUpperCase();
    }


    /**
     * 十进制转化为十六进制
     * @param params
     * @return
     */
    public static String stringToHex(String params){
        if(params == null || params==""){
            return "";
        }
        Integer value = Integer.valueOf(params);
        return Integer.toHexString(value);
    }

    /**
     * 二进制转16进制
     * @param params
     * @return
     */
    public static String binaryToHex(String params)
    {
       return Long.toHexString(Long.parseLong(params,2));
    }

}
