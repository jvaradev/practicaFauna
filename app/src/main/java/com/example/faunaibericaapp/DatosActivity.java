package com.example.faunaibericaapp;// DatosAnimalActivity.java
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class DatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        // Recibe los datos del intent
        String nombreAnimal = getIntent().getStringExtra("nombreAnimal");

        // Configura la vista con los datos del animal
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        textViewNombre.setText(nombreAnimal);

        // Carga el contenido del archivo .txt correspondiente al nombre del animal
        TextView textViewArchivo = findViewById(R.id.textViewArchivo);
        try {
            // Convierte el nombre del animal a minúsculas y reemplaza espacios con guiones bajos
            String nombreArchivo = nombreAnimal.toLowerCase().replace(" ", "_");
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
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de error: el archivo no pudo ser abierto o no existe
            textViewArchivo.setText("Error al cargar el archivo");
        }
    }
}