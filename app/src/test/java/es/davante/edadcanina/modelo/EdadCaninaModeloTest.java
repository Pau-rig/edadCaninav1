package es.davante.edadcanina.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.databinding.Observable;

import org.junit.Before;
import org.junit.Test;

public class EdadCaninaModeloTest {
    // v3_Paso 3: Pruebas unitarias

    private EdadCaninaModelo modelo;

    @Before
    public void setUp(){
        modelo = new EdadCaninaModelo();
    }

    @Test
    public void  edadValidad_calculaPorSiete(){
        modelo.setEdadTexto("6");
        assertEquals(Validacion.OK, modelo.calcular());
        assertEquals(42, modelo.getEdadHumana());
    }

    @Test
    public void  entradaValidad_devuelveVacico(){
        modelo.setEdadTexto(" ");
        assertEquals(Validacion.VACIO, modelo.calcular());
        assertEquals(0, modelo.getEdadHumana());
    }

    @Test
    public void  respuestaNoNumerica_develveNoNumero(){
        modelo.setEdadTexto("abc");
        assertEquals(Validacion.NO_NUMERO, modelo.calcular());
        assertEquals(0, modelo.getEdadHumana());
    }

    @Test
    public void  cero_fueradeRango(){
        modelo.setEdadTexto("0");
        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
    }

    @Test
    public void  treintaUno_fueradeRango(){
        modelo.setEdadTexto("31");
        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
    }

    @Test
    public void  cambiarTextoTrasCalcular_orSiete(){
        modelo.setEdadTexto("6");
        modelo.calcular();
        assertEquals(Validacion.OK, modelo.getValidacion());

        //Con esto si se cambia el numero el resultado anterior deja de ser valido

        modelo.setEdadTexto("5");
        assertEquals(Validacion.SIN_CALCULAR, modelo.getValidacion());
        assertEquals(0, modelo.getEdadHumana());

    }

    //v3_Paso 4: NUeva Prueba Test para verificar que el modelo avisa

    @Test
    public void calcular_noticaCambio(){
        final boolean[] notificado = {false};
        modelo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                notificado[0] = true;
            }
        });

        modelo.setEdadTexto("6");
        notificado[0] = false;
        modelo.calcular();

        assertTrue(notificado[0]);
    }


}
