package com.example.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class green_fragment extends Fragment {

    Button b;
    TextView textView;
    OnSelectionChangedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_green_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        b = MainActivity.getInstance().findViewById(R.id.btnSpeichern);
        textView = MainActivity.getInstance().findViewById(R.id.txtViewName);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectionChanged(textView.getText().toString());
            }
        });
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectionChangedListener) {
            listener = (OnSelectionChangedListener) context;
        } else {
            Log.e("EXEPTION", "Act does not exist");
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    public interface OnSelectionChangedListener {
        void onSelectionChanged(String message);
    }


}