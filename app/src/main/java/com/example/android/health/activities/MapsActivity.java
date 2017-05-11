package com.example.android.health.activities;

import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android.health.R;
import com.example.android.health.adapter.MyorderData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String[] data = {"CARE Hospitals, Nagpur","Center Point Hospital","Meditrina Institute Of Medical Sciences"
            ,"Orange City Hospital & Research Institute","KRIMS Hospital","Jasleen Hospital","Kothari Hospital","Chintamani Skin & Cosmetic Clinic"
    ,"Mamta Hospital","Treat Me Hospital","Arogyam Superspeciality Hospital"};
    Double lat,lon;
    Double[] latitude={21.125566,21.146207,21.112865,21.133279,21.133059,21.138317,21.114581,21.135597,21.093370,21.115829,21.111612};
    Double[] longitude={79.072188,79.100,79.065511,79.07124,79.070425,79.079718,79.071,79.078837,79.065238,79.071839,79.070617};
    //boolean isSelectSpecilist = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        if(isSelectSpecilist) {
//            Bundle getdata = getIntent().getExtras();
//            Log.e(">>>DATA", String.valueOf(getdata));
//            data = getdata.getString("KEY_ADDRESS");
//            Toast.makeText(MapsActivity.this, data, Toast.LENGTH_SHORT).show();

//            isSelectSpecilist=true;
//        }else {
//            isSelectSpecilist=false;

       // }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Geocoder coder = new Geocoder(this);
        List<android.location.Address> address;


        try {

            address=coder.getFromLocationName(data[0],5);

            android.location.Address location=address.get(0);
             lat=location.getLatitude();
             lon=location.getLongitude();

            Toast.makeText(MapsActivity.this, String.valueOf(lat), Toast.LENGTH_SHORT).show();


        } catch (IOException e) {
            e.printStackTrace();
        }
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
        for(int i=0;i<data.length;i++){
//            if (i==0){
//                LatLng sydney = new LatLng(lat, lon);
//                mMap.addMarker(new MarkerOptions().position(sydney).title(data[0])).showInfoWindow();
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//            }
                LatLng hospital=new LatLng(latitude[i],longitude[i]);
                mMap.addMarker(new MarkerOptions().position(hospital).title(data[i])).showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(hospital));



        }



    }
}

