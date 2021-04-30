package com.example.g_intent_sd;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<Unfall> items = new ArrayList<>();
    private int layoutId;
    private LayoutInflater inflater;

    public MyAdapter(Context ctx, int layoutId, ArrayList<Unfall>items) {
        this.items = items;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Unfall u = items.get(position);
        final View listItem = (convertView == null) ? inflater.inflate(this.layoutId, null) : convertView;
        ((TextView)listItem.findViewById(R.id.txtViewListInfo)).setText(u.toString());
        ((CheckBox)listItem.findViewById(R.id.checkBoxVerletzteList)).setChecked(u.verlezte);
        ((CheckBox)listItem.findViewById(R.id.checkBoxSaschaedenList)).setChecked(u.andereSchaeden);
        ImageView deleteImg= (ImageView)listItem.findViewById(R.id.btnDelete);
        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.getInstance().deleteFile(position, u.id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        ImageView showImg= (ImageView)listItem.findViewById(R.id.btnEdit);
        showImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().switchWindow(position);
            }
        });
        return listItem;
    }
}
