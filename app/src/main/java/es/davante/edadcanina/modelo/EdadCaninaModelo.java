package es.davante.edadcanina.modelo;

import java.util.Objects;

// v3_Paso 3: Modelo de la aplicación. contine los datos y reglas
public class EdadCaninaModelo {

    // v3_Paso 3: Constantes de negocio movidas desde MainActivitiy
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;

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
        this.edadHumana = 0;
        this.validacion = Validacion.SIN_CALCULAR;
    }

    public int getEdadHumana(){
        return edadHumana;
    }

    public Validacion getValidacion(){
        return validacion;
    }

    // v3_Paso 3: Validar entrada y calcular la edad humana
    public Validacion calcular(){
        String textoLimpio = edadTexto.trim();

        //1. comprobar si esta vacio
        if (textoLimpio.isEmpty()) {
            edadHumana = 0;
            validacion = Validacion.VACIO;
            return validacion;
        }

        //2. Comprobar si es número entero
        int edadPerro;
        try {
            edadPerro = Integer.parseInt(textoLimpio);
        } catch (NumberFormatException e){
            edadHumana = 0;
            validacion = Validacion.NO_NUMERO;
            return validacion;
        }

        // 3. Comprobar el rango (1 -30)
        if (edadPerro < EDAD_MINIMA || edadPerro > EDAD_MAXIMA) {
            edadHumana = 0;
            validacion = Validacion.FUERA_DE_RANGO;
            return validacion;
        }

        // 4. Todo correcto
        edadHumana = edadPerro * FACTOR_CANINO;
        validacion = Validacion.OK;
        return validacion;

    }



}
