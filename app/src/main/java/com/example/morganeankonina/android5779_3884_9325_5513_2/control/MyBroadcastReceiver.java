package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;

public class MyBroadcastReceiver extends BroadcastReceiver {
    /**
     * This method is called when the BroadcastReceiver is receiving an Intent broadcast
     * @param context is the Context in which the receiver is running
     * @param intent is the Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Toast.makeText(context.getApplicationContext(), "The list was updated sucessfully", Toast.LENGTH_LONG).show();
            // Get instance of Vibrator from current Context
            NotificationCompat.Builder notifi=
                    new NotificationCompat.Builder(context).
                            setSmallIcon(R.drawable.iconapp).
                            setContentTitle("Updated the list").
                            setContentText("HEy");

            NotificationManager notification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notification.notify();
        }
        catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
