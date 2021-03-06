package com.example.android.favoritetoys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.*;
import android.Manifest;
import android.widget.*;
import android.view.*;
import java.util.*;
import android.content.pm.*;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    private static final String  TAG = "My Activity";
    private StringBuilder sb = new StringBuilder();
    private TextView tv;
    private Context context;
    private WifiManager wifiManager;
    List<ScanResult> scanList;
    TextView txtWifiInfo;
    ToggleButton wifiOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String CoarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
        final String AccessWifi = Manifest.permission.ACCESS_WIFI_STATE;
        final String ChangeWifi = Manifest.permission.CHANGE_WIFI_STATE;

        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        //wifiManager.setWifiEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWifiInfo = (TextView) findViewById(R.id.idTxt);
        Button btn = (Button) findViewById(R. id. idBtn);

        wifiOnOff = (ToggleButton) findViewById(R.id.toggleButton);
        wifiOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    WifiManager onwifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    onwifi.setWifiEnabled(true);
                } else{
                    WifiManager offwifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    offwifi.setWifiEnabled(false);
                }
            }
        });

    }

    private static String getIpAddress(WifiInfo wifiInfo) {
        String result;
        int ip = wifiInfo.getIpAddress();

        result = String.format("%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff),
                (ip >> 24 & 0xff));

        return result;
    }

    public void getWifiInformation(View view){
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ip = getIpAddress(wifiInfo);
        String macAddress = wifiInfo.getMacAddress();
        int frequency = wifiInfo.getFrequency();
        String bssid = wifiInfo.getBSSID();
        int rssi = wifiInfo.getRssi();
        int linkspeed = wifiInfo.getLinkSpeed();
        String ssid = wifiInfo.getSSID();
        int networkId = wifiInfo.getNetworkId();
        int level = wifiManager.calculateSignalLevel(wifiInfo.getRssi(),1000);
        String info = "MacAddress "+macAddress+"\n"+"BSSID "+bssid+"\n"
                +"SSID "+ssid+"\n"+"Network ID "+networkId + "\n IP: "+ ip
                + "\n Frequency: "+ frequency + "\n linkspeed: "+linkspeed
                + "\n RSSI:" +rssi + "\n signal level:" +level;
        txtWifiInfo.setText(info);
    }
}
