package es.davante.edadcanina;
//v3_Paso 2: Eliminamos los import de Buttom, EditText y TextView
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.controlador.EdadCaninaControlador;
import es.davante.edadcanina.databinding.ActivityMainBinding;

import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
// v3_Paso 5: Ya no es necesario
//import es.davante.edadcanina.modelo.Validacion;

//Paso 3: Eliminar los import no utilizados

public class MainActivity extends AppCompatActivity {

// v_3 Paso 3: Las constantes se movieron a EdadCaninaModelo
    //Paso 6: Constantes
    //private static final int FACTOR_CANINO = 7;
    //private static final int EDAD_MINIMA = 1;
    //private static final int EDAD_MAXIMA = 30;

    //v3_Paso 2: creado el objeto de DataBinding para acceder directamente.
    private ActivityMainBinding binding;

    //v3_Paso 3: Poner las reglas echas en EdadCaninaModelo
    private EdadCaninaModelo modelo;

    //v3_Paso 2: Eliminamos los atributos no necesarios
    //Paso 7: Atributos
    //private EditText ageEdit;
    //private TextView resultText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //v3_Paso 2: Ya no lo necesitamos
        //setContentView(R.layout.activity_main);
        // Declaramos variables
        // ageEdit = findViewById(R.id.age_input); //Paso 7: utilizamos directamente el atributo
        // resultText = findViewById(R.id.result_text); //Paso 7: utilizamos directamente el atributo
        // Button button1 = findViewById(R.id.btn_calc);

        //v3_Paso 2: Sustituimos los setContentView por DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //v3_Paso 3: Iniciar el modelo
        modelo = new EdadCaninaModelo();

        //v3_Paso 4: Entregamos el modelo al binding para que la vista lo observe
        binding.setModelo(modelo);

        //v3_paso 6: Creamos el controlador conectándolo
        EdadCaninaControlador controlador = new EdadCaninaControlador(modelo);
        binding.setControlador(controlador);

        // Implementar el botón (setOnClickListener)
        //Paso 8: simplificar el botón con un método
        //button1.setOnClickListener(view -> calcularEdad());

    //v3_Paso 2: Accedemos directamente al boton desde binding
        //binding.btnCalc.setOnClickListener(view -> calcularEdad());

        //v_Paso 5: el listener
        binding.btnCalc.setOnClickListener(view -> modelo.calcular());

    }
    //v3_Paso 5: Ya no es necesario porque se a movido a AdaptadoresVista
    //Paso 8: crear método
    //private void calcularEdad(){
    // v3_Paso 4: Yo no necisatos leer el EditText porque tenemos @={modelo.edadTexto}
    // v3_Paso 3: Pasamos la entrada al modelo y delegamos para que valide
        //modelo.setEdadTexto(binding.ageInput.getText().toString());
        //Validacion resultado = modelo.calcular();

    /*
    //v3_Paso 4: Ya no llamamos al binding porque ahora se actualiza solo
    //v3_Paso 3: Pintamos el resultado
        switch (resultado){
            case OK:
                binding.ageInput.setError(null);
                //binding.resultText.setText(getString(R.string.tu_edad_format,modelo.getEdadHumana()));
                break;
            case VACIO:
                mostrarError(getString(R.string.error_vacio));
                break;
            case NO_NUMERO:
                mostrarError(getString(R.string.error_no_numero));
                break;
            case FUERA_DE_RANGO:
                mostrarError(getString(R.string.error_rango,
                        EdadCaninaModelo.EDAD_MINIMA,
                        EdadCaninaModelo.EDAD_MAXIMA));
                break;
            case SIN_CALCULAR:
                binding.ageInput.setError(null);
                //binding.resultText.setText("");
                break;
        }


//v3_Paso 3: Toda esta logica se queda obsoleta porque toda la validación se movió a EdadCaninoModelo
    //Paso 10: Realizar método calcularEdad
        //1. Leer el texto quitando espacios del principio y el final

        //v3_paso 2: Lo cambiamos al binding
        //String ageString = ageEdit.getText().toString().trim();
        //String ageString = binding.ageInput.getText().toString().trim();

        //2. Comprobar si el campo está vacío
        //if (ageString.isEmpty()){
            ///mostrarError(getString(R.string.error_vacio));
            //return;
        //}

        //3. Control de excepciones si no es número entero
        //int age;
        //try {
            //age = Integer.parseInt(ageString);
        //} catch (NumberFormatException e){
            //mostrarError(getString(R.string.error_no_numero));
            //return;
        //}

        //4. Comprobar si está dentro del rango
        //if (age < EDAD_MINIMA || age > EDAD_MAXIMA){
            //mostrarError(getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA));
            //return;
        //}

        //5. Todo correcto
        //ageEdit.setError(null);
        //int dogAge = age * FACTOR_CANINO; //Paso 6
        //resultText.setText(getString(R.string.tu_edad_format, dogAge));

        //v3_Paso 2: Actualizar en binding
        //binding.ageInput.setError(null);
        //int dogAge = age * FACTOR_CANINO;
        //binding.resultText.setText(getString(R.string.tu_edad_format, dogAge));

    }

    //Paso 9: Método errores
    private void mostrarError(String mensaje){
        //1. Error EditText
        //ageEdit.setError(mensaje);
        //2. Foco en el EditText para saber del error
        //ageEdit.requestFocus();
        //3. Borrar el resultado anterior del TextView
        //resultText.setText("");

        //v3_Paso 2: cambiar a binding
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        //v3_Paso 4: Ya no borramos el resultText a mano
        //binding.resultText.setText("");

    }


     */

}