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
import com.example.app_pro.duan.com.duan.data.FindPath;

import java.util.ArrayList;

/**
 * Created by LaVanDuc on 1/15/2018.
 */

public class FindPathAdapter extends BaseAdapter {
    Context myContext;
    ArrayList<FindPath> arrayList;

    public FindPathAdapter(Context myContext, ArrayList<FindPath> arrayList) {
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
        view=inflater.inflate(R.layout.row_findpath_item,null);

        TextView txtStartAddress=view.findViewById(R.id.txtstartaddress);
        TextView txtStartLat=view.findViewById(R.id.txtstartlat);
        TextView txtStartLng=view.findViewById(R.id.txtstartlng);

        TextView txtEndAddress=view.findViewById(R.id.txtendaddress);
        TextView txtEndLat=view.findViewById(R.id.txtendlat);
        TextView txtEndLng=view.findViewById(R.id.txtendlng);


        FindPath findPath=arrayList.get(i);
        txtStartAddress.setText("Beginning Points: "+findPath.getStartAddress());
        txtStartLat.setText("Lat: "+findPath.getStartLat());
        txtStartLng.setText("Lng: "+findPath.getStartLng());

        txtEndAddress.setText("Destination: "+findPath.getEndAddress());
        txtEndLat.setText("Lat: "+findPath.getEndLat());
        txtEndLng.setText("Lng: "+findPath.getEndLng());

        Animation animation= AnimationUtils.loadAnimation(myContext,R.anim.anim_listview);
        view.startAnimation(animation);

        return view;
    }
}
