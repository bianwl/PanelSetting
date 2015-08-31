package com.vann.panelsetting.util;

import com.vann.panelsetting.model.SendSocket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Author: wenlong.bian 2015-08-20
 * @E-mail: bxl049@163.com
 */
public class UdpUtil {

    public static void send(String msg,String server_ip,int server_port){
        DatagramSocket send = null;
        try{
            send = new DatagramSocket();
            new Thread(new SendSocket(send,msg,server_ip,server_port)).start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
