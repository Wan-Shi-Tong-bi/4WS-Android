package com.example.projekt1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
LocalDate date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.textView);
        text.setText("");
    }


    public void onClickb1 (final View source){
        TextView text = findViewById(R.id.textView);
        text.setText(text.getText()+"Chosen Android"+"\n");
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.android);
        Toast t1 = Toast.makeText(this, "You have chosen Android",Toast.LENGTH_LONG);
        t1.show();
    }

    public void onClickb2 (final View source){
        TextView text = findViewById(R.id.textView);
        text.setText(text.getText()+"Chosen Apple"+"\n");
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.applelogo);
        Toast t2 = Toast.makeText(this, "You have chosen Apple",Toast.LENGTH_SHORT);
        t2.show();
    }

}