package com.example.movie;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<ListItem> listItems;
    private static final String URL_DATA = "https://segmented-dishes.000webhostapp.com/Movie.json";
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems =new ArrayList<>();

        loadRecyleViewData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.about) {
            Intent intabout = new Intent(Home.this, About.class);
            startActivity(intabout);

        } else if (id == R.id.location) {
            Intent intmap = new Intent(Home.this,MapsActivity.class);
            startActivity(intmap);
        } else if (id == R.id.settings) {
            Intent intsettings = new Intent(Home.this, Settings.class);
            startActivity(intsettings);

        } else if (id == R.id.logout) {
            Intent intlogout = new Intent(Home.this,MainActivity.class);
            startActivity(intlogout);
            finish();

        }else if(id == R.id.delete){
            deleteUser();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void deleteUser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Confirm");
        builder.setMessage("You are about to delete "+MainActivity.global_username+" user.Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Response.Listener<String> stringListener =new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObjectRes =new JSONObject(response);
                            boolean success = jsonObjectRes.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(Home.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                                builder.setMessage("Failed to delete user!").setNegativeButton("Retry",null).create().show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                DeleteRequest deleteRequest = new DeleteRequest(MainActivity.global_username ,stringListener);
                RequestQueue requestQueue = Volley.newRequestQueue(Home.this);
                requestQueue.add(deleteRequest);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've changed your mind to delete all records", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    private void loadRecyleViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Movies");

                    for(int i=0;i<array.length();i++){
                        JSONObject o = array.getJSONObject(i);
                        ListItem item = new ListItem(
                                o.getString("name"),
                                o.getString("cinemas"),
                                o.getString("image")
                        );

                        listItems.add(item);
                    }
                    adapter = new MyAdapter(listItems, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
