package com.example.faunaibericaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RapacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapaces);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal(R.drawable.aguila, "Aguila"));
        animalList.add(new Animal(R.drawable.buho, "Buho"));
        animalList.add(new Animal(R.drawable.buitre, "Buitre"));
        animalList.add(new Animal(R.drawable.cernicalo, "Cernicalo"));
        animalList.add(new Animal(R.drawable.halcon, "Halcón"));
        animalList.add(new Animal(R.drawable.quebrantahuesos, "Quebrantahuesos"));

        Adapter adapter = new Adapter(this, animalList);
        recyclerView.setAdapter(adapter);
    }
}