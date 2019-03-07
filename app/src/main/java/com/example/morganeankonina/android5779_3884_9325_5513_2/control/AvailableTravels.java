package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.*;
import android.support.v4.app.*;
import android.app.*;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.BackendFactory;


public class AvailableTravels extends AppCompatActivity {
    BackendFactory backendFactory = new BackendFactory();
    Backend backend = backendFactory.getInstance(this);
    public Driver registerDriver;
    public Driver getRegisterDriver() {
        return registerDriver;
    }

    public void setRegisterDriver(Driver registerDriver) {
        this.registerDriver = registerDriver;
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_travels);
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction=manager.beginTransaction();
        //FragmentManager manager=getFragmentManager();
        //FragmentTransaction transaction=manager.beginTransaction();
        //FilterAvailableTravels filterFrag=new FilterAvailableTravels();
        //AvailableTravelsList listFrag=new AvailableTravelsList();
        //AvailableTravelsDetails detailsFrag=new AvailableTravelsDetails();

        transaction.add(R.id.filter_word, new FilterAvailableTravels()).commit();
        //transaction.replace(R.id.availableList, listFrag, "AvailableTravelsListTag");
        //transaction.replace(R.id.availableDetails, detailsFrag, "AvailableTravelsDetailsTag");
        //transaction.commit();

        /*Intent intent=getIntent();
        String un = intent.getStringExtra("username");
        String pass = intent.getStringExtra("password");
        registerDriver=  backend.getDriverByDetails(un, pass);*/
    }

}
