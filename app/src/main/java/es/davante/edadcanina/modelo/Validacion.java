package es.davante.edadcanina.modelo;

//v3_Paso 3: Enum con los posibles estados de validación
public enum Validacion {
    SIN_CALCULAR, //Estado inicial
    OK, //Entrada válida y calculada
    VACIO, //El campo está vacío
    NO_NUMERO, //El texto no es un numero
    FUERA_DE_RANGO //El número está fuera del rango establecido
}
