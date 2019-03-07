package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.datasource.DataBaseFB;

import java.io.Serializable;
import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    BackendFactory backendFactory = new BackendFactory();
    final Backend backend = backendFactory.getInstance(this);

    Travel travel1 = new Travel(Travel.States.FREE, "Ashdood", "Jerusalem", new Time(0, 0, 0), new Time(0, 0, 0), "Yehouda", "0586808006", "ankoninam@hotmail.com");
    Travel travel2 = new Travel(Travel.States.FREE, "BaitVagan", "BeitHadfouss", new Time(0, 0, 0), new Time(0, 0, 0), "Yaacov", "0586808006", "ankoninam@hotmail.com");

    public Driver getRegisteredDriver() {
        return registeredDriver;
    }

    public void setRegisteredDriver(Driver registeredDriver) {
        this.registeredDriver = registeredDriver;
    }

    Driver registeredDriver;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            TextView usernameText = (TextView) findViewById(R.id.login_username);
            TextView passwordText = (TextView) findViewById(R.id.login_password);
            String usernameGet = "";
            String passwordGet = "";
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
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

                    } catch (Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });

            Button signIn = (Button) findViewById(R.id.login_signIn);

            final String prefName="";

            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        TextView usernameText = (TextView) findViewById(R.id.login_username);
                        final String username = usernameText.getText().toString();
                        TextView passwordText = (TextView) findViewById(R.id.login_password);
                        final String password = passwordText.getText().toString();

                        Driver driver = ((DataBaseFB) BackendFactory.getInstance(MainActivity.this)).valid(username, password);
                        //get the current driver
                        if (driver != null) {//move the driver to his space
                            registeredDriver=driver;

                        }
                        else {
                            Toast.makeText(MainActivity.this, "Try again",
                                    Toast.LENGTH_LONG).show();//message to user
                        }
                        if(backend.checkDriverValid(driver)==false)
                            throw new Exception("Your information is not correct! Please enter again or register!");

                        prefs = getSharedPreferences(prefName,MODE_PRIVATE);
                        SharedPreferences.Editor editor=prefs.edit();
                        editor.putString("Username: ", usernameText.getText().toString());
                        editor.putString("Password: ", passwordText.getText().toString());
                        editor.commit();

                        Intent signIn = new Intent(MainActivity.this, NavMenu.class);
                        signIn.putExtra("username", usernameText.getText().toString());
                        signIn.putExtra("password", passwordText.getText().toString());
                        startActivity(signIn);
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
            SharedPreferences prefs =getSharedPreferences(prefName,MODE_PRIVATE);
            usernameText.setText(prefs.getString(usernameGet,""));
            passwordText.setText(prefs.getString(passwordGet,""));
            Toast toast = Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG);
            toast.show();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
