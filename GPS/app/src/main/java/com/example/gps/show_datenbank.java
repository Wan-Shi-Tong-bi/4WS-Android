package com.example.gps;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gps.database.MySqLiteHelper;
import com.example.gps.model.Standort;

import java.util.ArrayList;

public class show_datenbank extends AppCompatActivity {

    ListView listViewStandorte;
    ArrayAdapter<String> mAdapter;
    ArrayList<Standort> standorte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_datenbank);

        listViewStandorte = findViewById(R.id.lstViewEinträgeShow);
        standorte = new ArrayList<Standort>();

        loadData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void loadData(){
        MySqLiteHelper dbHelper = new MySqLiteHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor rows = db.rawQuery("select * from GPSDaten", null);
        while (rows.moveToNext()){
            Standort s = new Standort();
            s.setLongitude(rows.getString(1));
            s.setLatitude(rows.getString(2));
            s.setDate(rows.getString(3));
            standorte.add(s);
        }
        rows.close();
        db.close();
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, standorte);
        listViewStandorte.setAdapter(mAdapter);
    }
}