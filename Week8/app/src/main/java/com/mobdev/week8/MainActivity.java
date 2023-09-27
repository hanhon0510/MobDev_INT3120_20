package com.mobdev.week8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        MyBroadcastReceiver br = new MyBroadcastReceiver();
        registerReceiver(br, intentFilter);

//        button = findViewById(R.id.button);
//
//        doSth();
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent("com.mobdev.week8.ACTION_MY_EVENT");
//                intent.putExtra("message", "Hello, this is a broadcast event!");
//
//                sendBroadcast(intent);
//                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
//
//            }
//        });
    }

    public void doSth() {
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("com.mobdev.week8.ACTION_MY_EVENT");
        registerReceiver(myBroadcastReceiver, intentFilter);
    }
}