package com.example.app_pro.duan.com.duan.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.app_pro.duan.R;
import com.example.app_pro.duan.com.duan.adapter.CheckinAdapter;
import com.example.app_pro.duan.com.duan.data.Chẹckin;
import com.example.app_pro.duan.com.duan.data.MyDatabase;

import java.util.ArrayList;

public class CheckinActivity extends AppCompatActivity implements SensorEventListener {

    ListView listView;
    CheckinAdapter adapter;
    ArrayList<Chẹckin> arrayList;
    //Sensor
    private SensorManager sensorManager;
    private boolean isColor = false;
    private long lastUpdate;


    //Databse
    final String DATABASE_NAME = "appnangcao.sqlite";
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        init();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
        addData();

        addEvent();
    }

    private void addEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckinActivity.this);
                builder.setTitle("Confirm");
                builder.setMessage("Would you like to show up google map?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CheckinActivity.this, CheckinMapActivity.class);
                        intent.putExtra("ADDRESS", arrayList.get(i).getAddress());
                        intent.putExtra("LAT", arrayList.get(i).getLat());
                        intent.putExtra("LNG", arrayList.get(i).getLng());
                        startActivity(intent);
                    }
                });
                builder.show();


            }
        });
    }

    private void addData() {
        setData();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.listViewCheckin);
        arrayList = new ArrayList<>();
        adapter = new CheckinAdapter(this, arrayList);
        listView.setAdapter(adapter);

        sqLiteDatabase = MyDatabase.initDatabase(CheckinActivity.this, DATABASE_NAME);
    }

    private void setData() {
        arrayList.clear();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Checkin", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String ma = cursor.getString(0);
            String address = cursor.getString(1);
            double lat = cursor.getDouble(2);
            double lng = cursor.getDouble(3);
            Chẹckin chẹckin = new Chẹckin(ma, address, lat, lng);
            arrayList.add(chẹckin);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_danhsach_duong, menu);
        getMenuInflater().inflate(R.menu.menu_checkin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menudanhsachduong:
                startActivity(new Intent(CheckinActivity.this, FindPathActivity.class));
                break;

            case R.id.menucheckin:
                startActivity(new Intent(CheckinActivity.this, ScreenCheckinActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(sensorEvent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
        sensorManager.registerListener((SensorEventListener) this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }



    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
// Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();

        if (accelationSquareRoot >= 2)
        {

            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            if (isColor) {
                listView.setBackgroundColor(Color.GREEN);
//                view.setBackgroundColor(Color.GREEN);
//                view.setTextColor(Color.RED);
//                view.setText("Change the background to blue, red");


            } else {
                listView.setBackgroundColor(Color.WHITE);
//                view.setBackgroundColor(Color.RED);
//                view.setTextColor(Color.WHITE);
//                view.setText("Change the background to red, white");
            }
            isColor = !isColor;
        }
    }
}
