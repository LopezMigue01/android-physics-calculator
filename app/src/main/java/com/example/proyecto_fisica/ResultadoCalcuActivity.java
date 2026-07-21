package com.example.proyecto_fisica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultadoCalcuActivity extends AppCompatActivity {

    EditText res;
    double d1, d2, d3, d4, d5, d6, d7, d8, d9, resul;
    String form, nfor = "", texto1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado_calcu);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resultado");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        res = findViewById(R.id.xRes);
        res.setText("");
        Bundle bundle = getIntent().getExtras();

        if(bundle !=null){
            form = bundle.getString("formula");
            d1 = bundle.getDouble("B");
            d2 = bundle.getDouble("k");
            d3 = bundle.getDouble("I");
            d4 = bundle.getDouble("r");
            d5= bundle.getDouble("l");
            d6 = bundle.getDouble("F");
            d7 = bundle.getDouble("q");
            d8 = bundle.getDouble("v");
            d9 = bundle.getDouble("Ei");
            resul = bundle.getDouble("Resultado");
            nfor = bundle.getString("nformula");

            texto1+="\nNOMBRE DE LA FORMULA:"+ nfor+"\n";
            if(form != null){
                texto1 +=" FORMULA:"+form+"\n";
            }



            texto1+="RESULTADO: "+resul;
            if(d2 != 0 && d3 !=0 && d4!= 0){
                texto1+="\nDato 1:"+d2+"\nDato 2:"+ d3+"\nDato 3:"+d4;
            }
            if(d1!=0 && d3!= 0 && d4!=0){
                texto1+="\nDato 1:" +d1+"\nDato 2:"+d3+"\nDato 3:"+d4;
            }
            if(d1!=0 && d2!=0 && d4!=0){
                texto1+="\nDato 1:"+d1+"\nDato 2:"+d2 +"\nDato 3:"+d4;
            }
            if(d1!=0 && d2!=0 && d3!=0)
            {
                texto1+="\nDato 1:"+d1+"\nDato 2:"+d2 +"\nDato 3:"+d3;
            }
            if(d1!=0 && d3!=0 && d5 !=0){
                texto1 += "\nDato 1:"+d1+"\nDato 2:"+d3+"\nDato 3:"+d5;
            }
            if(d6!=0 && d3!= 0 && d5!=0 ){
                texto1 +="\nDato 1:"+d6+"\nDato 2:"+d3+"\nDato 3:"+d5;
            }
            if(d6!=0 && d1!=0 && d5!=0){
                texto1+= "\nDato 1:"+d6+"\nDato 2:"+d1+"\nDato 3:" +d5;

            }
            if(d6!=0 && d7!=0 && d8 !=0){
                texto1 += "\nDato 1:"+d6+"\nDato 2:"+d7+"\nDato 3:"+d8;
            }
            if(d1!=0 && d7!=0 && d8 !=0){
                texto1 += "\nDato 1:"+d1+"\nDato 2:"+d7+"\nDato 3:"+d8;
            }
            if(d6!=0 && d1!=0 && d7 !=0){
                texto1 += "\nDato 1:"+d6+"\nDato 2:"+d1+"\nDato 3:"+d7;
            }
            if(d6!=0 && d1!=0 && d8 !=0){
                texto1 += "\nDato 1:"+d6+"\nDato 2:"+d1+"\nDato 3:"+d8;
            }
            if(d1!=0 && d5!=0 && d8 !=0){
                texto1 += "\nDato 1:"+d1+"\nDato 2:"+d5+"\nDato 3:"+d8;
            }
            if(d9!=0 && d5!=0 && d8 !=0){
                texto1 += "\nDato 1:"+d1+"\nDato 2:"+d5+"\nDato 3:"+d8;
            }
            if(d9!=0 && d1!=0 && d8 !=0){
                texto1 += "\nDato 1:"+d9+"\nDato 2:"+d1+"\nDato 3:"+d8;
            }
            if(d9!=0 && d1!=0 && d5 !=0){
                texto1 += "\nDato 1:"+d9+"\nDato 2:"+d1+"\nDato 3:"+d6;
            }


            res.setText(texto1);

        }
    }



    public void abrir(Class cl){

        Intent intent = new Intent(getApplicationContext(), cl);
        startActivity(intent);

    }
}