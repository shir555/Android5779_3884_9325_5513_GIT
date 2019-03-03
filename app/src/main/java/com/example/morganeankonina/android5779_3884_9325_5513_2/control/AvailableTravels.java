package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.*;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;


public class AvailableTravels extends AppCompatActivity {

    Driver registerDriver;
    public Driver getRegisterDriver() {
        return registerDriver;
    }

    public void setRegisterDriver(Driver registerDriver) {
        this.registerDriver = registerDriver;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_travels);
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        FilterAvailableTravels filterFrag=new FilterAvailableTravels();
        AvailableTravelsList listFrag=new AvailableTravelsList();
        AvailableTravelsDetails detailsFrag=new AvailableTravelsDetails();

        transaction.add(listFrag, "AvailableTravelsFilterTag");
        transaction.add(R.id.availableList, listFrag, "AvailableTravelsListTag");
        transaction.add(R.id.availableDetails, detailsFrag, "AvailableTravelsDetailsTag");

        Intent intent=getIntent();
        registerDriver= (Driver) intent.getSerializableExtra("driver");
    }

}
