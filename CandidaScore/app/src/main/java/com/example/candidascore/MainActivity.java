package com.example.candidascore;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList <Buttons> items = new ArrayList<>();
    private ListView mListView;
    private MyAdapter myAdapter;
    private static MainActivity instance;

    int count = 0;
    boolean start = false;
    Switch s1;
    Switch s2;
    Switch s3;
    Switch s4;
    ImageView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        mListView = findViewById(R.id.view);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickButton(parent,view,position,id);
            }
        });
        s1 = findViewById(R.id.switch1);
        s2 = findViewById(R.id.switch2);
        s3 = findViewById(R.id.switch3);
        s4 = findViewById(R.id.switch4);
        status = findViewById(R.id.status);
        checksum();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public void onClickButton(AdapterView<?> parent, View view, int position, long id){
        Buttons b = (Buttons) mListView.getAdapter().getItem(position);
        s1.setChecked(b.b1);
        s2.setChecked(b.b2);
        s3.setChecked(b.b3);
        s4.setChecked(b.b4);
        count = b.count;
        checksum();

        Toast toast=Toast.makeText(getApplicationContext(),"Graphik aktualisiert",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", count);
        outState.putBoolean("sw1",s1.isChecked());
        outState.putBoolean("sw2",s2.isChecked());
        outState.putBoolean("sw3",s3.isChecked());
        outState.putBoolean("sw4",s4.isChecked());

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        count = savedInstanceState.getInt("counter");
        s1.setChecked(savedInstanceState.getBoolean("sw1"));
        s2.setChecked(savedInstanceState.getBoolean("sw2"));
        s3.setChecked(savedInstanceState.getBoolean("sw3"));
        s4.setChecked(savedInstanceState.getBoolean("sw4"));
        checksum();
    }

    public void onAction1 (final View source) {
        if (s1.isChecked()){
            count++;

        }else{
            count--;
        }

        checksum();
    }

    public void onAction2 (final View source) {
        if (s2.isChecked()){
            count++;
        }else{
            count--;
        }

        checksum();
    }

    public void onAction3 (final View source) {
        if (s3.isChecked()){
            count++;
        }else{
            count--;
        }

        checksum();
    }

    public void onAction4 (final View source) {
        if (s4.isChecked()){
            count = count + 2;
        }else{
            count = count - 2;
        }

        checksum();
    }

    public void checksum(){
        myAdapter = new MyAdapter(this, R.layout.list_item, items);
        mListView.setAdapter(myAdapter);

        TextView t = findViewById(R.id.score);

        t.setText("Candida Score: "+count);
        if (count>=3){
            start = true;
            status.setImageResource(R.drawable.mycirclered);
        }else {
            start = false;
            status.setImageResource(R.drawable.mycirclegreen);
        }
    }

    public void onActionSpeichern (final View source) {
        items.add(getSwitches());
        checksum();

        Toast toast=Toast.makeText(getApplicationContext(),R.string.hinzugefügt,Toast.LENGTH_SHORT);
        toast.show();
    }


    public Buttons getSwitches(){
       return new Buttons(s1.isChecked(),s2.isChecked(),s3.isChecked(),s4.isChecked(),start,count);
    }

    public void remove(int position){
        items.remove(position);
        s1.setChecked(false);
        s2.setChecked(false);
        s3.setChecked(false);
        s4.setChecked(false);
        count = 0;
        checksum();

        Toast toast=Toast.makeText(getApplicationContext(),R.string.entfernt,Toast.LENGTH_SHORT);
        toast.show();

    }

    public void showinfo(int index){
        MyDialog md = new MyDialog(getInfoMessage(index),"Information über Patienten " + index++);
        md.show(getSupportFragmentManager(), "mes1");
    }

    public String getInfoMessage(int index){
        String message =  getResources().getString(R.string.info) + "\n";
        message = message +  getResources().getString(R.string.patienthatte) + "\n";
        Buttons b = items.get(index);
        if (b.b1){
            message = message + getResources().getString(R.string.btn1m) + "\n";
        }if (b.b2){
            message = message + getResources().getString(R.string.btn2m) + "\n";
        }if (b.b3){
            message = message + getResources().getString(R.string.btn3m) + "\n";
        } if (b.b4) {
            message = message + getResources().getString(R.string.btn4m) + "\n";
        }
        return message;

    }



}


