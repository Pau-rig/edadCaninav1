package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Paso 6: Constantes
    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    //Paso 7: Atributos
    private EditText ageEdit;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Declaramos variables
        ageEdit = findViewById(R.id.age_input); //Paso 7: utilizamos directamente el atributo
        resultText = findViewById(R.id.result_text); //Paso 7: utilizamos directamente el atributo
        Button button1 = findViewById(R.id.btn_calc);


        // Implementar el boton (setOnClickListener)
        button1.setOnClickListener(view -> {

            String ageString = ageEdit.getText().toString();
            int age = Integer.parseInt(ageString);
            int dogAge = age * FACTOR_CANINO; //Paso 6
            resultText.setText(getString(R.string.tu_edad_format, dogAge));
        });


    }
}