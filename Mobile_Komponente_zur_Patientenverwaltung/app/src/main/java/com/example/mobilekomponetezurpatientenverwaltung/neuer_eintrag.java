package com.example.mobilekomponetezurpatientenverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class neuer_eintrag extends AppCompatActivity {

    Button btnEintragtHinzufuegenNewEintrag;
    TextView txtViewNachrichtNewEintrag;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuer_eintrag);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        id = b.getInt("id");


        txtViewNachrichtNewEintrag = findViewById(R.id.txtViewNachrichtNewEintrag);
        btnEintragtHinzufuegenNewEintrag = findViewById(R.id.btnEintragtHinzufuegenNewEintrag);
        btnEintragtHinzufuegenNewEintrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEintragtHinzufuegenNewEintragOnAction();
            }
        });
    }

    public void btnEintragtHinzufuegenNewEintragOnAction() {
        ClientTaskNewPatient task = new ClientTaskNewPatient();
        task.execute("neweintrag", id + "", txtViewNachrichtNewEintrag.getText().toString());
        finish();
        patient_anzeigen.getInstance().startPatientTask();
    }
}