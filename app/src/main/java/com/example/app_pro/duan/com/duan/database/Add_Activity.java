package com.example.app_pro.duan.com.duan.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_pro.duan.R;
import com.example.app_pro.duan.com.duan.sensor.General;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Add_Activity extends AppCompatActivity {

    EditText edtid,edtusername, edtfirstname, edtlastname, edtpass;
    RadioButton radioNamadd, radioNuadd;
    Button btnThem, btnHuy;
    CheckBox checkBox;
    String male = "Male";
    String ck = "0";
    String url= General.urlAdd;
    String urlKiemTraMaTrung=General.urlKiemTrama;
    boolean checkMa=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);
        init();
        addEvent();
    }

    private void addEvent() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maid=edtid.getText().toString();
                String ten=edtusername.getText().toString();
                String firstname=edtfirstname.getText().toString();
                String lastname=edtlastname.getText().toString();
                String pass=edtpass.getText().toString();
                if(maid.equals("")|| ten.equals("")||firstname.equals("")
                        || pass.equals("")
                        )
                {
                    Toast.makeText(Add_Activity.this, "Điền đầy đủ thông" +
                            " tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                KiemTraMaTrung(urlKiemTraMaTrung,maid,url);


//                Toast.makeText(Add_Activity.this, "Check="+male, Toast.LENGTH_SHORT).show();
            }
        });
        radioNamadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male = "Male";
                radioNuadd.setChecked(false);
            }
        });
        radioNuadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male = "Female";
                radioNamadd.setChecked(false);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ck = "1";
            }
        });
    }

    public void POST_DATA(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("ok")) {
                    Toast.makeText(Add_Activity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Add_Activity.this,MainActivity.class));
                } else {
                    Toast.makeText(Add_Activity.this, "Không thêm được", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Add_Activity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                Random x=new Random();
                map.put("Id",edtid.getText().toString());
                map.put("User", edtusername.getText().toString());
                map.put("Dau", edtfirstname.getText().toString());
                map.put("Cuoi", edtlastname.getText().toString());
                map.put("Gioi", male);
                map.put("Mat", edtpass.getText().toString());
                map.put("Trang", ck);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    //Kiem tra ma trung
    public void KiemTraMaTrung(String url, final String matrung, final String urladd) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(Add_Activity.this, "Ma="+response.toString(), Toast.LENGTH_SHORT).show();
                if (response.trim().equals("trung")) {
                    Toast.makeText(Add_Activity.this, "Mã bị trùng", Toast.LENGTH_SHORT).show();
                    checkMa=false;
                    //Toast.makeText(Add_Activity.this, "Check="+checkMa, Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(Add_Activity.this,MainActivity.class));
                }else{
                    POST_DATA(urladd);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Add_Activity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("mauser",matrung);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init() {
        edtid= (EditText) findViewById(R.id.edtid);
        edtusername = (EditText) findViewById(R.id.edtusername);
        edtfirstname = (EditText) findViewById(R.id.edtfirstname);
        edtlastname = (EditText) findViewById(R.id.edtlastname);
        edtpass = (EditText) findViewById(R.id.edtpass);
        radioNamadd = (RadioButton) findViewById(R.id.radioNamadd);
        radioNuadd = (RadioButton) findViewById(R.id.radioNuadd);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnHuy = (Button) findViewById(R.id.btnHuy);
    }
}
