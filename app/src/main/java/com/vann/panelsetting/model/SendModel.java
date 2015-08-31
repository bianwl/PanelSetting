package com.vann.panelsetting.model;

/**
 * @Author: wenlong.bian 2015-08-24
 * @E-mail: bxl049@163.com
 */
public class SendModel {
    // 组地址
    private String groupAddr;
    //子地址
    private String childAddr;
    //地址
    private String addr;
    //通信协议
    private String data;

    private int type;

    public SendModel(){};

    public SendModel(String addr,String data){
        this.addr = addr;
        this.data = data;
    }


    public String getGroupAddr() {
        return groupAddr;
    }

    public void setGroupAddr(String groupAddr) {
        this.groupAddr = groupAddr;
    }

    public String getChildAddr() {
        return childAddr;
    }

    public void setChildAddr(String childAddr) {
        this.childAddr = childAddr;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "A6" + addr + "05" + data + "00EE";
    }


}
