package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.BackendFactory;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.datasource.DataBaseFB;

import java.util.List;
import java.util.TreeMap;

public class MyService extends Service {

    DataBaseFB dataBaseManager;

    /**
     * This method is activated when another component requests to start the service
     * @param intent The Intent supplied to Context.startService(Intent).
     * @param flags Additional data on the request.
     * @param startId A unique integer identifying this specific request
     * @return value indicates what semantics the system should use for the service's current started state.
     */
    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        dataBaseManager = (DataBaseFB) BackendFactory.getInstance(this);
        dataBaseManager.notifyToTravelList(new DataBaseFB.NotifyDataChange<List<Travel>>() {

            @Override
            public void onDataChanged(List<Travel> obj) {//sending a broadcast
                try {
                    Intent intent = new Intent(getApplicationContext(), MyBroadcastReceiver.class);
                    sendBroadcast(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Exception exception) {
            }
        });
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
