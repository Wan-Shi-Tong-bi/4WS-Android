package com.example.g_intent_sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EditwitnessActivity extends AppCompatActivity {
    Zeuge z;
    int id;

    TextView currentVornameEditWitness;
    TextView currentNachnameEditWitness;
    TextView currentAlterEditWitness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editwitness);

        currentVornameEditWitness = findViewById(R.id.txtFieldVornameEditWitness);
        currentNachnameEditWitness = findViewById(R.id.txtFieldNachnameEditWitness);
        currentAlterEditWitness = findViewById(R.id.txtFieldAlterEditWitness);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        z = (Zeuge) b.getSerializable("zeuge");
        id = b.getInt("id");

        currentVornameEditWitness.setText(z.vorname);
        currentNachnameEditWitness.setText(z.nachnaname);
        currentAlterEditWitness.setText(z.alter+"");

    }

    public void editWitnessOnClick (final View source){
         z.setVorname(currentVornameEditWitness.getText().toString());
          z.setNachnaname(currentNachnameEditWitness.getText().toString());
          z.setAlter(Integer.parseInt(currentAlterEditWitness.getText().toString()));
          NewaccidentActivity.getInstance().editWitness(z, id);
          finish();
    }
}