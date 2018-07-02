package com.example.app_pro.duan.com.duan.sensor;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.app_pro.duan.R;
import com.example.app_pro.duan.com.duan.google_map.ThongTinMapActivity;
import com.example.app_pro.duan.com.duan.database.MainActivity;
import com.example.app_pro.duan.com.duan.data.MyDatabase;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class ManHinhChuActivity extends AppCompatActivity implements OnMenuSelectedListener {
    final String DATABASE_NAME = "appnangcao.sqlite";
    SQLiteDatabase sqLiteDatabase;

    CircleMenu menu;
    public  static final int MAP=0,SENSOR=1,DATABASE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chu);
        init();
        menu.setMainMenu(Color.BLUE, R.drawable.icon_home, R.drawable.icon_neon);
        menu.addSubMenu(Color.CYAN, R.drawable.icon_map);//0
        menu.addSubMenu(Color.RED, R.drawable.icon_sensor);//1
        menu.addSubMenu(Color.GREEN, R.drawable.icon_database);//2
        menu.setOnMenuSelectedListener(this);
        
        addData();
    }

    private void addData() {
        setData();
    }

    private void setData() {
        Cursor cursor=sqLiteDatabase.rawQuery("select * from Checkin",null);
        for (int i = 0; i <cursor.getCount() ; i++) {
            cursor.moveToPosition(i);
            String khoa=cursor.getString(1);
            Toast.makeText(this, "address="+khoa, Toast.LENGTH_SHORT).show();

        }
    }

    private void init() {
        menu= (CircleMenu) findViewById(R.id.circleMenu);

        sqLiteDatabase = MyDatabase.initDatabase(ManHinhChuActivity.this, DATABASE_NAME);
    }

    @Override
    public void onMenuSelected(int i) {
        switch (i){
            case MAP:
                Intent intent=new Intent(ManHinhChuActivity.this,ThongTinMapActivity.class);
                startActivity(intent);
                break;
            case SENSOR:
                Intent intentOne=new Intent(ManHinhChuActivity.this,MySensor.class);
                startActivity(intentOne);
                break;
            case DATABASE:
                Intent intentTwo=new Intent(ManHinhChuActivity.this,MainActivity.class);
                startActivity(intentTwo);
                break;
        }
    }
}
