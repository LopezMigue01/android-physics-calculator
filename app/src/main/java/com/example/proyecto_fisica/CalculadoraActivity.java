package com.example.proyecto_fisica;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculadoraActivity extends AppCompatActivity {
    private EditText a, b, c;
    private int currentTextView = 1;

    double x = 1, y = 1, z = 1, r = 1;
    TextView jtxt1, jtxt2, jtxt3;
    Intent i;
    Spinner jSpnFormulas, jSpnObtener;
    String form = "", nfor = "", obt = "";
    Button jbtnCalcular;

    LinearLayout jLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        i = new Intent(this, ResultadoCalcuActivity.class);
        jLayout = findViewById(R.id.lLayout1);
        a = findViewById(R.id.xDato1);
        b = findViewById(R.id.xDato2);
        c = findViewById(R.id.xDato3);
        jtxt1 = findViewById(R.id.txt1);
        jtxt2 = findViewById(R.id.txt2);
        jtxt3 = findViewById(R.id.txt3);

        // Set onClickListeners for the EditText fields
        a.setOnClickListener(v -> currentTextView = 1);
        b.setOnClickListener(v -> currentTextView = 2);
        c.setOnClickListener(v -> currentTextView = 3);

        jbtnCalcular = findViewById(R.id.xCalcular);
        jSpnFormulas = findViewById(R.id.xspnFormula);
        jSpnObtener = findViewById(R.id.xspnObtener);

        String[] ob = {""};
        final ArrayAdapter<String> AdapterObtener = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, ob);
        AdapterObtener.setDropDownViewResource(R.layout.spinner_item);
        jSpnObtener.setAdapter(AdapterObtener);

        String[] F = {"Seleccionar", "Ley del Ampere", "Fuerza en un conductor recto", "Fuerza de Lorentz", "Ley de Faraday"};
        final ArrayAdapter<String> AdapterFormula = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, F);
        AdapterFormula.setDropDownViewResource(R.layout.spinner_item1);
        jSpnFormulas.setAdapter(AdapterFormula);

        jSpnFormulas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    switch (position) {
                        case 0:
                            jSpnObtener.setAdapter(null);
                            nfor = "Seleccionar";
                            break;
                        case 1:
                            nfor = "Ley del Ampere";
                            configurarSpinnerObtener(new String[]{"B", "k'", "I", "r"});
                            break;
                        case 2:
                            nfor = "Fuerza en un conductor recto";
                            configurarSpinnerObtener(new String[]{"F", "B", "I", "l"});
                            break;
                        case 3:
                            nfor = "Fuerza de Lorentz";
                            configurarSpinnerObtener(new String[]{"F", "B", "q", "v"});
                            break;
                        case 4:
                            nfor = "Ley de Faraday";
                            configurarSpinnerObtener(new String[]{"Ei", "B", "l", "v"});
                            break;
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                jSpnObtener.setAdapter(null);
            }
        });

        i.putExtra("formula", form);

        jbtnCalcular.setOnClickListener(v -> {
            try {
                if (validarCampos()) {
                    calcular();
                    i.putExtra("nformula", nfor);

                    if (nfor.equals("Seleccionar")) {
                        Toast.makeText(getApplicationContext(), "Selecciona una fórmula", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "LLENA TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
            }
        });


    }



    private boolean validarCampos() {
        return !TextUtils.isEmpty(a.getText().toString()) &&
                !TextUtils.isEmpty(b.getText().toString()) &&
                !TextUtils.isEmpty(c.getText().toString());
    }

    public void abrir(Class cl) {
        Intent intent = new Intent(getApplicationContext(), cl);
        startActivity(intent);
    }

    public void obtener() {
        try {
            x = Double.parseDouble(a.getText().toString());
            y = Double.parseDouble(b.getText().toString());
            z = Double.parseDouble(c.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Error al obtener datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void calcular() {
        obtener();
        try {
            switch (nfor) {
                case "Ley del Ampere":
                    calcularLeyAmpere();
                    break;
                case "Fuerza en un conductor recto":
                    calcularFuerzaConductor();
                    break;
                case "Fuerza de Lorentz":
                    calcularFuerzaLorentz();
                    break;
                case "Ley de Faraday":
                    calcularLeyFaraday();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error en los cálculos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void configurarSpinnerObtener(String[] opciones) {
        final ArrayAdapter<String> AdapterObtener = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, opciones);
        jSpnObtener.setAdapter(AdapterObtener);
        jLayout.setVisibility(View.VISIBLE);
        jSpnObtener.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                obt = jSpnObtener.getSelectedItem().toString();
                switch (nfor) {
                    case "Ley del Ampere":
                        configurarTextosLeyAmpere(position);
                        break;
                    case "Fuerza en un conductor recto":
                        configurarTextosFuerzaConductor(position);
                        break;
                    case "Fuerza de Lorentz":
                        configurarTextosFuerzaLorentz(position);
                        break;
                    case "Ley de Faraday":
                        configurarTextosLeyFaraday(position);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void configurarTextosLeyAmpere(int position) {
        switch (position) {
            case 0:
                jtxt1.setText("K':");
                jtxt2.setText("I:");
                jtxt3.setText("r:");
                break;
            case 1:
                jtxt1.setText("B:");
                jtxt2.setText("I:");
                jtxt3.setText("r:");
                break;
            case 2:
                jtxt1.setText("B:");
                jtxt2.setText("K':");
                jtxt3.setText("r:");
                break;
            case 3:
                jtxt1.setText("B:");
                jtxt2.setText("K':");
                jtxt3.setText("I:");
                break;
        }
    }

    private void configurarTextosFuerzaConductor(int position) {
        switch (position) {
            case 0:
                jtxt1.setText("B:");
                jtxt2.setText("I:");
                jtxt3.setText("l:");
                break;
            case 1:
                jtxt1.setText("F:");
                jtxt2.setText("I:");
                jtxt3.setText("l:");
                break;
            case 2:
                jtxt1.setText("F:");
                jtxt2.setText("B:");
                jtxt3.setText("l:");
                break;
            case 3:
                jtxt1.setText("F:");
                jtxt2.setText("B:");
                jtxt3.setText("I:");
                break;
        }
    }

    private void configurarTextosFuerzaLorentz(int position) {
        switch (position) {
            case 0:
                jtxt1.setText("B:");
                jtxt2.setText("q:");
                jtxt3.setText("v:");
                break;
            case 1:
                jtxt1.setText("F:");
                jtxt2.setText("q:");
                jtxt3.setText("v:");
                break;
            case 2:
                jtxt1.setText("F:");
                jtxt2.setText("B:");
                jtxt3.setText("v:");
                break;
            case 3:
                jtxt1.setText("F:");
                jtxt2.setText("B:");
                jtxt3.setText("q:");
                break;
        }
    }

    private void configurarTextosLeyFaraday(int position) {
        switch (position) {
            case 0:
                jtxt1.setText("B:");
                jtxt2.setText("l:");
                jtxt3.setText("v:");
                break;
            case 1:
                jtxt1.setText("Ei:");
                jtxt2.setText("l:");
                jtxt3.setText("v:");
                break;
            case 2:
                jtxt1.setText("Ei:");
                jtxt2.setText("B:");
                jtxt3.setText("v:");
                break;
            case 3:
                jtxt1.setText("Ei:");
                jtxt2.setText("B:");
                jtxt3.setText("l:");
                break;
        }
    }

    private void calcularLeyAmpere() {
        switch (obt) {
            case "B":
                r = (x * 2 * y) / z;
                form = "B = k'2I / r";
                break;
            case "k'":
                r = (x * z) / (2 * y);
                form = "k' = Br / 2I";
                break;
            case "I":
                r = (x * z) / (2 * y);
                form = "I = Br / 2k'";
                break;
            case "r":
                r = (x * 2 * y) / z;
                form = "r = k'2I / B";
                break;
        }
        actualizarIntentExtras();
    }

    private void calcularFuerzaConductor() {
        switch (obt) {
            case "F":
                r = x * y * z;
                form = "F = B * I * l";
                break;
            case "B":
                r = x / (y * z);
                form = "B = F / (I * l)";
                break;
            case "I":
                r = x / (y * z);
                form = "I = F / (B * l)";
                break;
            case "l":
                r = x / (y * z);
                form = "l = F / (B * I)";
                break;
        }
        actualizarIntentExtras();
    }

    private void calcularFuerzaLorentz() {
        switch (obt) {
            case "F":
                r = x * y * z;
                form = "F = B * q * v";
                break;
            case "B":
                r = x / (y * z);
                form = "B = F / (q * v)";
                break;
            case "q":
                r = x / (y * z);
                form = "q = F / (B * v)";
                break;
            case "v":
                r = x / (y * z);
                form = "v = F / (B * q)";
                break;
        }
        actualizarIntentExtras();
    }

    private void calcularLeyFaraday() {
        switch (obt) {
            case "Ei":
                r = x * y * z;
                form = "Ei = B * l * v";
                break;
            case "B":
                r = x / (y * z);
                form = "B = Ei / (l * v)";
                break;
            case "l":
                r = x / (y * z);
                form = "l = Ei / (B * v)";
                break;
            case "v":
                r = x / (y * z);
                form = "v = Ei / (B * l)";
                break;
        }
        actualizarIntentExtras();
    }

    private void actualizarIntentExtras() {
        i.putExtra("formula", form);
        i.putExtra("Resultado", r);
        i.putExtra("B", x);
        i.putExtra("I", y);
        i.putExtra("r", z);
        // Añadir otros extras según sea necesario...
        Toast.makeText(this, "Formula: " + form + "\nResultado: " + r, Toast.LENGTH_SHORT).show(); // Mensaje de depuración
    }


   //botones
    public void onDigit(View view) {
        Button button = (Button) view;
        String number = button.getText().toString();

        switch (currentTextView) {
            case 1:
                a.append(number);
                break;
            case 2:
                b.append(number);
                break;
            case 3:
                c.append(number);
                break;
        }
    }
    public void onCE(View view) {
        Button button = (Button) view;


        switch (currentTextView) {
            case 1:
                a.append("E");
                break;
            case 2:
                b.append("E");
                break;
            case 3:
                c.append("E");
                break;
        }
    }

    public void onClearButtonClick(View view) {
        a.setText("");
        b.setText("");
        c.setText("");
    }
}