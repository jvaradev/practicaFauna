package com.example.faunaibericaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AnimalViewHolder> {

    private static List<Animal> animalList;
    private Context context;

    public Adapter(Context context, List<Animal> animalList) {
        this.context = context;
        this.animalList = animalList;
    }

    public static List<Animal> getListaAnimales() {
        return animalList;
    }


    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);
        holder.imageView.setImageResource(animal.getImageResource());
        holder.textView.setText(animal.getName());

        // Agregar un OnClickListener para abrir DatosAnimalActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DatosActivity.class);
                intent.putExtra("nombreAnimal", animal.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
