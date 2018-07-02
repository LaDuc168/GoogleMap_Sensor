package com.example.app_pro.duan.com.duan.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_pro.duan.R;
import com.example.app_pro.duan.com.duan.sensor.General;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AdapterSinhVien adapterSinhVien;
    ArrayList<SinhVien> listSinhVien;
    String url= General.urlCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        adapterSinhVien=new AdapterSinhVien(MainActivity.this,listSinhVien);
        listView.setAdapter(adapterSinhVien);
        getJSON(url);
        addEvent();
    }

    private void addEvent() {
    }

    public void getJSON(String url) {

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject obj=response.getJSONObject(i);
                                listSinhVien.add(new SinhVien(
                                        obj.getInt("id"),
                                        obj.getString("name"),
                                        obj.getString("firstname"),
                                        obj.getString("lastname"),
                                        obj.getString("gender"),
                                        obj.getString("pass"),
                                        obj.getInt("status")) );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterSinhVien.notifyDataSetChanged();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
//        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.menuadd)
        {
            Intent intent=new Intent(MainActivity.this,Add_Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void init() {
        listSinhVien=new ArrayList<>();
        listView= (ListView) findViewById(R.id.listView);

    }
}
