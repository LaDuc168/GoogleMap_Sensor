package com.example.app_pro.duan.com.duan.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.app_pro.duan.R;
import com.example.app_pro.duan.com.duan.adapter.FindPathAdapter;
import com.example.app_pro.duan.com.duan.google_map.ThongTinMapActivity;
import com.example.app_pro.duan.com.duan.data.FindPath;
import com.example.app_pro.duan.com.duan.data.MyDatabase;

import java.util.ArrayList;

public class FindPathActivity extends AppCompatActivity
        implements SensorEventListener {
    ListView listView;
    FindPathAdapter adapter;
    ArrayList<FindPath> arrayList;

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
        setContentView(R.layout.activity_find_path);
        init();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
        addData();

        addEvent();
    }

    private void addEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(FindPathActivity.this, ThongTinMapActivity.class);
                intent.putExtra("START_ADDRESS",arrayList.get(i).getStartAddress());
                intent.putExtra("END_ADDRESS",arrayList.get(i).getEndAddress());
                startActivity(intent);
            }
        });
    }

    private void addData() {
        setData();
    }

    private void setData() {
        arrayList.clear();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Findpath", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String id = cursor.getString(0);
            String startaddress = cursor.getString(1);
            double startlat = cursor.getDouble(2);
            double startlng = cursor.getDouble(3);

            String endaddress = cursor.getString(4);
            double endlat = cursor.getDouble(5);
            double endlng = cursor.getDouble(6);
            FindPath findPath = new FindPath(id, startaddress, startlat, startlng, endaddress, endlat, endlng);
            arrayList.add(findPath);
        }
        adapter.notifyDataSetChanged();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.listViewFindPath);
        arrayList = new ArrayList<>();
        adapter = new FindPathAdapter(this, arrayList);
        listView.setAdapter(adapter);

        sqLiteDatabase = MyDatabase.initDatabase(FindPathActivity.this, DATABASE_NAME);
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
                listView.setBackgroundColor(Color.YELLOW);
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
