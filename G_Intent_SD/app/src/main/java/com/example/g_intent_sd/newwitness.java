package com.example.g_intent_sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class newwitness extends AppCompatActivity {
    TextView currentVornameNewWitness;
    TextView currentNachnameNewWitness;
    TextView currentAlterNewWitness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newwitness);

        currentVornameNewWitness = findViewById(R.id.txtFieldVornameNewWitness);
        currentNachnameNewWitness = findViewById(R.id.txtFieldNachnameNewWitness);
        currentAlterNewWitness = findViewById(R.id.txtFieldAlterNewWitness);
    }

    public void addNewWitnessOnClick(final View source){
          Zeuge z = new Zeuge(currentVornameNewWitness.getText().toString(), currentNachnameNewWitness.getText().toString(), Integer.parseInt(currentAlterNewWitness.getText().toString()));
          NewaccidentActivity.getInstance().addNewWitness(z);
          finish();
    }
}