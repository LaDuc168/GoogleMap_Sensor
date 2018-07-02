package com.example.app_pro.duan.com.duan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_pro.duan.R;
import com.example.app_pro.duan.com.duan.data.Chẹckin;

import java.util.ArrayList;

/**
 * Created by LaVanDuc on 1/15/2018.
 */

public class CheckinAdapter extends BaseAdapter {
    Context myContext;
    ArrayList<Chẹckin> arrayList;

    public CheckinAdapter(Context myContext, ArrayList<Chẹckin> arrayList) {
        this.myContext = myContext;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.row_checkin_item,null);
        TextView txtaddress=view.findViewById(R.id.txtnameAddress);
        TextView txtlat=view.findViewById(R.id.txtLat);
        TextView txtLng=view.findViewById(R.id.txtLng);

        Chẹckin chẹckin=arrayList.get(i);
        txtaddress.setText("Address: "+chẹckin.getAddress());
        txtlat.setText("Lat: "+chẹckin.getLat());
        txtLng.setText("Lng: "+chẹckin.getLng());


        Animation animation= AnimationUtils.loadAnimation(myContext,R.anim.anim_listview);
        view.startAnimation(animation);
        return view;
    }
}
