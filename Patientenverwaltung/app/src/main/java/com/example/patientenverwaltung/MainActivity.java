package com.example.patientenverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
HashMap <Integer, Patient> h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        h  = new HashMap<>();
        h.put(1, new Patient("Benedikt", "Gaubinger"));
        h.put(2, new Patient("Mathias", "Hamedinger"));
        h.put(4, new Patient("Andreas", "Pilger"));
    }

    public void onClickb1 (final View source) {
        TextView textVorname = findViewById(R.id.vname);
        TextView textNachname = findViewById(R.id.nname);
        EditText number = findViewById(R.id.iDPatient);

        Patient current = h.get(Integer.parseInt(number.getText().toString()));
        if (current==null){
            Toast t = Toast.makeText(this, "ID existiert nicht...",Toast.LENGTH_SHORT);
            t.show();
        }else {
            textVorname.setText(current.vorname);
            textNachname.setText(current.nachname);
        }

    }
    }
