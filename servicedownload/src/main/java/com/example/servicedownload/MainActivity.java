package com.example.servicedownload;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button start, stop, cancel;
    DownLoadService.DownLoadBinder downLoadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downLoadBinder = (DownLoadService.DownLoadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.bt_start);
        stop = (Button) findViewById(R.id.bt_stop);
        cancel = (Button) findViewById(R.id.bt_cancel);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        cancel.setOnClickListener(this);

        Intent intent = new Intent(this, DownLoadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        if (downLoadBinder == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.bt_start:
                downLoadBinder.startDownload("https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe");
                break;

            case R.id.bt_stop:
                downLoadBinder.pauseDownload();
                break;

            case R.id.bt_cancel:
                downLoadBinder.cancelDownload();
                break;
            default:
        }
    }
}
