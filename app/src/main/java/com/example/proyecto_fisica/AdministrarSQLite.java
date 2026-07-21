package com.example.proyecto_fisica;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.proyecto_fisica.defineTablas.defTablas;

public class AdministrarSQLite extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "fisicaCal.db";

    public AdministrarSQLite(Context context){

        super(context,DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tablas
        db.execSQL(defTablas.crear_cuestionario_preguntas);
        db.execSQL(defTablas.crear_cuestionario);
        db.execSQL(defTablas.crear_usuario);
        db.execSQL(defTablas.crear_pregunta);
        db.execSQL(defTablas.crear_resultados);





        // Insertar datos
        db.execSQL(defTablas.insertar_resultados);
        db.execSQL(defTablas.insertar_cuestionario_preguntas);
        db.execSQL(defTablas.insertar_cuestionario);
        db.execSQL(defTablas.insertar_usuario);
        db.execSQL(defTablas.insertar_pregunta);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(defTablas.eliminar_cuestionario_preguntas);
        db.execSQL(defTablas.eliminar_cuestionario);
        db.execSQL(defTablas.eliminar_pregunta);
        db.execSQL(defTablas.eliminar_usuario);
        db.execSQL(defTablas.eliminar_resultados);
        onCreate(db);
    }

}
