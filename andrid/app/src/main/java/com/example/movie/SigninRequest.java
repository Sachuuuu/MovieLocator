package com.example.movie;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SigninRequest extends StringRequest {
    private static final String SIGN_URL = "https://segmented-dishes.000webhostapp.com/Connection/login.php";
    private Map<String,String> params;

    public SigninRequest(String uname, String pword, Response.Listener<String> listener){
        super(Method.POST,SIGN_URL,listener,null);

        params = new HashMap<>();
        params.put("username",uname);
        params.put("password",pword);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
