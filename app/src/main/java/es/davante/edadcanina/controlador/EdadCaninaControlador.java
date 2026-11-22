package es.davante.edadcanina.controlador;

//v3_Paso 6: Es un controlador para el patron MVC recive las instrucciones desde el XML y da órdenes del Modelo

import es.davante.edadcanina.modelo.EdadCaninaModelo;

public class EdadCaninaControlador {

    private final EdadCaninaModelo modelo;

    public EdadCaninaControlador(EdadCaninaModelo modelo){
        this.modelo = modelo;
    }

    //Acción al pulsar el boton calcular
    public void onCalcularClick(){
        modelo.calcular();
    }

    //Acción al pulsar el boton limpiar
    public void onLimpiarClick(){
        modelo.limpiar();
    }


}
