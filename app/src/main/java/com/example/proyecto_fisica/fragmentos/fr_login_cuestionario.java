package com.example.proyecto_fisica.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyecto_fisica.R;


public class fr_login_cuestionario extends Fragment {


    public fr_login_cuestionario() {
        // Required empty public constructor
    }

    private Button jbtnLogin, jbtnCrearCuentafr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fr_login_cuestionario, container, false);
        jbtnLogin = view.findViewById(R.id.xIniciarSesion);
        jbtnCrearCuentafr = view.findViewById(R.id.xCrearCuentafr);
        View.OnClickListener ev = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.xIniciarSesion){try{
                    Fragment nuevoFragment = new fr_Iniciar_Sesion(); // Reemplaza NuevoFragment con tu Fragment de destino
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment_content_main, nuevoFragment)
                            .addToBackStack(null) // Para permitir al usuario regresar al Fragment anterior
                            .commit();
                }catch (Exception e){
                    Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                }
                } else if (v.getId() == R.id.xCrearCuentafr) {
                    try{

                        Fragment nuevoFragment = new fr_Crear_Cuenta(); // Reemplaza NuevoFragment con tu Fragment de destino
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_content_main, nuevoFragment)
                                .addToBackStack(null) // Para permitir al usuario regresar al Fragment anterior
                                .commit();

                    }catch (Exception e){
                        Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        };

        jbtnLogin.setOnClickListener(ev);
        jbtnCrearCuentafr.setOnClickListener(ev);

        return view;
    }
}