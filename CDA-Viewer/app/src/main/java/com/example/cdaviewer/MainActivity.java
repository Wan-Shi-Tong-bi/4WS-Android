package com.example.cdaviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ListView lstViewDatein;
    ArrayAdapter<String> adapter;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstViewDatein = findViewById(R.id.lstViewDatein);
        lstViewDatein.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick((String) lstViewDatein.getAdapter().getItem(position));
            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        path = "xmlfiles";
        try {
            String[] files = getAssets().list(path);
            for (int i = 0; i <= files.length; i++) {
                adapter.add(files[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        lstViewDatein.setAdapter(adapter);
    }

    public void onClick(String filename) {
        Intent intent = new Intent(this, showPatient.class);
        intent.putExtra("filename", filename);
        intent.putExtra("path", path);
        startActivity(intent);
    }
}