package com.example.movie;

/*
* Settings.java
*
* This displays the password change interface to update
* the password
* */
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Settings extends AppCompatActivity {

    //declare variables
    EditText new_pw_1;
    EditText new_pw_2;
    Button update_pw;
    String Username = MainActivity.global_username; // store the user name in a variable using a global variable in MainActivity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize variables
        setContentView(R.layout.activity_settings);
        new_pw_1 = findViewById(R.id.newpassword);
        new_pw_2 = findViewById(R.id.confirm_newpassword);
        update_pw = findViewById(R.id.update);


        //on click listener for update button
        update_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pw1 = new_pw_1.getText().toString();
                final String pw2 = new_pw_2.getText().toString();

                //check whether the entered passwords are empty or not
               if(!pw1.isEmpty() && !pw2.isEmpty()){

                   //check whether the entered passwords are same or not
                   if(pw1.equals(pw2)){
                       Response.Listener<String> stringListener = new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {

                               try {
                                   //get the value of the 'success' from the json object
                                   JSONObject jsonObjectRes =new JSONObject(response);
                                   boolean success = jsonObjectRes.getBoolean("success");

                                   //check whether the success is true or not
                                   if(success){
                                       Intent inthome = new Intent(Settings.this, Home.class);
                                       startActivity(inthome); //load home page
                                       finish();
                                   }
                                   else{

                                       //display error dialog box
                                       AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                                       builder.setMessage("Cannot change password.Please try again later!").setNegativeButton("Retry",null).create().show();
                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }

                           }
                       };

                       // send the username and password to the web server through SettignsRequest
                       SettignsRequest settignsRequest = new SettignsRequest(Username, pw1,stringListener);
                       RequestQueue requestQueue = Volley.newRequestQueue(Settings.this);
                       requestQueue.add(settignsRequest);
                   }
                   else{

                       //display error dialog box
                       AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                       builder.setMessage("Password Mismatch!").setNegativeButton("Retry",null).create().show();
                   }
               }
               else{
                   //display error dialog box
                   AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                   builder.setMessage("Please provide valid passwords").setNegativeButton("Ok",null).create().show();
               }
            }
        });


    }


}
