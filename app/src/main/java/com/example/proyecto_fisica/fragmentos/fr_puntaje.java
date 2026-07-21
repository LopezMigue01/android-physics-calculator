package com.example.proyecto_fisica.fragmentos;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyecto_fisica.AdministrarSQLite;
import com.example.proyecto_fisica.R;
import com.example.proyecto_fisica.defineTablas.defTablas;

import java.util.ArrayList;

public class fr_puntaje extends Fragment {

    public fr_puntaje() {
        // Required empty public constructor
    }

    EditText jCodigo;
    Button jbtnBuscar;
    ListView jlst;

    private ArrayList<String> lstPuntajes;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fr_puntaje, container, false);
        jbtnBuscar = view.findViewById(R.id.xBuscarPuntaje);
        jCodigo = view.findViewById(R.id.xCodigoPuntaje);
        jlst = view.findViewById(R.id.xlstPuntaje);

        View.OnClickListener ev = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarPuntajes();
            }
        };
        jbtnBuscar.setOnClickListener(ev);

        return view;
    }

    @SuppressLint("Range")
    public void listarPuntajes() {
        String codigoUsuario = jCodigo.getText().toString().trim();
        if (codigoUsuario.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingrese el código del usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        lstPuntajes = new ArrayList<>();

        AdministrarSQLite adminSQL = new AdministrarSQLite(getContext());
        SQLiteDatabase db = adminSQL.getReadableDatabase();

        String query = "SELECT c.nombre AS nombre_cuestionario, r.puntaje, r.fecha " +
                "FROM usuarios u " +
                "JOIN resultados r ON u.codigo = r.codigoUsuario " +
                "JOIN cuestionario c ON r.idCuestionario = c.idCuestionario " +
                "WHERE u.codigo = ?";

        String[] selectionArgs = {codigoUsuario};

        try {
            Cursor cursor = db.rawQuery(query, selectionArgs);

            if (cursor.moveToFirst()) {
                do {
                    String reg = "";
                    reg += "Cuestionario: " + cursor.getString(cursor.getColumnIndex("nombre_cuestionario")) + "\n";
                    reg += "Puntaje: " + cursor.getInt(cursor.getColumnIndex("puntaje")) + "\n";
                    reg += "Fecha: " + cursor.getString(cursor.getColumnIndex("fecha")) + "\n";
                    lstPuntajes.add(reg);
                } while (cursor.moveToNext());
            } else {
                Toast.makeText(getContext(), "No existen resultados para el código ingresado", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "No existen resultados para el código ingresado", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "No existen resultados para el código ingresado", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "No existen resultados para el código ingresado", Toast.LENGTH_SHORT).show();
            }

            cursor.close();

            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, lstPuntajes);
            jlst.setAdapter(adapter);

            db.close();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
