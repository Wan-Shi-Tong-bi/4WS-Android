package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements green_fragment.OnSelectionChangedListener {
    public static MainActivity instance;
    blue_fragment blue;
    boolean showright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.acticity_main);
        }catch (Exception e){
            setContentView(R.layout.activity_main);
        }

        instance = this;
         showright = false;
        initializeView();
    }

    public void initializeView() {
        blue = (blue_fragment) getSupportFragmentManager().findFragmentById(R.id.fragment_blue);
        showright = blue != null && blue.isInLayout();
    }


    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public void onSelectionChanged(String message) {
        if (showright){
            blue.show(message);
        }else {
            newActivity(message);
        }

    }

    public void newActivity(String message){
        Intent i = new Intent(this, blue_activity.class);
        i.putExtra("message", message);
        startActivity(i);
    }


}