package es.davante.edadcanina.controlador;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class EdadCaninaControladorTest {
    // v3_Paso 6: Pruebas del controlador
    private EdadCaninaModelo modelo;
    private EdadCaninaControlador controlador;

    @Before
    public void setUp() {
        modelo = new EdadCaninaModelo();
        controlador = new EdadCaninaControlador(modelo);
    }

    @Test
    public void onCalcularClick_ordenaCalcularAlModelo(){
        modelo.setEdadTexto("6");
        controlador.onCalcularClick();

        assertEquals(Validacion.OK, modelo.getValidacion());
        assertEquals(42, modelo.getEdadHumana());

        }

    @Test
    public void onLimpiarClick_restableceElModelo(){
        modelo.setEdadTexto("6");
        controlador.onCalcularClick();

        controlador.onLimpiarClick();

        assertEquals("", modelo.getEdadTexto());
        assertEquals(0, modelo.getEdadHumana());
        assertEquals(Validacion.SIN_CALCULAR, modelo.getValidacion());

    }



}
