package com.example.drg_fallpauschalenkatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Datensatz> arr;
    TextView drgGruppeTextView;
    TextView mittlereVerweildauer;
    TextView ersterTagAbschlag;
    TextView ersterTag;
    TextView zusätzlicesGeld;
    ListView mListView;
    ArrayAdapter<Datensatz>mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drgGruppeTextView = findViewById(R.id.textViewDrgGruppe);
        mittlereVerweildauer = findViewById(R.id.textViewMittlereVerweildauer);
        ersterTagAbschlag = findViewById(R.id.textViewErsterTagAbschlag);
        ersterTag = findViewById(R.id.textViewErsterTag);
        zusätzlicesGeld = findViewById(R.id.textViewZusätzlichesGeld);
        mListView = findViewById(R.id.listViewList);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickAtion(position);
            }
        });
        arr = new ArrayList<>();
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bindAdpaterToList();
    }


    public InputStream getInputStreamForAsset(String filename) {
        AssetManager assets = getAssets();
        try {
            return assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void readFile() throws IOException {
        InputStream is = getInputStreamForAsset("daten.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        line = br.readLine();

        try {
            while ((line = br.readLine()) != null) {
               Datensatz d = splitLine(line);
                arr.add(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        line = "test";
    }

    public Datensatz splitLine (String line){
         String[] tmparr = line.split(";");

         if(tmparr[2].equals("")){
             return new Datensatz (tmparr[0], tmparr[1],0, Integer.parseInt(tmparr[3]), Integer.parseInt(tmparr[4]));
         }else if (tmparr[3].equals("")){
             return new Datensatz (tmparr[0], tmparr[1], Double.parseDouble(tmparr[2]), 0, Integer.parseInt(tmparr[4]));
         }else if (!tmparr[4].equals("")){
             return new Datensatz (tmparr[0], tmparr[1], Double.parseDouble(tmparr[2]), Integer.parseInt(tmparr[3]), 0);
         }else{
             return new Datensatz (tmparr[0], tmparr[1], Double.parseDouble(tmparr[2]), Integer.parseInt(tmparr[3]), Integer.parseInt(tmparr[4]));
         }
    }

    public void bindAdpaterToList(){
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        mListView.setAdapter(mAdapter);
    }

    public void onClickAtion(int position){
        Datensatz d = (Datensatz) mListView.getAdapter().getItem(position);
        drgGruppeTextView.setText("DRG-Gruppe: "+d.bezeichnung);
        mittlereVerweildauer.setText("Mittlere Verweildauer: " + d.mittlereVerweildauer);
        ersterTagAbschlag.setText("Erster Tag Abschlag: "+d.ersterTagAbschalg);
//        ersterTag.setText(""+d.mittlereVerweildauer);
        zusätzlicesGeld.setText("Zusätzliches Geld: "+d.ersterTagZusätzlichesGeld);

    }
}