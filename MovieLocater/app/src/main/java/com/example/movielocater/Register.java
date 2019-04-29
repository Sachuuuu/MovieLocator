package com.example.movielocater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    EditText regusername;
    EditText regpassword;
    EditText confirm_password;
    Button register;
    TextView sigin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regusername = findViewById(R.id.registration_username);
        regpassword = findViewById(R.id.registration_password);
        confirm_password = findViewById(R.id.RegpasswordConfirm);
        register = findViewById(R.id.regbutton);
        sigin = findViewById(R.id.reg_to_signin);

        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinInt = new Intent(Register.this,Sign_in.class);
                startActivity(signinInt);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
