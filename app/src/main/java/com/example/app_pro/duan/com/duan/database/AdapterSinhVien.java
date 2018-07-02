package com.example.app_pro.duan.com.duan.database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by APP-PRO on 12/3/2017.
 */

public class AdapterSinhVien extends BaseAdapter {
    Context myContext;
    ArrayList<SinhVien> arr;
    String url = General.urlXoa;

    public AdapterSinhVien(Context myContext, ArrayList<SinhVien> arr) {
        this.myContext = myContext;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.row_item_sinh_vien, null);
        TextView id = view.findViewById(R.id.txtid);
        TextView username = view.findViewById(R.id.txtusername);
        TextView name = view.findViewById(R.id.txtname);
        RadioButton genderNam = view.findViewById(R.id.radioNam);
        RadioButton genderNu = view.findViewById(R.id.radioNu);
        TextView pass = view.findViewById(R.id.txtpass);
        final SinhVien sv = arr.get(i);

        id.setText("ID: " + sv.getId() + "");
        username.setText("Username: " + sv.getName());
        name.setText("Their names: " + sv.getFirstname() + " " + sv.getLastname()
        );
        if (sv.getGender().equals("Female")) {
            genderNu.setChecked(true);
            genderNam.setChecked(false);
            genderNam.setEnabled(false);
        } else {
            genderNam.setChecked(true);
            genderNu.setChecked(false);
            genderNu.setEnabled(false);
        }
        pass.setText("Password: " + sv.getPass());

        Button btnSua = view.findViewById(R.id.btnSua);
        Button btnXoa = view.findViewById(R.id.btnXoa);
        //Sửa
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(myContext, EditActivity.class);
                intent.putExtra("SV",sv);
                myContext.startActivity(intent);
            }
        });
        //Xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(myContext);
                builder.setTitle("Dialog");
                builder.setMessage("Do you want to delete?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        POST_DATA(url,sv.getId()+"");

                    }
                });
//        Dialog hopThoai=builder.create();
                builder.show();
            }
        });
        return view;
    }
    public void POST_DATA(String url, final String ma) {
        RequestQueue requestQueue = Volley.newRequestQueue(myContext);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("ok")) {
                    Toast.makeText(myContext, "Deleted successfully", Toast.LENGTH_SHORT).show();
                    myContext.startActivity(new Intent(myContext, MainActivity.class));
                } else {
                    Toast.makeText(myContext, "Delete faild", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(myContext, "Erro", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> map=new HashMap<>();
                map.put("Idx",ma);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
