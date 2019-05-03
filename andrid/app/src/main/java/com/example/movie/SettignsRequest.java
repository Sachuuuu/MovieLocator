
package com.example.movie;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SettignsRequest extends StringRequest {

    private static final String UPD_URL = "https://segmented-dishes.000webhostapp.com/Connection/update.php";
    private Map<String,String> params;

    public SettignsRequest(String uname, String pword, Response.Listener<String> listener){
        super(Method.POST,UPD_URL,listener,null);

        params = new HashMap<>();
        params.put("username",uname);
        params.put("password",pword);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
