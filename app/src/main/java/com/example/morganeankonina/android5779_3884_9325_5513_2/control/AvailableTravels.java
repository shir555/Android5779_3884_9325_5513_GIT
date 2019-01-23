package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;


public class AvailableTravels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_travels);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //AvailableTravelsList fragment = new AvailableTravelsList();
        //fragmentTransaction.add(R.id.frag1,fragment);
        //fragmentTransaction.commit();
    }

}
