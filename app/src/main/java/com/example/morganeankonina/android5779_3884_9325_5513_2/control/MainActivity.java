package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.BackendFactory;

public class MainActivity extends AppCompatActivity {
    BackendFactory backendFactory = new BackendFactory();
    final Backend backend = backendFactory.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView usernameText = (TextView) findViewById(R.id.login_username);
        TextView passwordText = (TextView) findViewById(R.id.login_password);
        String usernameGet="";
        String passwordGet="";
        Bundle extras =getIntent().getExtras();
        if (extras!=null) {
            usernameGet = extras.getString("username");
            passwordGet = extras.getString("password");
            usernameText.setText(usernameGet);
            passwordText.setText(passwordGet);
        }

        TextView registerOk = (TextView) findViewById(R.id.login_reg_text);

        /**
         * When user click on text don't have account the user enters all information and the function adds them to DB
        */

        registerOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(register);
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


        Button signIn=(Button) findViewById(R.id.login_signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    TextView usernameText = (TextView) findViewById(R.id.login_username);
                    final String username = usernameText.getText().toString();
                    TextView passwordText = (TextView) findViewById(R.id.login_password);
                    final String password = passwordText.getText().toString();

                    Driver driver= new Driver(username, password, "", "", "", "", "","");
                    boolean flag = false;
                    for (Driver item:backend.getDrivers())
                        if (item.getUsername()==driver.getUsername())
                            if(item.getPassword()==driver.getPassword())
                                flag = true;
                    if (flag == false)
                        throw new Exception("Your information is not correct! Please enter again or register!");
                    //if(backend.checkDriverValid(driver)==false)
                    //    throw new Exception("Your information is not correct! Please enter again or register!");
                    Intent signIn = new Intent(MainActivity.this,NavMenu.class);
                    startActivity(signIn);
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
}
