package com.example.movielocater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sign_in extends AppCompatActivity {

    EditText username;
    EditText password;
    Button signin;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        register = findViewById(R.id.signin_to_reg);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerInt = new Intent(Sign_in.this,Register.class);
                startActivity(registerInt);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test =new Intent(Sign_in.this, MainActivity.class);
                startActivity(test);
            }
        });
    }
}
