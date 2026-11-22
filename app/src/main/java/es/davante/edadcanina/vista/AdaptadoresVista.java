package es.davante.edadcanina.vista;

import android.content.Context;
import android.widget.EditText;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import es.davante.edadcanina.R;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class AdaptadoresVista {

    //v3_Paso 5: Esta clase es para traducir los estdo del modelo en mensajes visuales en el XML
    private AdaptadoresVista(){

    }
        //v3_Pasos 5: Crear atributos XML
        @BindingAdapter("errorValidacion")
        public static void mostrarErrorValidacion(EditText campo, Validacion validacion){
            if (validacion == null) {
                campo.setError(null);
                return;
            }

            Context context = campo.getContext();
            String mensajeError = null;

            switch (validacion){
                case VACIO:
                    mensajeError = context.getString(R.string.error_vacio);
                    break;
                case NO_NUMERO:
                    mensajeError = context.getString(R.string.error_no_numero);
                    break;
                case FUERA_DE_RANGO:
                    mensajeError = context.getString(R.string.error_rango,
                            EdadCaninaModelo.EDAD_MINIMA,
                            EdadCaninaModelo.EDAD_MAXIMA);
                    break;
                case OK:
                case SIN_CALCULAR:
                    mensajeError = null;
                    break;
            }

            //Cuando gay error lo mostrara en el campo
            campo.setError(mensajeError);
            if (mensajeError != null){
                campo.requestFocus();
            }

        }



}
