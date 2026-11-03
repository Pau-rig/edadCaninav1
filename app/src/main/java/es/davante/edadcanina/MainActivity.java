package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

//Paso 3: Eliminar los import no utilizados

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



        // Implementar el botón (setOnClickListener)
        //Paso 8: simplificar el botón con un método
        button1.setOnClickListener(view -> calcularEdad());
    }
    //Paso 8: crear método
    private void calcularEdad(){
    //Paso 10: Realizar método calcularEdad
        //1. Leer el texto quitando espacios del principio y el final
        String ageString = ageEdit.getText().toString().trim();

        //2. Comprobar si el campo está vacío
        if (ageString.isEmpty()){
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        //3. Control de excepciones si no es número entero
        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e){
            mostrarError(getString(R.string.error_no_numero));
            return;
        }

        //4. Comprobar si está dentro del rango
        if (age < EDAD_MINIMA || age > EDAD_MAXIMA){
            mostrarError(getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA));
            return;
        }

        //5. Todo correcto
        ageEdit.setError(null);
        int dogAge = age * FACTOR_CANINO; //Paso 6
        resultText.setText(getString(R.string.tu_edad_format, dogAge));


    }

    //Paso 9: Método errores
    private void mostrarError(String mensaje){
        //1. Error EditText
        ageEdit.setError(mensaje);
        //2. Foco en el EditText para saber del error
        ageEdit.requestFocus();
        //3. Borrar el resultado anterior del TextView
        resultText.setText("");
    }


}