package com.example.practica;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{

    TextView nombre;
    TextView apellidos;
    TextView edad;
    RadioGroup genero;
    RadioButton seleccionado;
    Spinner estado;
    Switch hijos;
    Button aceptar;
    Button limpiar;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nombre = findViewById(R.id.editTextNombre);
        nombre.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        apellidos = findViewById(R.id.editTextApellidos);
        apellidos.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        edad = findViewById(R.id.editTextEdad);
        genero = findViewById(R.id.radioGroupGenero);
        estado = findViewById(R.id.spinnerEstado);
        hijos = findViewById(R.id.switchHijos);
        aceptar = findViewById(R.id.buttonAceptar);
        limpiar = findViewById(R.id.buttonLimpiar);
        error = findViewById(R.id.textViewError);
        error.setTextColor(getResources().getColor(R.color.red));

        aceptar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(nombre.getText().toString().isBlank())
                {
                    error.setText(R.string.error_nombre);
                }
                else if(apellidos.getText().toString().isBlank())
                {
                    error.setText(R.string.error_apellidos);
                }
                else if(edad.getText().toString().isBlank())
                {
                    error.setText(R.string.error_edad);
                }
                else
                {
                    String textoEdad = edad.getText().toString();
                    int edadConseguida = Integer.parseInt(textoEdad);
                    int seleccion = genero.getCheckedRadioButtonId();
                    String verificacion;
                    String generoSeleccionado = "";
                    String infantes;

                    if(edadConseguida < 18)
                    {
                        verificacion = getString(R.string.menor);
                    }
                    else
                    {
                        verificacion = getString(R.string.mayor);
                    }
                    if(hijos.isChecked())
                    {
                        infantes = getString(R.string.hijos_si);
                    }
                    else
                    {
                        infantes = getString(R.string.hijos_no);
                    }
                    if(seleccion != -1)
                    {
                        seleccionado = findViewById(seleccion);
                        generoSeleccionado = seleccionado.getText().toString();
                    }
                    else if(seleccion == -1)
                    {
                        generoSeleccionado = getString(R.string.genero_neutro);
                    }
                    error.setText("");
                    Toast.makeText(MainActivity.this, apellidos.getText().toString() + ", " + nombre.getText().toString() + ", " + verificacion + ", " + generoSeleccionado.toLowerCase() + " " + estado.getSelectedItem().toString().toLowerCase() + " " + infantes, Toast.LENGTH_SHORT).show();
                }
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                nombre.setText("");
                apellidos.setText("");
                edad.setText("");
                genero.clearCheck();
                estado.setSelection(0);
                hijos.setChecked(false);
                error.setText("");
            }
        });
    }
}