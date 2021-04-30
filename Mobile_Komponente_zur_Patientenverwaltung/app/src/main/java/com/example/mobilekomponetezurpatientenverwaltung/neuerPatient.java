package com.example.mobilekomponetezurpatientenverwaltung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class neuerPatient extends AppCompatActivity {

    TextView txtViewVornameNew;
    TextView txtViewNachnameNew;
    Button btnPatientHinzufuegenNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuer_patient);

        txtViewVornameNew = findViewById(R.id.txtViewVornameNew);
        txtViewNachnameNew = findViewById(R.id.txtViewNachnameNew);
        btnPatientHinzufuegenNew = findViewById(R.id.btnPatientHinzufuegenNew);
        btnPatientHinzufuegenNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPatientHinzufuegenNewOnAction();
            }
        });

    }

    public void btnPatientHinzufuegenNewOnAction(){
        ClientTaskNewPatient tnewpatient = new ClientTaskNewPatient();
        tnewpatient.execute("newpatient", txtViewVornameNew.getText().toString(), txtViewNachnameNew.getText().toString());
        MainActivity.getInstance().startTask();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MainActivity.getInstance().startTask();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        MainActivity.getInstance().startTask();
    }
}