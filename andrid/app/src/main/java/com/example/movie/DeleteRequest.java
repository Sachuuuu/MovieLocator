package com.example.movie;
/*
* DeleteRequest.java class
*
* This class is implemented to communicate with the online web server
* to delete some data from the DB using php
*
* */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest {
    private static final String DEL_URL = "https://segmented-dishes.000webhostapp.com/Connection/delete.php";   //Url for the php file
    private Map<String,String> params;

    public DeleteRequest(String uname, Response.Listener<String> listener){
        super(Method.POST,DEL_URL,listener,null);

        params = new HashMap<>();
        params.put("username",uname);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
