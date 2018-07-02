package com.example.app_pro.duan.com.duan.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_pro.duan.R;
import com.example.app_pro.duan.com.duan.data.MyDatabase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class CheckinMapsUpdateActivity extends FragmentActivity implements
        OnMapReadyCallback {

    private GoogleMap mMap;

    private static double LAT = 0.0;
    private static double LNG = 0.0;

    EditText edtCheckin;
    Button btnSave;

    //Database
    final String DATABASE_NAME = "appnangcao.sqlite";
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_maps_update);
        init();
        getData();

        addEvent();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void addEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = edtCheckin.getText().toString().trim();
                if (address.equals("")) {
                    Toast.makeText(CheckinMapsUpdateActivity.this, "Please enter name address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Random random = new Random();
                int index = 1 + random.nextInt(780);
                String MA = "00" + index;
                ContentValues contentValues = new ContentValues();
                contentValues.put("ma", MA);
                contentValues.put("address", address);
                contentValues.put("lat", LAT);
                contentValues.put("lng", LNG);

                long check = sqLiteDatabase.insert("Checkin", null, contentValues);
                if (check > 0) {
                    Toast.makeText(CheckinMapsUpdateActivity.this, "Save checkin success!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CheckinMapsUpdateActivity.this, CheckinActivity.class));
                    finish();
                }else{
                    Toast.makeText(CheckinMapsUpdateActivity.this, "Save to faild!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        edtCheckin = findViewById(R.id.edtAdressCheckinUpdate);
        btnSave = findViewById(R.id.btnSaveCheckin);
        sqLiteDatabase = MyDatabase.initDatabase(CheckinMapsUpdateActivity.this, DATABASE_NAME);
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menusave:
                Toast.makeText(this, "Ok save", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getData() {
        LAT = getIntent().getDoubleExtra("LAT", 0.0);
        LNG = getIntent().getDoubleExtra("LNG", 0.0);
    }
}
