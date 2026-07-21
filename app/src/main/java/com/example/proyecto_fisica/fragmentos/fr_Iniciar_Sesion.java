package com.example.proyecto_fisica.fragmentos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto_fisica.AdministrarSQLite;
import com.example.proyecto_fisica.R;
import com.example.proyecto_fisica.defineTablas.defTablas;


public class fr_Iniciar_Sesion extends Fragment {


    public fr_Iniciar_Sesion() {
        // Required empty public constructor
    }
    private EditText jCorreo, jContra;
    private Button jbtnLogin;
    private String correo, contrasena;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fr__iniciar__sesion, container, false);
        jCorreo = view.findViewById(R.id.xCorreo);
        jContra = view.findViewById(R.id.xContra);
        jbtnLogin = view.findViewById(R.id.xLogin);

        View.OnClickListener ev = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.xLogin) {
                    if (validar()) {

                        Fragment nuevoFragment = new fr_puntaje(); // Reemplaza NuevoFragment con tu Fragment de destino
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_content_main, nuevoFragment) // Para permitir al usuario regresar al Fragment anterior
                                .commit();
                    }
                }
            }
        };

        jbtnLogin.setOnClickListener(ev);
        return view;
    }

    public boolean validar() {

        AdministrarSQLite administrarSQLite = new AdministrarSQLite(getContext());
        SQLiteDatabase db = administrarSQLite.getWritableDatabase();

        correo = jCorreo.getText().toString();
        contrasena = jContra.getText().toString();

        // Usando consultas preparadas para evitar la inyección SQL
        String[] columns = { "*" };
        String selection = defTablas.cCorreo + " = ? AND " + defTablas.cContra + " = ?";
        String[] selectionArgs = { correo, contrasena };

        Cursor fila = db.query(defTablas.tUsuario, columns, selection, selectionArgs, null, null, null);

        boolean isValid = fila.getCount() > 0;

        // Cerrar el cursor y la base de datos después de usar
        fila.close();
        db.close();

        return isValid;
    }

    public void abrir(Class cl){

        Intent intent = new Intent(getContext(), cl);
        startActivity(intent);

    }
}