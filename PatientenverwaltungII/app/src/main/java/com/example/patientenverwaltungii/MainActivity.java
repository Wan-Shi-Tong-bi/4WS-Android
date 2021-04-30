package com.example.patientenverwaltungii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    ArrayList<Person> arr;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arr = new ArrayList<>();
        i = -1;
//        handleInstanceState(savedInstanceState);
    }

    private void handleInstanceState (Bundle mysavedInstanceState){
        if (mysavedInstanceState == null){
           Log.d("TAG", "handleInstanceState");
     }
        if (mysavedInstanceState.containsKey("counter")){
            i = mysavedInstanceState.getInt("counter");
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("arr", arr);
        outState.putInt("counter", i);
    }

    public void anlgenOnAction(final View source) {
        EditText vorname = findViewById(R.id.vorname);
        EditText nachname = findViewById(R.id.nachname);
        RadioGroup gr = findViewById(R.id.radiobuttonGroup);
        RadioButton btnarzt = findViewById(R.id.radioButtonArzt);
        RadioButton btnpatient = findViewById(R.id.radioButtonPatient);
        Typ current;
        if (btnarzt.isChecked()){
            current = Typ.ARZT;
        }else if (btnpatient.isChecked()){
            current = Typ.PATIENT;
        }else{
            current = Typ.NOTSELECTED;
        }
        Person p = new Person(vorname.getText().toString(), nachname.getText().toString(), current);
        //Person p = new Person(vorname.getText().toString(), nachname.getText().toString(), Typ.Arzt);
        closeKeyboard();
        nachname.setText("");
        vorname.setText("");
        nachname.setSelection(0);
        gr.check(R.id.radioButtonArzt);


        arr.add(p);
        Toast t = Toast.makeText(this, "Patient wurde erfolgreich angelegt", Toast.LENGTH_SHORT);
        t.show();

    }

    public void vorherigePersonOnAction(final View source) {
        TextView vorname = findViewById(R.id.textViewVorname);
        TextView nachname = findViewById(R.id.textViewNachname);
        TextView type = findViewById(R.id.textViewType);
        i--;
        try {
            vorname.setText(arr.get(i).vorname.toUpperCase());
            nachname.setText(arr.get(i).nachname.toUpperCase());
            type.setText(arr.get(i).typ.toString());
        } catch (IndexOutOfBoundsException e) {
            try {
                i = arr.size() - 1;
                vorname.setText(arr.get(i).vorname.toUpperCase());
                nachname.setText(arr.get(i).nachname.toUpperCase());
                type.setText(arr.get(i).typ.toString());
            } catch (IndexOutOfBoundsException e2) {
                Toast t = Toast.makeText(this, "Keine Patienten gespeichert!", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    public void nächstPersonOnAction(final View source) {
        TextView vorname = findViewById(R.id.textViewVorname);
        TextView nachname = findViewById(R.id.textViewNachname);
        TextView type = findViewById(R.id.textViewType);
        i++;
        try {
            vorname.setText(arr.get(i).vorname.toUpperCase());
            nachname.setText(arr.get(i).nachname.toUpperCase());
            type.setText(arr.get(i).typ.toString());
        } catch (IndexOutOfBoundsException e) {
            try {
                i = 0;
                vorname.setText(arr.get(i).vorname.toUpperCase());
                nachname.setText(arr.get(i).nachname.toUpperCase());
                type.setText(arr.get(i).typ.toString());
            } catch (IndexOutOfBoundsException e2) {
                Toast t = Toast.makeText(this, "Keine Patienten gespeichert!", Toast.LENGTH_SHORT);
                t.show();
            }


        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}




