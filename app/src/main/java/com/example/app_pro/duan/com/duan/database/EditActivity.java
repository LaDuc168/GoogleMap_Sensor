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

public class EditActivity extends AppCompatActivity {
    EditText edtidedit, edtusernameedit, edtfirstnameedit, edtlastnameedit, edtpassedit;
    RadioButton radioNamedit, radioNuedit;
    Button btnThemedit, btnHuyedit;
    CheckBox checkBoxedit;
    String url = General.urlEdit;
    String male = "";
    String check = "";
    String maSV="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
        Intent intent = getIntent();
        SinhVien sv = (SinhVien) intent.getSerializableExtra("SV");
        maSV=sv.getId()+"";
        edtidedit.setText(sv.getId() + "");
        edtusernameedit.setText(sv.getName());
        edtfirstnameedit.setText(sv.getFirstname());
        edtlastnameedit.setText(sv.getLastname());
        edtpassedit.setText(sv.getPass());
        if (sv.getGender().equals("Male")) {
            radioNamedit.setChecked(true);
            radioNuedit.setChecked(false);
        } else {
            radioNamedit.setChecked(false);
            radioNuedit.setChecked(true);
        }
        if (sv.getStatus() == 1) {
            checkBoxedit.setChecked(true);
        } else {
            checkBoxedit.setChecked(false);
        }

        addEvent();
    }

    private void addEvent() {

        radioNamedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male = "Male";
                radioNuedit.setChecked(false);
            }
        });
        radioNuedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male = "Female";
                radioNamedit.setChecked(false);
            }
        });
        checkBoxedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = "1";
            }
        });
        btnHuyedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnThemedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma=edtpassedit.getText().toString();
                String ten=edtusernameedit.getText().toString();
                String firstname=edtfirstnameedit.getText().toString();
                String lastname=edtlastnameedit.getText().toString();
                String pass=edtpassedit.getText().toString();
                if(ma.equals("")|| ten.equals("")||firstname.equals("")
                        || pass.equals("")
                        )
                {
                    Toast.makeText(EditActivity.this, "Điền đầy đủ thông" +
                            " tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                POST_DATA(url);
            }
        });
    }

    private void init() {
        edtidedit = (EditText) findViewById(R.id.edtidedit);
        edtusernameedit = (EditText) findViewById(R.id.edtusernameedit);
        edtfirstnameedit = (EditText) findViewById(R.id.edtfirstnameedit);
        edtlastnameedit = (EditText) findViewById(R.id.edtlastnameedit);
        edtpassedit = (EditText) findViewById(R.id.edtpassedit);
        radioNamedit = (RadioButton) findViewById(R.id.radioNamedit);
        radioNuedit = (RadioButton) findViewById(R.id.radioNuedit);
        checkBoxedit = (CheckBox) findViewById(R.id.checkBoxedit);
        btnThemedit = (Button) findViewById(R.id.btnThemedit);
        btnHuyedit = (Button) findViewById(R.id.btnHuyedit);
    }

    public void POST_DATA(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("ok")) {
                    Toast.makeText(EditActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(EditActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(EditActivity.this, "Không thêm được", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Ids",maSV);
                map.put("Users", edtusernameedit.getText().toString());
                map.put("Daus", edtfirstnameedit.getText().toString());
                map.put("Cuois", edtlastnameedit.getText().toString());
                map.put("Giois", male);
                map.put("Mats", edtpassedit.getText().toString());
                map.put("Trangs", check);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
