package com.example.candidascore;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter  {
    private ArrayList<Buttons> items = new ArrayList<>();
    private int layoutId;
    private LayoutInflater inflater;

    public MyAdapter(Context ctx, int layoutId, ArrayList<Buttons>items) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Buttons b = items.get(position);
        final View listItem = (convertView == null) ? inflater.inflate(this.layoutId, null) : convertView;
        ((TextView)listItem.findViewById(R.id.textItem)).setText("Patient "+position);
        ((CheckBox)listItem.findViewById(R.id.checkBoxItem)).setChecked(b.needTherapy);

        ImageView deleteImg= (ImageView)listItem.findViewById(R.id.deletePic);
        deleteImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().remove(position);
            }
        });
        ImageView showImg= (ImageView)listItem.findViewById(R.id.showPic);
        showImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().showinfo(position);
            }
        });
        return listItem;
    }

}
