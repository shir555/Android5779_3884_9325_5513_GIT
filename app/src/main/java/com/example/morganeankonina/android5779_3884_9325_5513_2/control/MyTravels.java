package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;

public class MyTravels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_travels);
        String[] arraySpinner = new String[] {
                "Sort By:", "Name", "From ", "To ", "Price"
        };
        Spinner s = (Spinner) findViewById(R.id.filterSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }
}
