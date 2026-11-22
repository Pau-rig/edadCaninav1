package es.davante.edadcanina.modelo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Objects;

import es.davante.edadcanina.BR;

//v3_Paso 4: Heredamos de BaseObservable
public class EdadCaninaModelo extends BaseObservable {
// v3_Paso 3: Modelo de la aplicación. contine los datos y reglas
    // v3_Paso 3: Constantes de negocio movidas desde MainActivitiy
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;

    //v3_Paso 4: Es para que obtenga la propiedad el DataBinding
    @Bindable
    public String getEdadTexto() {
        return edadTexto;
    }

    //v3_Paso 3: Regla de negocio: si cambia la entrada, el resultado anterior se elimina y vuelve a SIN_CALCULAR

    public void setEdadTexto(String nuevoTexto) {
        if (nuevoTexto == null){
            nuevoTexto = "";
        }


        if (Objects.equals(nuevoTexto, edadTexto)){
            return;
        }
        this.edadTexto = nuevoTexto;
        //v3_Paso 4: Avisamos a la vista de que ha cambiado edadTexto
        notifyPropertyChanged(BR.edadTexto);

        //this.edadHumana = 0;
        //this.validacion = Validacion.SIN_CALCULAR;

        //v3_Paso 4: Usamos setter privados para notificar el cambio
        setEdadHumana(0);
        setValidacion(Validacion.SIN_CALCULAR);
    }

    //v3_Paso 4: @Bindable para get EdadHumana
    @Bindable
    public int getEdadHumana(){
        return edadHumana;
    }

    //v3_Paso 4: set para EdadHumana
    private void setEdadHumana(int nuevaEdadHumana){
        if (this.edadHumana == nuevaEdadHumana){
            return;
        }
        this.edadHumana = nuevaEdadHumana;
        notifyPropertyChanged(BR.edadHumana);
    }

    //v3_Paso 4: @Bindable para getValidacion
    @Bindable
    public Validacion getValidacion(){
        return validacion;
    }

    //v3_Paso 4: Set privado de Validacion
    private void  setValidacion(Validacion nuevaValidacion){
        if (this.validacion == nuevaValidacion){
            return;
        }
        this.validacion = nuevaValidacion;
        notifyPropertyChanged(BR.validacion);
    }

    //v3_Paso 4: utilizamos los set automaticos
    // v3_Paso 3: Validar entrada y calcular la edad humana
    public Validacion calcular(){
        String textoLimpio = edadTexto.trim();

        //1. comprobar si esta vacio
        if (textoLimpio.isEmpty()) {
            //v3_Paso 4:
            //edadHumana = 0;
            //validacion = Validacion.VACIO;
            setEdadHumana(0);
            setValidacion(Validacion.VACIO);
            return validacion;
        }

        //2. Comprobar si es número entero
        int edadPerro;
        try {
            edadPerro = Integer.parseInt(textoLimpio);
        } catch (NumberFormatException e){
            //v3_Paso 4:
            //edadHumana = 0;
            //validacion = Validacion.NO_NUMERO;
            setEdadHumana(0);
            setValidacion(Validacion.NO_NUMERO);
            return validacion;
        }

        // 3. Comprobar el rango (1 -30)
        if (edadPerro < EDAD_MINIMA || edadPerro > EDAD_MAXIMA) {
            //v3_Paso 4:
            //edadHumana = 0;
            //validacion = Validacion.FUERA_DE_RANGO;
            setEdadHumana(0);
            setValidacion(Validacion.FUERA_DE_RANGO);
            return validacion;
        }

        // 4. Todo correcto
        //v3_Paso 4:
        //edadHumana = edadPerro * FACTOR_CANINO;
        //validacion = Validacion.OK;
        setEdadHumana(edadPerro * FACTOR_CANINO);
        setValidacion(Validacion.OK);
        return validacion;

    }

    //v3_Paso 6: Es para restablecer el modelo después de pulsar limpiar
    public void limpiar(){
        setEdadTexto("");
        setEdadHumana(0);
        setValidacion(Validacion.SIN_CALCULAR);
    }

}
