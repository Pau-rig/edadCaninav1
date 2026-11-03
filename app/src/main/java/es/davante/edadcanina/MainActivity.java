package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Declaramos variables
        EditText ageEdit = findViewById(R.id.age_input);
        TextView resultText = findViewById(R.id.result_text);
        Button button1 = findViewById(R.id.btn_calc);


        // Implementar el boton (setOnClickListener)
        button1.setOnClickListener(view -> {

            String ageString = ageEdit.getText().toString();
            int age = Integer.parseInt(ageString);
            int dogAge = age * 7;
            resultText.setText(getString(R.string.tu_edad_format, dogAge));
        });


    }
}