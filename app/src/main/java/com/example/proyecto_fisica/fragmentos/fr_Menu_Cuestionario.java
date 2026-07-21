package com.example.proyecto_fisica.fragmentos;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.proyecto_fisica.AdministrarSQLite;
import com.example.proyecto_fisica.R;
import com.example.proyecto_fisica.defineTablas.defTablas;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class fr_Menu_Cuestionario extends Fragment {



    public fr_Menu_Cuestionario() {
        // Required empty public constructor
    }

    private ListView jlstPreguntas;
    private Button jbtnSigR, jbtnValR, jbtnIniC;
    private ArrayList<String> lstPreguntas;
    private ArrayAdapter adapter;
    private RadioGroup jrdbgResp;
    private int contPreg=0, totPreg=0, id, respC, respE, aciertos=0, errores=0, intentos=0;
    private Cursor cursor;
    RadioButton rdb1 , rdb2 ,rdb3 ,rdb4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_fr__menu__cuestionario, container, false);
        rdb1 = view.findViewById(R.id.xrdbR1);
         rdb2 = view.findViewById(R.id.xrdbR2);
       rdb3 = view.findViewById(R.id.xrdbR3);
        rdb4 = view.findViewById(R.id.xrdbR4);



        jlstPreguntas = view.findViewById(R.id.xlstPreguntas);
        jbtnIniC = view.findViewById(R.id.xbtnIniC);
        jbtnSigR = view.findViewById(R.id.xbtnSigR);
        jbtnValR = view.findViewById(R.id.xbtnValR);
        jrdbgResp = view.findViewById(R.id.xrgpResp);

        View.OnClickListener ev = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()== R.id.xbtnIniC){
                    iniciar();
                } else if (v.getId() == R.id.xbtnSigR) {
                    listar();
                } else if (v.getId() == R.id.xbtnValR) {
                    valida(respE);
                }
            }
        };


        jbtnSigR.setOnClickListener(ev);
        jbtnIniC.setOnClickListener(ev);
        jbtnValR.setOnClickListener(ev);

        jrdbgResp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.xrdbR1){
                    respE =1;
                } else if (checkedId== R.id.xrdbR2) {
                    respE =2;
                }else if (checkedId== R.id.xrdbR3) {
                    respE =3;
                }else if (checkedId== R.id.xrdbR4) {
                    respE =4;
                }
            }
        });


        return view;
    }



    public void iniciar(){
        intentos++;
        contPreg=1;
        aciertos=0;
        errores=0;
        String UA ="FISICAIV";
        String qrycuenta="SELECT COUNT(*) FROM "+ defTablas.tPreguntas + " WHERE " + defTablas.cUnidadAp+ " = '"+
                UA+"'";
        //Limpiar
        String[] vaciar ={"","","","","","","",""};
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, vaciar);
        jlstPreguntas.setAdapter(adapter);

        //Consulta

        AdministrarSQLite adminSQL = new AdministrarSQLite(getContext());
        SQLiteDatabase db = adminSQL.getReadableDatabase();


        try {
            Cursor cuenta = db.rawQuery(qrycuenta,null);
            if(cuenta.moveToFirst()){
                totPreg = cuenta.getInt(0);
                Toast.makeText(getContext(), "Total de preguntas a responder: "+ totPreg, Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "Da clic en Siguiente para Contestar el cuestionario", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getContext(), "No existen preguntas", Toast.LENGTH_SHORT).show();
            }
        }catch (SQLiteConstraintException e){
            Toast.makeText(getContext(), "Error en la base: "+ e, Toast.LENGTH_SHORT).show();
        }

        //Iniciar construccion
        String[] projection = {
                defTablas.cIdPreg,
                defTablas.cUnidadAp,
                defTablas.cPregunta,
                defTablas.cResp1,
                defTablas.cResp2,
                defTablas.cResp3,
                defTablas.cResp4,
                defTablas.cRespC,
                defTablas.cImg

        };

        // orden de los datos
        String sortOrder = defTablas.cIdPreg + " ASC";
        //Filtro
        String selection= defTablas.cUnidadAp + " = ? ";
        String[] selectionArgs = {UA};
        try {
            cursor = db.query(
                    defTablas.tPreguntas,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder,
                    null
            );
            if(!cursor.moveToFirst()){
                Toast.makeText(getContext(), "No existe preguntas", Toast.LENGTH_SHORT).show();
                jrdbgResp.clearCheck();
                respE = 0;
            }
            db.close();
        }catch (SQLiteConstraintException e){
            Toast.makeText(getContext(), "Error en la base de datos: "+ e, Toast.LENGTH_SHORT).show();
        }

    }
    @SuppressLint("Range")
    public void listar(){

        lstPreguntas = new ArrayList<>();

        if(contPreg <= totPreg){
            lstPreguntas.add(cursor.getString(cursor.getColumnIndex(defTablas.cIdPreg))+"\n");
            lstPreguntas.add(cursor.getString(cursor.getColumnIndex(defTablas.cPregunta))+"\n");
            lstPreguntas.add(cursor.getString(cursor.getColumnIndex(defTablas.cResp1))+"\n");
            lstPreguntas.add(cursor.getString(cursor.getColumnIndex(defTablas.cResp2))+"\n");
            lstPreguntas.add(cursor.getString(cursor.getColumnIndex(defTablas.cResp3))+"\n");
            lstPreguntas.add(cursor.getString(cursor.getColumnIndex(defTablas.cResp4))+"\n");


            //radios
            String resp1 = cursor.getString(cursor.getColumnIndex(defTablas.cResp1));
            String resp2 = cursor.getString(cursor.getColumnIndex(defTablas.cResp2));
            String resp3 = cursor.getString(cursor.getColumnIndex(defTablas.cResp3));
            String resp4 = cursor.getString(cursor.getColumnIndex(defTablas.cResp4));


            rdb1.setText(resp1);
            rdb2.setText(resp2);
            rdb3.setText(resp3);
            rdb4.setText(resp4);


            respC = cursor.getInt(cursor.getColumnIndex(defTablas.cRespC));
            contPreg++;
            jrdbgResp.clearCheck();
            respE =0;
            adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, lstPreguntas);
            jlstPreguntas.setAdapter(adapter);
            cursor.moveToNext();
        }
        else {
            Toast.makeText(getContext(), "ya no hay preguntas ", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "Aciertos: "+ aciertos + " Errores: "+ errores, Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "Este es tu intento: "+ intentos, Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "Si deseas n nuevo intento da clicen iniciar", Toast.LENGTH_SHORT).show();

            //deshabilitar
            jrdbgResp.clearCheck();
            respE =0;
            cursor.close();
        }

    }
    public void valida(int resp){

        if(respE==0){
            Toast.makeText(getContext(), "Debes elegir una respuesta", Toast.LENGTH_SHORT).show();
        } else if ( respE==respC) {
            aciertos++;
            Toast.makeText(getContext(), "Respuesta correcta", Toast.LENGTH_SHORT).show();
        }else {
            errores++;
            Toast.makeText(getContext(), "Incorrecta", Toast.LENGTH_SHORT).show();
        }
    }



}