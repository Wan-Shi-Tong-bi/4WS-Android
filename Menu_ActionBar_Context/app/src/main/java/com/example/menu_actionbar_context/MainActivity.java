package com.example.menu_actionbar_context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.VersionInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtViewDatumNew;
    CheckBox checkBoxDatumNew;
    TextView txtViewUhrzeitNew;
    CheckBox checkBoxUhrezitNew;
    TextView txtViewHerstellerNew;
    TextView txtViewProduktNew;
    TextView txtViewMengeNew;
    ListView lstViewEinträgeNew;
    TextView txtViewÜberschriftNew;
    ArrayList<Medikament> arrListMedikamente;
    ArrayAdapter<Medikament> arrAdapterMedikament;
    boolean edit;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenzierung
        txtViewDatumNew = findViewById(R.id.txtViewDatumNew);
        checkBoxDatumNew = findViewById(R.id.checkBoxDatumNew);
        checkBoxDatumNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxDatumNew.isChecked()) {
                    txtViewDatumNew.setEnabled(false);
                    txtViewDatumNew.setText(LocalDate.now().toString());
                } else {
                    txtViewDatumNew.setEnabled(true);
                    txtViewDatumNew.setText("");
                }
            }
        });
        txtViewUhrzeitNew = findViewById(R.id.txtViewUhrzeitNew);
        checkBoxUhrezitNew = findViewById(R.id.checkBoxUhrzeitNew);
        checkBoxUhrezitNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxUhrezitNew.isChecked()) {
                    txtViewUhrzeitNew.setEnabled(false);
                    txtViewUhrzeitNew.setText(LocalTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
                } else {
                    txtViewUhrzeitNew.setEnabled(true);
                    txtViewUhrzeitNew.setText("");
                }
            }
        });
        txtViewHerstellerNew = findViewById(R.id.txtViewHerstellerNew);
        txtViewProduktNew = findViewById(R.id.txtViewProduktNew);
        txtViewMengeNew = findViewById(R.id.txtViewMengeNew);
        txtViewÜberschriftNew = findViewById(R.id.txtViewÜberschriftNew);
        lstViewEinträgeNew = findViewById(R.id.lstViewEinträgeNew);
        lstViewEinträgeNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickMy(position);
            }
        });
        registerForContextMenu(lstViewEinträgeNew);
        arrListMedikamente = new ArrayList<>();
        clearAllTxtViews();

    }

    //Regestireren
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //onAction für den Info-Button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Version 1.0", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.kontext_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (edit) {
            edit = false;
            clearAllTxtViews();
        }
        arrListMedikamente.remove(info.position);
        bindAdapterToList();
        return super.onContextItemSelected(item);
    }

    //onAction für den Button
    public void btnErstellenNewOnClick(final View source) {
        Medikament m;
        try {
            m = new Medikament(txtViewDatumNew.getText().toString(), txtViewUhrzeitNew.getText().toString(), txtViewHerstellerNew.getText().toString(), txtViewProduktNew.getText().toString(),
                    Integer.parseInt(txtViewMengeNew.getText().toString()));
        } catch (NumberFormatException e) {
            m = new Medikament(txtViewDatumNew.getText().toString(), txtViewUhrzeitNew.getText().toString(), txtViewHerstellerNew.getText().toString(), txtViewProduktNew.getText().toString(),
                    0);
        }

        if (!edit) {
            arrListMedikamente.add(m);
        } else {
            arrListMedikamente.set(position, m);
        }
        edit = false;
        bindAdapterToList();
        clearAllTxtViews();
    }

    public void bindAdapterToList() {
        arrAdapterMedikament = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrListMedikamente);
        lstViewEinträgeNew.setAdapter(arrAdapterMedikament);
    }

    public void onItemClickMy(int position) {
        edit = true;
        this.position = position;

        txtViewÜberschriftNew.setText("Medikament bearbeiten");
        txtViewDatumNew.setEnabled(true);
        txtViewUhrzeitNew.setEnabled(true);
        checkBoxDatumNew.setChecked(false);
        checkBoxUhrezitNew.setChecked(false);

        Medikament m = arrListMedikamente.get(position);
        txtViewDatumNew.setText(m.datum);
        txtViewUhrzeitNew.setText(m.uhrzeit);
        txtViewProduktNew.setText(m.produkt);
        txtViewHerstellerNew.setText(m.hersteller);
        txtViewMengeNew.setText("" + m.menge);
    }

    public void clearAllTxtViews() {
        txtViewÜberschriftNew.setText("Neues Medikament regestrieren");
        txtViewHerstellerNew.requestFocus();
        txtViewDatumNew.setText("");
        txtViewUhrzeitNew.setText("");
        txtViewProduktNew.setText("");
        txtViewHerstellerNew.setText("");
        txtViewMengeNew.setText("");

        checkBoxDatumNew.setChecked(true);
        txtViewDatumNew.setEnabled(false);
        txtViewDatumNew.setText(LocalDate.now().toString());

        checkBoxUhrezitNew.setChecked(true);
        txtViewUhrzeitNew.setEnabled(false);
        txtViewUhrzeitNew.setText(LocalTime.now().truncatedTo(ChronoUnit.MINUTES).toString());


    }

}


