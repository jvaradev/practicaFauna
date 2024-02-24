package com.example.faunaibericaapp;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faunaibericaapp.Adapter;
import com.example.faunaibericaapp.Animal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recibe los datos del intent
        String nombreAnimal = getIntent().getStringExtra("nombreAnimal");

        // Verificar la orientación actual y si es una tablet
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE && isTablet()) {
            setContentView(R.layout.datos_horizontal);
        } else {
            setContentView(R.layout.activity_datos);
        }

        // Configura la vista con los datos del animal
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewNombre = findViewById(R.id.textViewNombre);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Carga el contenido del archivo .txt correspondiente al nombre del animal
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewArchivo = findViewById(R.id.textViewArchivo);
        String nombreArchivo = null;
        try {
            // Convierte el nombre del animal a minúsculas y reemplaza espacios con guiones bajos
            nombreArchivo = nombreAnimal.toLowerCase().replace(" ", "_");
            String nombreArchivoCompleto = nombreArchivo + ".txt";

            // Intenta abrir el archivo correspondiente en la carpeta assets
            InputStream is = getAssets().open(nombreArchivoCompleto);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Configura el texto del TextView con el contenido del archivo
            String textoArchivo = new String(buffer);
            textViewArchivo.setText(textoArchivo);

            // Configura la imagen del animal
            ImageView imagenAnimal = findViewById(R.id.imagenAnimal);
            int idImagen = getResources().getIdentifier(nombreArchivo, "drawable", getPackageName());
            if (idImagen != 0) {
                imagenAnimal.setImageResource(idImagen);
            } else {
                // Si no se encuentra la imagen, puedes mostrar una imagen predeterminada o manejar el error de otra manera.
                imagenAnimal.setImageResource(R.drawable.oso);
            }

            // Obtén la lista de animales desde AnimalesProvider (simulado)
            List<Animal> animalList = Adapter.getListaAnimales();

            // Configura el RecyclerView con la lista de animales
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            Adapter adapter = new Adapter(this, animalList);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de error: el archivo no pudo ser abierto o no existe
            InputStream is = null;
            try {
                ImageView imagenAnimal = findViewById(R.id.imagenAnimal);
                is = getAssets().open("error");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                // Configura el texto del TextView con el contenido del archivo
                String textoArchivo = new String(buffer);
                textViewArchivo.setText(textoArchivo);
                imagenAnimal.setImageResource(R.drawable.oso);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private boolean isTablet() {
        // Determina si el dispositivo es una tablet basándose en la anchura de la pantalla
        int screenLayout = getResources().getConfiguration().screenLayout;
        return (screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
