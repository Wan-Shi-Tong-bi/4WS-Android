package com.example.g_intent_sd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterWitness extends BaseAdapter {

    private ArrayList<Zeuge> items = new ArrayList<>();
    private int layoutId;
    private LayoutInflater inflater;

    public MyAdapterWitness(Context ctx, int layoutId, ArrayList<Zeuge> items) {
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
        Zeuge z = items.get(position);
        final View listItem = (convertView == null) ? inflater.inflate(this.layoutId, null) : convertView;
        ((TextView) listItem.findViewById(R.id.txtViewWitnessListLayout)).setText(z.toString());
        ImageView deleteImg = (ImageView) listItem.findViewById(R.id.imgViewRemoveWitness);
        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    NewaccidentActivity.getInstance().removeWitness(position);
                } catch (NullPointerException e) {

                }
            }
        });
        ImageView editImg = (ImageView) listItem.findViewById(R.id.imgViewEditWitness);
        editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    NewaccidentActivity.getInstance().switchWindowEditWitness(position);
                } catch (NullPointerException e) {

                }

            }
        });
        return listItem;
    }
}
