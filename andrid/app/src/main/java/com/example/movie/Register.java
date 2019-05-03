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
                Intent signinInt = new Intent(Register.this,MainActivity.class);
                startActivity(signinInt);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = regusername.getText().toString();
                final String password = regpassword.getText().toString();
                final String con_password = confirm_password.getText().toString();

                if(!username.isEmpty() && !password.isEmpty() && !con_password.isEmpty()){
                    if(password.equals(con_password)){

                        regusername.setText(null);
                        regpassword.setText(null);
                        confirm_password.setText(null);
                        Response.Listener<String> stringListener =new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObjectRes =new JSONObject(response);
                                    boolean success = jsonObjectRes.getBoolean("success");

                                    if(success){
                                        Intent intent = new Intent(Register.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                        builder.setMessage("Register Failed!").setNegativeButton("Retry",null).create().show();

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };


                        RegisterRequest registerRequest = new RegisterRequest(username, password,stringListener);
                        RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
                        requestQueue.add(registerRequest);
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage("Password Mismatch !").setNegativeButton("Ok",null).create().show();
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("Please fill the form correctly.").setNegativeButton("Ok",null).create().show();
                }
            }
        });

    }
}
