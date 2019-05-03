package com.example.movie;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button signin;
    TextView register;
    public static String global_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        register = findViewById(R.id.signin_to_reg);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerInt = new Intent(MainActivity.this,Register.class);
                startActivity(registerInt);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String uname = username.getText().toString();
                final String pass = password.getText().toString();

                username.setText(null);
                password.setText(null);

                if(!uname.isEmpty() && !pass.isEmpty()){
                    Response.Listener<String> stringListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");

                                if(success){
                                    global_username = uname;
                                    Intent movieloactor =new Intent(MainActivity.this, Home.class);
                                    startActivity(movieloactor);

                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Login Failed!").setNegativeButton("Retry",null).create().show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    SigninRequest signinRequest =new SigninRequest(uname,pass,stringListener);
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(signinRequest);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please provide correct username and password !").setNegativeButton("Ok",null).create().show();
                }
            }
        });
    }
}
