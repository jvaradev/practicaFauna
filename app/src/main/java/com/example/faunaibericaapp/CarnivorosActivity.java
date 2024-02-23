package com.example.faunaibericaapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CarnivorosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carnivoros);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal(R.drawable.comadreja, "Comadreja"));
        animalList.add(new Animal(R.drawable.gineta, "Gineta"));
        animalList.add(new Animal(R.drawable.lince, "Lince"));
        animalList.add(new Animal(R.drawable.lobo, "Lobo"));
        animalList.add(new Animal(R.drawable.nutria, "Nutria"));
        animalList.add(new Animal(R.drawable.zorro, "Zorro"));

        Adapter adapter = new Adapter(this, animalList);
        recyclerView.setAdapter(adapter);
    }
}
