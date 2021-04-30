package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class blue_activity extends AppCompatActivity {

    Button b;
    static blue_activity instace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_activity);
        instace = this;
        b = findViewById(R.id.btnZurück);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int orientation = getResources().getConfiguration().orientation;
        if (orientation != Configuration.ORIENTATION_PORTRAIT){
            finish();
            return;
        }
        Intent i = getIntent();
        if ( i==null) return;
        blue_fragment bF = (blue_fragment) getSupportFragmentManager().findFragmentById(R.id.fragment_blue);
        String message = i.getStringExtra("message");
        bF.show(message);
    }

    public static blue_activity getInstace() {
        return instace;
    }
}