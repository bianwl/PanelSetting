package com.vann.panelsetting.model;

import android.util.Log;

import com.vann.panelsetting.util.HexToUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author: wenlong.bian 2015-08-24
 * @E-mail: bxl049@163.com
 */
public class SendSocket implements  Runnable {

    private DatagramSocket socket;
    private String server_ip ;
    private int server_port;
    private String content;

    public SendSocket(DatagramSocket socket,String msg,String ip,int port){
        this.socket = socket;
        this.content =msg;
        this.server_ip = ip;
        this.server_port = port;
    }

    @Override
    public void run() {
        try{
            byte[] sendMsg= HexToUtil.hexStringToBytes(content);
            DatagramPacket sendDp  = new DatagramPacket(sendMsg,sendMsg.length, InetAddress.getByName(server_ip),server_port);
            socket.send(sendDp);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("SendSocket",e.getMessage());
        }finally {
            if(socket != null){
                socket.close();
            }
        }


    }
}
