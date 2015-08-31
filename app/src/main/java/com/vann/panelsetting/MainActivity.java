package com.vann.panelsetting;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.vann.panelsetting.CommonConstant.CommonConstant;
import com.vann.panelsetting.model.SendModel;
import com.vann.panelsetting.util.HexToUtil;
import com.vann.panelsetting.util.ParamUtil;
import com.vann.panelsetting.util.UdpUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class MainActivity extends Activity implements CommonConstant{

    private Spinner mSpinner;
    private Button mSetting;
    private Button mCancel;
    private Button mSearch;
    private EditText edt_group;
    private EditText edt_child;
    private EditText edt_sence;
    private EditText edt_circuit;
    private EditText edt_circut2;
    private EditText edt_circut3;
    private EditText edt_ip;
    private EditText edt_port;
    private TextView tip;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    private void initListener() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                setViewEnable(true);
                mSearch.setEnabled(false);
                edt_circuit.setText("");
                edt_circut2.setText("");
                edt_circut3.setText("");
                switch (position) {
                    case 0:
                        edt_circuit.setEnabled(false);
                        edt_sence.setEnabled(false);
                        edt_circut2.setEnabled(false);
                        edt_circut3.setEnabled(false);
                        mCancel.setEnabled(false);
                        break;
                    case 1:
                        setViewEnable(false);
                        mSearch.setEnabled(true);
                        break;
                    case 3:
                    case 5:
                        edt_circuit.setEnabled(false);
                        edt_circut3.setEnabled(false);
                        edt_circut2.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSearch();
            }
        });
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicked(0);
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicked(1);
            }
        });
    }


    private void handleSearch() {
        tip.setText("");
        if (TextUtils.isEmpty(getServerPort()) || TextUtils.isEmpty(getServerIp())) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] sends = HexToUtil.hexStringToBytes("A6EFFF05bb0000000000EE");
                    DatagramSocket socket = new DatagramSocket();
                    int port = Integer.valueOf(getServerPort());
                    DatagramPacket sendDp = new DatagramPacket(sends, sends.length, InetAddress.getByName(getServerIp()), port);
                    socket.send(sendDp);
                    byte[] recieves = new byte[11];
                    DatagramPacket recDp = new DatagramPacket(recieves, recieves.length);
                    while (true) {
                        socket.receive(recDp);
                        final String result = HexToUtil.byte2HexStr(recieves);
                        Log.e("MainActivity", result);
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tip.setText("返回结果数据:" + result);
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String getServerIp() {
        String address = edt_ip.getText().toString();
        if (TextUtils.isEmpty(address)) {
            new AlertDialog.Builder(this).setTitle(R.string.warnning)
                    .setMessage(R.string.enable_null_ip)
                    .setPositiveButton(R.string.ok, null).show();

        }
        return address;
    }

    private String getServerPort() {
        String port = edt_port.getText().toString();
        if (TextUtils.isEmpty(port)) {
            new AlertDialog.Builder(this).setTitle(R.string.warnning)
                    .setMessage(R.string.enable_null_port)
                    .setPositiveButton(R.string.ok, null).show();
        }
        return port;
    }


    /**
     * 设置控件编辑状态
     */
    private void setViewEnable(boolean isEnable) {
        edt_child.setEnabled(isEnable);
        edt_group.setEnabled(isEnable);
        edt_circuit.setEnabled(isEnable);
        edt_circut2.setEnabled(isEnable);
        edt_circut3.setEnabled(isEnable);
        edt_sence.setEnabled(isEnable);
        mSetting.setEnabled(isEnable);
        mCancel.setEnabled(isEnable);
    }

    private void initView() {
        mSetting = (Button) findViewById(R.id.btn_setting);
        mCancel = (Button) findViewById(R.id.btn_cancel);
        mSearch = (Button) findViewById(R.id.btn_search);
        edt_group = (EditText) findViewById(R.id.edt_group);
        edt_child = (EditText) findViewById(R.id.edt_child);
        edt_sence = (EditText) findViewById(R.id.edt_sence);
        edt_circuit = (EditText) findViewById(R.id.edt_circuit);
        edt_circut2 = (EditText) findViewById(R.id.edt_circuit2);
        edt_circut3 = (EditText) findViewById(R.id.edt_circuit3);
        edt_ip = (EditText) findViewById(R.id.edt_ip);
        edt_port = (EditText) findViewById(R.id.edt_port);
        tip = (TextView) findViewById(R.id.tv_note);
        mSpinner = (Spinner) findViewById(R.id.sp_style);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.orderstyle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }


    private void handleClicked(int code) {

        if (TextUtils.isEmpty(getServerIp()) || TextUtils.isEmpty(getServerPort())) {
            return;
        }
        SendModel model = null;
        String data;
        String tips = "";
        StringBuilder sb;
        switch (index) {
            case 0: //地址设置
                model = new SendModel();
                data = "aa" + getAddr() + "0000";
                model = new SendModel("0000", data);
                tips = String.format(SET_ADDRESS_TIP, getAddr(), getGroupValue(), getmChildValue());
                tip.setText(tips);
                break;
            case 2: //开关场景设置
                sb = new StringBuilder("发送:");
                if (code == 0) {
                    data = "cc013" + getSenceValue() + getCircuitValue(false);
                    tips =SET_SENCE_TIP_K;
                } else {
                    data = "cc012" + getSenceValue() + getCircuitValue(false);
                    tips = CANCEL_SENCE_TIP_K;
                }
                model = new SendModel(getAddr(), data);
                sb.append(model.toString()).append("\n").append("注释:").append("\n").append(tips);
                tip.setText(sb.toString());
                break;
            case 3: // 开关监视设置
                sb = new StringBuilder("发送");
                if (code == 0) {
                    data = "cc011" + getSenceValue() + "0000";
                    tips = SET_MONITOR_TIP_K;
                } else {
                    data = "cc010" + getSenceValue() + "0000";
                    tips = CANCEL_MONITOR_TIP_K;
                }
                model = new SendModel(getAddr(), data);
                sb.append(model.toString()).append("\n").append("注释:").append("\n").append(tips);
                tip.setText(sb.toString());
                break;
            case 4://调光场景设置
                sb = new StringBuilder("发送");
                if (code == 0) {
                    data = "cc023" + getSenceValue() + getCircuitValue(true);
                    tips = SET_SENCE_TIP_T;
                } else {
                    data = "cc022" + getSenceValue() + getCircuitValue(true);
                    tips = CANCEL_SENCE_TIP_T;
                }
                model = new SendModel(getAddr(), data);
                sb.append(model.toString()).append("\n").append("注释:").append("\n").append(tips);
                tip.setText(sb.toString());
                break;
            case 5://调光监视设置
                sb = new StringBuilder("发送");
                if (code == 0) {
                    data = "cc021" + getSenceValue() + "0000";
                    tips = SET_MONITOR_TIP_T;
                } else {
                    data = "cc020" + getSenceValue() + "0000";
                    tips = CANCEL_MONITOR_TIP_T;
                }
                model = new SendModel(getAddr(), data);
                sb.append(model.toString()).append("\n").append("注释:").append("\n").append(tips);
                tip.setText(sb.toString());
                break;
            case 6://窗帘场景设置
                sb = new StringBuilder("发送");
                if (code == 0) {
                    data = "cc033" + getSenceValue() + getCircuitValue(false);
                    tips = SET_SENCE_TIP_C;
                } else {
                    data = "cc032" + getSenceValue() + getCircuitValue(false);
                    tips = CANCEL_SENCE_TIP_C;
                }
                model = new SendModel(getAddr(), data);
                sb.append(model.toString()).append("\n").append("注释:").append("\n").append(tips);
                tip.setText(sb.toString());
                break;

            case 7:
                String addrs = getGroupValue() + "00";
                sb = new StringBuilder("发送");
                if (code == 0) {
                    data = "cc043" + getSenceValue() + "0000";
                    tips = SET_SENCE_TIP_GROUP;
                } else {
                    data = "cc042" + getSenceValue() + "0000";
                    tips = CANCEL_SENCE_TIP_GROUP;
                }
                model = new SendModel(addrs, data);
                sb.append(model.toString()).append("\n").append("注释:").append("\n").append(tips);
                tip.setText(sb.toString());
                break;
        }
        if (model != null) {
            UdpUtil.send(model.toString(), getServerIp(), Integer.valueOf(getServerPort()));
        }

    }


    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tip.setText(msg.getData().getShort("data"));

        }
    };

    private String getmChildValue() {
        String val = HexToUtil.stringToHex(edt_child.getText().toString());
        String cValue = String.format("%02d", Integer.valueOf(val));
        return ParamUtil.isEmpty(edt_child.getText().toString()) ? "00" : cValue;
    }

    private String getGroupValue() {
        String val = HexToUtil.stringToHex(edt_group.getText().toString());
        String pValue = String.format("%02d", Integer.valueOf(val));
        return ParamUtil.isEmpty(edt_group.getText().toString()) ? "00" : pValue;
    }

    private String getSenceValue() {
        return HexToUtil.stringToHex(edt_sence.getText().toString());
    }

    private String getCircuitValue(boolean isLight) {
        StringBuilder sb = new StringBuilder();
        if (isLight) {
            String s1 = edt_circuit.getText().toString();
            if (ParamUtil.isEmpty(s1)) {
                s1 = "00";
            }
            String s2 = edt_circut2.getText().toString();
            if (ParamUtil.isEmpty(s2)) {
                s2 = "00";
            }
            String val1= HexToUtil.stringToHex(s1);
            if(val1.length()==1){
                s1="0"+val1;
            }else{
                s1= val1;
            }
            String val2 = HexToUtil.stringToHex(s2);
            if(val2.length()==1){
                s2="0"+val2;
            }else{
                s2 = val2;
            }
           sb.append(s1).append(s2);
            return sb.toString();
        } else {
            String c1 = edt_circuit.getText().toString();
            String c2 = edt_circut2.getText().toString();
            String c3 = edt_circut3.getText().toString();
            if (ParamUtil.isEmpty(c1)) {
                c1 = "0";
            }
            if (ParamUtil.isEmpty(c2)) {
                c2 = "0";
            }
            if (ParamUtil.isEmpty(c3)) {
                c3 = "0";
            }
            sb.append(c1).append(c2).append(c3);
            String val = HexToUtil.binaryToHex(sb.toString());
            return String.format("%02d", Integer.valueOf(val)) + "00";
        }
    }

    private String getAddr() {
        return getGroupValue() + getmChildValue();
    }
}
