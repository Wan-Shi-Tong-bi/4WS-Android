package com.example.mobilekomponetezurpatientenverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ListView lstViewPatienten;
    Button btnPatientHinzufuegen;
    ClientTaskNewPatient task;
    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstViewPatienten = findViewById(R.id.lstViewPatienten);
        lstViewPatienten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lstViewOnItemClicked(position);
            }
        });
        btnPatientHinzufuegen = findViewById(R.id.btnPatientHinzufuegen);
        btnPatientHinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPatientHinzufuegenOnClicked();
            }
        });
        instance = this;
       startTask();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public void lstViewOnItemClicked(int position) {
        stopTask();
        Intent intent = new Intent(this, patient_anzeigen.class);
        intent.putExtra("patient", (Serializable) lstViewPatienten.getAdapter().getItem(position));
        startActivity(intent);
    }

    public void btnPatientHinzufuegenOnClicked() {
        Intent intent = new Intent(this, neuerPatient.class);
        startActivity(intent);
    }

    public void startTask(){
        task = (ClientTaskNewPatient) new ClientTaskNewPatient(this).execute("updatestart");
    }

    public void stopTask(){
        task.setActivated(false);
        task.cancel(true);
    }

//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        startTask();
//        Toast.makeText(instance, "Resume", Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTask();
        Toast.makeText(instance, "Pause", Toast.LENGTH_SHORT).show();
    }
}