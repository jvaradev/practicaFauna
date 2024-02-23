package com.example.faunaibericaapp;// DatosActivity.java
import android.os.Bundle;
import android.widget.ImageView;
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

        // Carga el contenido del archivo .txt correspondiente al nombre del animal
        TextView textViewArchivo = findViewById(R.id.textViewArchivo);
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
}
