package com.example.g_intent_sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class editaccident extends AppCompatActivity {

    TextView currentDatumEdit;
    TextView currentUhrzeitEdit;
    TextView currentStrasseEdit;
    TextView currentHausnummerEdit;
    TextView currentPLZEdit;
    TextView currentOrtEdit;
    CheckBox currentVerlezteEdit;
    CheckBox currentAndereSchaedenEdit;
    ArrayList <Zeuge> zeugenarr;
    ListView zeugenliste;
    MyAdapterWitness myAdapter;
    Unfall u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaccident);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        u = (Unfall) b.getSerializable("unfall");

        currentDatumEdit = findViewById(R.id.txtFieldDateEdit);
        currentUhrzeitEdit = findViewById(R.id.txtFieldUhrzeitEdit);
        currentStrasseEdit = findViewById(R.id.txtFieldStrasseEdit);
        currentHausnummerEdit = findViewById(R.id.txtFieldHausnrEdit);
        currentPLZEdit = findViewById(R.id.txtFieldPlzEdit);
        currentOrtEdit = findViewById(R.id.txtFieldOrtEdit);
        currentVerlezteEdit = findViewById(R.id.checkBoxVerlezteEdit);
        currentAndereSchaedenEdit = findViewById(R.id.checkBoxAndereSachschädenEdit);
        zeugenliste = findViewById(R.id.lstViewWitnessEditAccident);
        zeugenarr = u.zeugen;

        //Views werden gesezt
        currentDatumEdit.setText(u.datum);
        currentUhrzeitEdit.setText(u.uhrzeit);
        currentStrasseEdit.setText(u.strasse);
        currentHausnummerEdit.setText(""+u.hausnummer);
        currentPLZEdit.setText(""+u.plz);
        currentOrtEdit.setText(u.ort);
        currentVerlezteEdit.setChecked(u.verlezte);
        currentAndereSchaedenEdit.setChecked(u.andereSchaeden);
        bindAdapterToList();
    }

    public void editAccidentOnClick (final View source) throws IOException {
        Unfall uNeu = new Unfall(currentDatumEdit.getText().toString(), currentUhrzeitEdit.getText().toString(), currentStrasseEdit.getText().toString(),
                Integer.parseInt(currentHausnummerEdit.getText().toString()), Integer.parseInt(currentPLZEdit.getText().toString()), currentOrtEdit.getText().toString(),
                currentVerlezteEdit.isChecked(), currentAndereSchaedenEdit.isChecked(), u.id, u.zeugen);

        FileOutputStream fos = openFileOutput("unfall" + u.id, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(uNeu);
        oos.close();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void bindAdapterToList(){
        myAdapter = new MyAdapterWitness(this, R.layout.listlayoutwitness, zeugenarr);
        zeugenliste.setAdapter(myAdapter);
    }


}