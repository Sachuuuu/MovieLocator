package com.example.movie;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest {
    private static final String DEL_URL = "https://segmented-dishes.000webhostapp.com/Connection/delete.php";
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
