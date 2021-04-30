package com.example.g_intent_sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Unfall> arr;
    ListView list;
    private MyAdapter myAdapter;
    private static MainActivity instance;
    ArrayList<Integer> arrNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arr = new ArrayList<>();
        list = findViewById(R.id.lstviewUebersichtUnfaelle);
        try {
            arrNumber = readArrIdex();
            readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        instance = this;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public void readObject() throws IOException, ClassNotFoundException {
        try {
            for (int i = 0; i < arrNumber.size(); i++) {
                FileInputStream fis2 = openFileInput("unfall" + arrNumber.get(i));
                ObjectInputStream ois = new ObjectInputStream(fis2);
                Unfall currentunfall = (Unfall) ois.readObject();
                arr.add(currentunfall);
            }
            bindAdapterToList();
        } catch (FileNotFoundException e) {
            bindAdapterToList();
        }
    }

    public void newAccidentOnClick(final View source) {
        Intent intent = new Intent(this, NewaccidentActivity.class);
        startActivity(intent);
    }

    public void bindAdapterToList() {
        myAdapter = new MyAdapter(this, R.layout.listlayout, arr);
        list.setAdapter(myAdapter);
    }

    public void deleteFile(int indexList, int indexUnfall) throws IOException {
        arr.remove(indexList);
        bindAdapterToList();
        File dir = getFilesDir();
        File file = new File(dir, "unfall" + indexUnfall);
        file.delete();

        arrNumber.remove(indexList);
        FileOutputStream fos2 = openFileOutput("arrIndex", Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos2);
        oos.writeObject(arrNumber);
        oos.close();
        fos2.close();

    }

    public ArrayList readArrIdex() throws IOException {
        try {
            FileInputStream fis1 = openFileInput("arrIndex");
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            return (ArrayList<Integer>) ois1.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            FileOutputStream fos = openFileOutput("arrIndex", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Integer>());
            oos.close();
            fos.close();
            Toast.makeText(this, "ArrFile fehlt.", Toast.LENGTH_SHORT).show();
            readArrIdex();
        }
        return new ArrayList<Integer>();
    }

    public void switchWindow(int index){
        Intent intent = new Intent(this, editaccident.class);
        intent.putExtra("unfall", arr.get(index));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


}