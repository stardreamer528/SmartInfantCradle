package com.paneerbuttermasala.studios.smartinfantcradle;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterMemories extends ArrayAdapter<List_data> {
    ArrayList<List_data> listdata;
    Context context;
    int resource;
    public MyAdapterMemories(Context context, int resource,ArrayList<List_data> listdata) {
        super(context, resource, listdata);
        this.listdata=listdata;
        this.context=context;
        this.resource=resource;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.memories_grid_list,null,true);
        }
        List_data listdata = getItem(position);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView5);
        Picasso.with(context)
                .load(listdata.getImageurl())
                .into(img);


        return convertView;
    }
}