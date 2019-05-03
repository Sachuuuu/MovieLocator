package com.example.movie;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng savoy = new LatLng(6.879286, 79.859589);
        LatLng liberty = new LatLng(6.912652, 79.851174);
        LatLng ccc = new LatLng(6.918168,  79.855602);
        mMap.addMarker(new MarkerOptions().position(savoy).title("Savoy3D"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(savoy));

        mMap.addMarker(new MarkerOptions().position(liberty).title("Liberty Cinema"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(liberty));

        mMap.addMarker(new MarkerOptions().position(ccc).title("Scope Cinema - CCC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ccc));
    }
}
