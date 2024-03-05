package com.example.faunaibericaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DatosFragment extends Fragment {

    public DatosFragment() {
        // Constructor vacío requerido por la API de fragmentos
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.datos_horizontal, container, false);

        // Obtén el nombre del animal del argumento del fragmento
        String nombreAnimal = getArguments().getString("nombreAnimal");

        // Configura la vista con los datos del animal
        TextView textViewNombre = rootView.findViewById(R.id.textViewNombre);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        // Carga el contenido del archivo .txt correspondiente al nombre del animal
        TextView textViewArchivo = rootView.findViewById(R.id.textViewArchivo);
        ImageView imagenAnimal = rootView.findViewById(R.id.imagenAnimal);

        try {
            // Convierte el nombre del animal a minúsculas y reemplaza espacios con guiones bajos
            String nombreArchivo = nombreAnimal.toLowerCase().replace(" ", "_");
            String nombreArchivoCompleto = nombreArchivo + ".txt";

            // Intenta abrir el archivo correspondiente en la carpeta assets
            InputStream is = requireActivity().getAssets().open(nombreArchivoCompleto);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Configura el texto del TextView con el contenido del archivo
            String textoArchivo = new String(buffer);
            textViewArchivo.setText(textoArchivo);

            // Configura la imagen del animal
            int idImagen = getResources().getIdentifier(nombreArchivo, "drawable", requireActivity().getPackageName());
            if (idImagen != 0) {
                imagenAnimal.setImageResource(idImagen);
            } else {
                // Si no se encuentra la imagen, puedes mostrar una imagen predeterminada o manejar el error de otra manera.
                imagenAnimal.setImageResource(R.drawable.oso);
            }

            // Resto del código relacionado con RecyclerView...

        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de error: el archivo no pudo ser abierto o no existe
            // Resto del código de manejo de errores...
        }

        return rootView;
    }
}
