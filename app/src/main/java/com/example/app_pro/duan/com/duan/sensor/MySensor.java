package com.example.app_pro.duan.com.duan.sensor;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_pro.duan.R;

import java.util.ArrayList;
import java.util.List;

public class MySensor extends AppCompatActivity implements SensorEventListener {


    private SensorManager sensorManager;
    private boolean isColor = false;
    private TextView view;
    private long lastUpdate;
//ListView
    ListView listViewSenSor;
    ArrayAdapter adapter;
    ArrayList<String > arrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        init();
        addData();
        view.setBackgroundColor(Color.GREEN);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
    }

    private void addData() {
        for (int i = 0; i <10 ; i++) {
            arrayList.add("Item "+(i+1));
        }
        adapter.notifyDataSetChanged();
    }

    private void init() {
        view = (TextView) findViewById(R.id.txtSensoritem);
        listViewSenSor= (ListView) findViewById(R.id.listViewSensor);
        arrayList=new ArrayList<>();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listViewSenSor.setAdapter(adapter);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                listViewSenSor.setBackgroundColor(Color.BLUE);
//                view.setBackgroundColor(Color.GREEN);
//                view.setTextColor(Color.RED);
//                view.setText("Change the background to blue, red");


            } else {
                listViewSenSor.setBackgroundColor(Color.WHITE);
//                view.setBackgroundColor(Color.RED);
//                view.setTextColor(Color.WHITE);
//                view.setText("Change the background to red, white");
            }
            isColor = !isColor;
        }
    }
}
