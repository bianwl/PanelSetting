package com.vann.panelsetting.model;

import android.util.Log;

import com.vann.panelsetting.util.HexToUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Author: wenlong.bian 2015-08-25
 * @E-mail: bxl049@163.com
 */
public class RecieveSocket implements Runnable {
    private DatagramSocket mSocket;

    public  RecieveSocket(DatagramSocket socket){
        this.mSocket = socket;
    }
    @Override
    public void run() {
        String result="";
        System.out.println("***接收*****");
        byte[] buf  =new byte[11];
        DatagramPacket recieveDp = new DatagramPacket(buf, buf.length);
        while(true){

            try {
                mSocket.receive(recieveDp);
                String value = new String(buf,0,recieveDp.getLength());
                byte[] bytes =recieveDp.getData();
                result = HexToUtil.byte2HexStr(bytes);

            } catch (IOException e) {
                Log.e("RecieveSocket",e.getMessage());
                e.printStackTrace();
            }
            Log.e("RecieveSocket",result);

        }
    }
}
