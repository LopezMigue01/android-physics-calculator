package com.example.proyecto_fisica.fragmentos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto_fisica.AdministrarSQLite;
import com.example.proyecto_fisica.R;
import com.example.proyecto_fisica.defineTablas.defTablas;


public class fr_Crear_Cuenta extends Fragment {


    public fr_Crear_Cuenta() {
        // Required empty public constructor
    }
    private EditText jtxtNombre, jtxtTelCel, jtxtCorreo, jtxtContra;
    private Button jbtnCrearCuenta;
    private String nombre,  tCel, correo, contra;
    private long result;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fr__crear__cuenta, container, false);
        jtxtNombre = view.findViewById(R.id.xNomC);
        jtxtTelCel = view.findViewById(R.id.xTelCelC);
        jtxtCorreo = view.findViewById(R.id.xCorreoC);
        jtxtContra = view.findViewById(R.id.xContraC);
        jbtnCrearCuenta = view.findViewById(R.id.xCrear);


        View.OnClickListener ev = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.xCrear) {
                    agregarUsuario();
                }
            }
        };

        jbtnCrearCuenta.setOnClickListener(ev);
        return view;
    }

    public void getDatos() {
        try {
            nombre = jtxtNombre.getText().toString();
            tCel = jtxtTelCel.getText().toString();
            correo = jtxtCorreo.getText().toString();
            contra = jtxtContra.getText().toString();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void agregarUsuario() {
        Toast.makeText(getContext(), "Creando cuenta.... ", Toast.LENGTH_SHORT).show();
        AdministrarSQLite adminSQLite = new AdministrarSQLite(getContext());
        SQLiteDatabase db = adminSQLite.getWritableDatabase();
        getDatos();
        ContentValues registro = new ContentValues();
        registro.put(defTablas.cNombre, nombre);
        registro.put(defTablas.cTelCel, tCel);
        registro.put(defTablas.cCorreo, correo);
        registro.put(defTablas.cContra, contra);


        result = db.insert(defTablas.tUsuario, null, registro);
        if (result == -1) {
            Toast.makeText(getContext(), "ERROR: No se creo la cuenta", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Se creo la cuenta", Toast.LENGTH_SHORT).show();
            Fragment nuevoFragment = new fr_Menu_Cuestionario(); // Reemplaza NuevoFragment con tu Fragment de destino
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, nuevoFragment)
                    .commit();
        }
        db.close();
    }
    public void abrir(Class cl){

        Intent intent = new Intent(getContext(), cl);
        startActivity(intent);

    }
}