package com.mobdev.week8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //String message = intent.getStringExtra("message");
        if (intent.getAction() != null &&
            intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);

            String msg = isAirplaneModeOn ? "Airplane Mode is On" : "Airplane Mode is Off";
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }


}
