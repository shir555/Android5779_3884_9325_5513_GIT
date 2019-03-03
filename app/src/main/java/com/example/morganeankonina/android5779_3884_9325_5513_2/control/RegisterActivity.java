package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.BackendFactory;

public class RegisterActivity extends AppCompatActivity {

    /**
     * The function emailValid checks if the email is valid
     *
     * @param email
     * @return boolean
     */
    public static boolean emailValid(String email) {
        if (email != null && email.trim().length() > 0)
            return email.matches("^[a-zA-Z0-9\\.\\-\\_]+@([a-zA-Z0-9\\-\\_\\.]+\\.)+([a-zA-Z]{2,4})$");
        return false;
    }

    /**
     * The function phoneValid checks if the phone is valid
     *
     * @param phone
     * @return boolean
     */
    public static boolean phoneValid(String phone) {
        if (phone.length() != 10 || phone.charAt(0) != '0' || phone.charAt(1) != '5')
            return false;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button ok = (Button) findViewById(R.id.register_ok);

        /**
         * When user click on bouton ok the user enters all information and the function adds them to DB
         */
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BackendFactory backendFactory = new BackendFactory();
                    final Backend backend = backendFactory.getInstance(RegisterActivity.this);

                    /**
                     * Get all information from view
                     */
                    TextView usernameText = (TextView) findViewById(R.id.userEdit);
                    final String username = usernameText.getText().toString();
                    TextView passwordText = (TextView) findViewById(R.id.passwordEdit);
                    final String passoword = passwordText.getText().toString();
                    TextView lastNameText = (TextView) findViewById(R.id.LastNameEdit);
                    final String lastName = lastNameText.getText().toString();
                    TextView firstNameText = (TextView) findViewById(R.id.FirstNameEdit);
                    final String firstName = firstNameText.getText().toString();
                    TextView idText = (TextView) findViewById(R.id.IdEdit);
                    final String id = idText.getText().toString();
                    TextView phoneText = (TextView) findViewById(R.id.TelephoneEdit);
                    final String phone = phoneText.getText().toString();
                    TextView emailText = (TextView) findViewById(R.id.EmailEdit);
                    final String email = emailText.getText().toString();
                    TextView creditEdit = (TextView) findViewById(R.id.CardEdit);
                    final String credit = creditEdit.getText().toString();

                    /**
                     * Conditions for a validation of the data
                     */
                    if (username.length() < 1)
                        throw new Exception("Please enter your start point!");
                    if (passoword.length() < 1)
                        throw new Exception("Please enter your destination!");
                    if (lastName.length() < 1)
                        throw new Exception("Please enter your name!");
                    if (firstName.length() < 1)
                        throw new Exception("Please enter your email!");
                    if (id.length() < 1)
                        throw new Exception("Please enter your email!");
                    if (phone.length() < 1)
                        throw new Exception("Please enter your email!");
                    if (email.length() < 1)
                        throw new Exception("Please enter your email!");
                    if (credit.length() < 1)
                        throw new Exception("Please enter your email!");
                    if (emailValid(email) == false)
                        throw new Exception("Your email is not valid. Please enter again!");
                    if (phoneValid(phone) == false)
                        throw new Exception("Your phone is not valid. Please enter again!");

                    Driver driver=new Driver(username, passoword, lastName, firstName, id, phone, email, credit);
                    String driverDetails=backend.addDriver(driver);
                    Toast toast = Toast.makeText(getApplicationContext(),"Your information added successufully\n"+driverDetails, Toast.LENGTH_LONG);
                    toast.show();

                    Intent goLogIn= new Intent(RegisterActivity.this, MainActivity.class);
                    goLogIn.putExtra("username", username);
                    goLogIn.putExtra("password ", passoword);
                    startActivity(goLogIn);

                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                    //e.printStackTrace();
                }
            }
        });
    }
}