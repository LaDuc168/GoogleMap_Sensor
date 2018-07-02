package com.example.app_pro.duan.com.duan.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app_pro.duan.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CheckinMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    TextView edtAddressCheckin;
    public static String ADDRESS="";
    public static double LAT=0.0;
    public static double LNG=0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_map);

        init();
        getData();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void init() {
        edtAddressCheckin=findViewById(R.id.edtAdressCheckin);
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
        LatLng sydney = new LatLng(LAT, LNG);
        mMap.addMarker(new MarkerOptions().position(sydney).title("I knit here").
                icon(BitmapDescriptorFactory.fromResource(R.drawable.nguoi_item)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,18));
    }

    public void getData() {
        ADDRESS=getIntent().getStringExtra("ADDRESS");
        LAT=getIntent().getDoubleExtra("LAT",0.0);
        LNG=getIntent().getDoubleExtra("LNG",0.0);
        edtAddressCheckin.setText("Address: "+ADDRESS);
    }
}
