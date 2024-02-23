package com.example.faunaibericaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioCarnivoro = findViewById(R.id.radioCarnivoro);
        RadioButton radioRapaces = findViewById(R.id.radioRapaces);

        radioCarnivoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarnivorosActivity.class);
                startActivity(intent);
            }
        });

        radioRapaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RapacesActivity.class);
                startActivity(intent);
            }
        });
    }
}
