package com.example.gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView textViewLong, textViewLat, textViewDatum;
    Button btnÖffneDatenbank;
    private static final int RQ_CODE = 123;
    private boolean isGpsAllowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views initialisieren
        textViewLong = findViewById(R.id.txtViewLong);
        textViewLat = findViewById(R.id.txtViewLat);
        textViewDatum = findViewById(R.id.txtViewDatum);
        btnÖffneDatenbank = findViewById(R.id.btnÖffneDatenbank);
        btnÖffneDatenbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnÖffneDatenbankOnClick();
            }
        });
        checkPermissionGPS();
    }

    public void btnÖffneDatenbankOnClick(){
        Intent intent = new Intent(this, show_datenbank.class);
        startActivity(intent);
    }

    public void checkPermissionGPS(){
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (ActivityCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{permission}, RQ_CODE);
            isGpsAllowed = false;
        }else{
            isGpsAllowed = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != RQ_CODE) return;
        if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
        }else{
            gpsGranted();
        }
    }

    private void gpsGranted() {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}