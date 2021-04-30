package com.example.g_intent_sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class NewaccidentActivity extends AppCompatActivity {

    TextView currentDatum;
    TextView currentUhrzeit;
    TextView currentStrasse;
    TextView currentHausnummer;
    TextView currentPLZ;
    TextView currentOrt;
    CheckBox checkCurrentDatum;
    CheckBox checkCurrentUhrzeit;
    CheckBox currentVerlezte;
    CheckBox currentAndereSchaeden;
    ArrayList<Integer> arrAllFileNumber;
    ArrayList<Zeuge> currentzeugen;
    int count;
    private static NewaccidentActivity instance;
    ListView witnessList;
    private MyAdapterWitness witnessAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccident);

        currentDatum = findViewById(R.id.txtFieldDate);
        currentUhrzeit = findViewById(R.id.txtFieldUhrzeit);
        currentStrasse = findViewById(R.id.txtFieldStrasse);
        currentHausnummer = findViewById(R.id.txtFieldHausnr);
        currentPLZ = findViewById(R.id.txtFieldPlz);
        currentOrt = findViewById(R.id.txtFieldOrt);
        checkCurrentDatum = findViewById(R.id.checkBoxDate);
        checkCurrentUhrzeit = findViewById(R.id.checkBoxUhrzeit);
        currentVerlezte = findViewById(R.id.checkBoxVerlezte);
        currentAndereSchaeden = findViewById(R.id.checkBoxAndereSachschäden);
        witnessList = findViewById(R.id.lstViewWitness);
        try {
            arrAllFileNumber = readIndexArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        count = 0;
        currentzeugen = new ArrayList<>();
        instance = this;

        checkCurrentDatum.setChecked(true);
        checkCurrentUhrzeit.setChecked(true);
        currentDatum.setText(LocalDate.now().toString());
        currentUhrzeit.setText(LocalTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
        currentDatum.setEnabled(false);
        currentUhrzeit.setEnabled(false);
    }

    public void saveAccidentOnClick(final View source) throws IOException {
        getCount();
        try {
            Unfall u = new Unfall(currentDatum.getText().toString(), currentUhrzeit.getText().toString(), currentStrasse.getText().toString(),
                    Integer.parseInt(currentHausnummer.getText().toString()), Integer.parseInt(currentPLZ.getText().toString()), currentOrt.getText().toString(),
                    currentVerlezte.isChecked(), currentAndereSchaeden.isChecked(), count, currentzeugen);

            arrAllFileNumber.add(u.id);

            FileOutputStream fos = openFileOutput("unfall" + count++, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(u);
            oos.close();

            raisecount();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(this, "Unfall wurde lokal gespeichert.", Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Bitte Werte überprüfen!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getCount() throws IOException {
        FileInputStream fis = null;
        try {
            fis = openFileInput("count.txt");
        } catch (FileNotFoundException e) {
            FileOutputStream fos = openFileOutput("count.txt", Context.MODE_PRIVATE);
            BufferedWriter br2 = new BufferedWriter(new OutputStreamWriter(fos));
            br2.write("" + 1);
            br2.close();
            fos.close();
            fis = openFileInput("count.txt");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        try {
            count = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {

        }
    }

    public void raisecount() throws IOException {
        FileOutputStream fos = openFileOutput("count.txt", Context.MODE_PRIVATE);
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(fos));
        br.write("" + count++);
        br.close();
        fos.close();
        FileOutputStream fos2 = openFileOutput("arrIndex", Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos2);
        oos.writeObject(arrAllFileNumber);
        oos.close();
        fos2.close();
    }

    public void checkBoxUhrzeitOnClick(final View source) {
        if (checkCurrentUhrzeit.isChecked()) {
            currentUhrzeit.setText(LocalTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
            currentUhrzeit.setEnabled(false);
        } else {
            currentUhrzeit.setText("");
            currentUhrzeit.setEnabled(true);
        }
    }

    public void checkBoxDateOnClick(final View source) {
        if (checkCurrentDatum.isChecked()) {
            currentDatum.setText(LocalDate.now().toString());
            currentDatum.setEnabled(false);
        } else {
            currentDatum.setText("");
            currentDatum.setEnabled(true);
        }
    }

    public ArrayList readIndexArray() throws IOException {
        try {
            FileInputStream fis = openFileInput("arrIndex");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (ArrayList) ois.readObject();
        } catch (FileNotFoundException e) {
            FileOutputStream fos = openFileOutput("arrIndex", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Integer>());
            oos.close();
            fos.close();

            File dir = getFilesDir();
            dir.delete();

            Toast.makeText(this, "ArrFile fehlt. Alle Daten wurden gelöscht", Toast.LENGTH_SHORT).show();
            readIndexArray();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void bindAdapterToList(){
        witnessAdapter = new MyAdapterWitness(this, R.layout.listlayoutwitness, currentzeugen);
        witnessList.setAdapter(witnessAdapter);
    }

    public void openWitnessWindowOnClick (final View source){
        Intent intent = new Intent(this, newwitness.class);
        startActivity(intent);
    }

    public void addNewWitness(Zeuge z){
        currentzeugen.add(z);
        bindAdapterToList();
    }

    public void removeWitness(int position){
        currentzeugen.remove(position);
        bindAdapterToList();
    }

    public void switchWindowEditWitness (int position){
        Intent intent = new Intent(this, EditwitnessActivity.class);
        intent.putExtra("zeuge" , currentzeugen.get(position));
        intent.putExtra("id", position);
        startActivity(intent);
  }

    public void editWitness (Zeuge z, int index){
        currentzeugen.set(index, z);
        bindAdapterToList();
    }

    public static NewaccidentActivity getInstance() {
        return instance;
    }
}