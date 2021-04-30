package com.example.mobilekomponetezurpatientenverwaltung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class patient_anzeigen extends AppCompatActivity {

    TextView txtViewVornameShow;
    TextView txtViewNachnameShow;
    ListView lstViewEintraegeShow;
    Button btnPatientHinzufuegenShow;
    Patient p;
    ClientTaskNewPatient task;
    public static patient_anzeigen instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_anzeigen);
        txtViewVornameShow = findViewById(R.id.txtViewVornameShow);
        txtViewNachnameShow = findViewById(R.id.txtViewNachnameShow);
        lstViewEintraegeShow = findViewById(R.id.lstViewEintraegeShow);
        btnPatientHinzufuegenShow = findViewById(R.id.btnPatientHinzufuegenShow);
        instance = this;
        btnPatientHinzufuegenShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPatientHinzufuegenShowOnAction();
            }
        });

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        p = (Patient) b.getSerializable("patient");

        txtViewVornameShow.setText(p.vorname);
        txtViewNachnameShow.setText(p.nachname);

        ArrayAdapter<Eintrag> arrAdapterEintrag = new ArrayAdapter<Eintrag>(this, android.R.layout.simple_list_item_1, p.getEinträge());
        lstViewEintraegeShow.setAdapter(arrAdapterEintrag);
        startPatientTask();

    }


    public static patient_anzeigen getInstance() {
        return instance;
    }


    public void btnPatientHinzufuegenShowOnAction() {
        Intent intent = new Intent(this, neuer_eintrag.class);
        intent.putExtra("id", p.id);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        MainActivity.getInstance().startTask();
        return super.onOptionsItemSelected(item);
    }

    public void stopPatientTask() {
        task.setActivated2(false);
        task.cancel(true);
    }

    public void startPatientTask() {
        task = (ClientTaskNewPatient) new ClientTaskNewPatient(this).execute("updatepatientalways", p.id + "");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        MainActivity.getInstance().startTask();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPatientTask();
    }
}