# 🐶 EdadCanina · v1

Aplicación Android (Java) que calcula la edad "humana" de un perro multiplicando su edad por 7.

Esta es la **versión 1**: funciona, pero no comprueba lo que escribe el usuario. Tu tarea es convertirla en la **versión 2** (rama `v2-validacion`), que valida la entrada y controla los errores sin que la app se cierre.

---

## Objetivos

Al terminar sabrás:

- Detectar por qué una app se cierra (`NumberFormatException`) y evitarlo con `try/catch`.
- Validar datos de entrada: campo vacío, texto no numérico y rango de valores.
- Mostrar errores al usuario con `EditText.setError()`.
- Sustituir "números mágicos" por constantes.
- Llevar los textos a `strings.xml` y usar formatos con varios parámetros.
- Trabajar en una rama de Git distinta de `main`.

---

## Paso 0 · Prepara el proyecto

1. Haz un **fork** de este repositorio (botón *Fork*) y clona tu fork:
   ```bash
   git clone https://github.com/TU_USUARIO/edadCaninav1.git
   ```
2. Ábrelo en Android Studio y ejecútalo en el emulador.

## Paso 1 · Rompe la app (a propósito)

Antes de arreglar nada, comprueba qué falla. Prueba estas entradas y anota qué pasa:

| Entrada | ¿Qué ocurre? |
|---|--------------|
| *(campo vacío)* |              |
| `abc` |              |
| `3.5` |              |
| `0` |              |
| `-4` |              |
| `99999999999` |              |
| `400000000` |              |

> 💡 Cuando la app se cierre, abre el **Logcat** y busca la excepción. ¿Qué método la lanza? ¿Por qué el último caso no cierra la app pero da un resultado absurdo?

## Paso 2 · Crea la rama de trabajo

```bash
git checkout -b v2-validacion
```

Todo lo que hagas a partir de aquí irá en esta rama; `main` se queda como está.

## Paso 3 · Corrige los fallos que ya trae la v1

Antes de añadir nada nuevo, limpia lo que hay:

1. **`activity_main.xml`**: busca la etiqueta de cierre `</TextView>>` y quita el `>` que sobra.
2. **`strings.xml`**: en `tu_edad_format` hay una falta de ortografía ("fuero"). Corrígela.
3. **Texto escrito "a fuego"**: el `android:hint="Ejemplo: 6"` del `EditText` está directamente en el layout. Llévalo a `strings.xml` con el nombre `hint_edad` y úsalo con `@string/hint_edad`.
   > 💡 Android Studio marca este aviso en amarillo (*Hardcoded string*). Prueba `Alt + Enter` encima.
4. **`MainActivity.java`**: hay tres `import` que no se usan (`Insets`, `ViewCompat`, `WindowInsetsCompat`). Elimínalos (`Ctrl + Alt + O`).

## Paso 4 · Añade los mensajes de error

En `res/values/strings.xml` añade tres textos nuevos:

```xml
<string name="error_vacio">Introduce la edad de tu perro</string>
<string name="error_no_numero">Introduce un número entero válido</string>
<string name="error_rango">La edad debe estar entre %1$d y %2$d años</string>
```

> ❓ ¿Qué significan `%1$d` y `%2$d`? ¿En qué orden se rellenan?

## Paso 5 · Restringe la entrada desde el layout

En el `EditText` (`@+id/age_input`) añade:

```xml
android:inputType="number"
android:maxLength="2"
android:gravity="center"
android:importantForAutofill="no"
```

| Atributo | Para qué sirve |
|---|---|
| `inputType="number"` | Muestra el teclado numérico: sin letras, sin signo menos ni decimales. |
| `maxLength="2"` | No deja escribir más de 2 cifras. |
| `gravity="center"` | Centra el número en el campo. |
| `importantForAutofill="no"` | Evita que el sistema ofrezca autocompletar. |

> ⚠️ Esto **no sustituye** a la validación en Java: el campo puede quedarse vacío y nunca conviene fiarse solo de la interfaz. Por eso seguimos con el código.

## Paso 6 · Sustituye los números mágicos por constantes

Al principio de la clase `MainActivity` declara:

```java
private static final int FACTOR_CANINO = 7;
private static final int EDAD_MINIMA = 1;
private static final int EDAD_MAXIMA = 30; // ningún perro supera esta edad
```

y usa `FACTOR_CANINO` en lugar del `7`.

## Paso 7 · Convierte las vistas en atributos de la clase

Ahora `ageEdit` y `resultText` son variables locales de `onCreate()`. Como vas a usarlas desde otros métodos, decláralas como atributos:

```java
private EditText ageEdit;
private TextView resultText;
```

y en `onCreate()` asígnalas **sin volver a escribir el tipo**:

```java
ageEdit = findViewById(R.id.age_input);
resultText = findViewById(R.id.result_text);
```

> ❓ ¿Qué pasaría si dejaras `EditText ageEdit = findViewById(...)` dentro de `onCreate()`?

## Paso 8 · Saca la lógica del botón a un método

El `setOnClickListener` debe quedar así de corto:

```java
button1.setOnClickListener(view -> calcularEdad());
```

Toda la lógica pasa a un método nuevo: `private void calcularEdad()`.

## Paso 9 · Crea un método para mostrar errores

```java
private void mostrarError(String mensaje) {
    // 1. Mostrar el mensaje en el EditText (setError)
    // 2. Poner el foco en el EditText (requestFocus)
    // 3. Borrar el resultado anterior del TextView
}
```

## Paso 10 · Valida la entrada en `calcularEdad()`

Implementa el método siguiendo este orden. En cuanto algo falle, llama a `mostrarError(...)` y sal con `return`:

1. **Leer** el texto del `EditText` quitando espacios (`trim()`).
2. **¿Está vacío?** → `error_vacio`. Recuerda que `Integer.parseInt("")` lanza `NumberFormatException`.
3. **Convertir a `int`** dentro de un `try/catch (NumberFormatException e)` → si falla, `error_no_numero`.
4. **¿Está en rango?** Si `age < EDAD_MINIMA || age > EDAD_MAXIMA` → `error_rango`. Para rellenar `%1$d` y `%2$d`:
   ```java
   getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA)
   ```
5. **Todo correcto**: quita el error del campo (`setError(null)`), calcula `age * FACTOR_CANINO` y muestra el resultado con `tu_edad_format`.

> ❓ ¿Por qué limitar la edad a 30 evita también el desbordamiento que viste en el Paso 1?

## Paso 11 · Vuelve a probar

Comprueba que **la app nunca se cierra**:

| Entrada | Resultado esperado |
|---|---|
| *(vacío)* | Error: "Introduce la edad de tu perro" |
| `0` | Error: "La edad debe estar entre 1 y 30 años" |
| `31` | Error de rango |
| `1` | "Si tu perro fuera humano tendría 7 años" |
| `30` | "Si tu perro fuera humano tendría 210 años" |

> 💡 Con el teclado numérico no puedes escribir `abc` ni `-4`. ¿Cómo comprobarías que el `try/catch` funciona? (Pista: quita `inputType` un momento.)

## Paso 12 · Guarda y sube tu trabajo

```bash
git add .
git commit -m "Validación de la entrada y control de errores aritméticos"
git push -u origin v2-validacion
```

Comprueba en GitHub que tu fork tiene ahora dos ramas: `main` y `v2-validacion`.

---

## ✅ Lista de comprobación

- [ ] La app no se cierra con ninguna entrada.
- [ ] Todos los textos están en `strings.xml` (ningún aviso *Hardcoded string*).
- [ ] No quedan números mágicos en `MainActivity`.
- [ ] `onCreate()` solo prepara la pantalla; la lógica está en `calcularEdad()`.
- [ ] El trabajo está en la rama `v2-validacion`, no en `main`.

---

# Versión 2 · Correcciones

## Paso 1 · Rompe la app (a propósito)

| Entrada | ¿Qué ocurre?                                                                                                |
|---|-------------------------------------------------------------------------------------------------------------|
| *(campo vacío)* | La app se cierra nada mas pulsar el boton 'Calcular'.                                                       |
| `abc` | La app se cierra nada mas pulsar el boton 'Calcular'.                                                       |
| `3.5` | La app se cierra nada mas pulsar el boton 'Calcular'.                                                       |
| `0` | La app no se cierra, pero muestra que el perro tiene 0 años por lo que el resultado no es valido.           |
| `-4` | La app no se cierra, pero muestra que el perro tiene -28 años por lo que el resultado no es valido.         |
| `99999999999` | La app se cierra nada mas pulsar el boton 'Calcular'.                                                       |
| `400000000` | La app no se cierra, pero muestra que el perro tiene -1494967296 años por lo que el resultado no es valido. |

### Análisis del Logcat
- **Campo vacío** 
  - **Método:** El método que genera la excepción lanzada es *java.lang.Integer.parseInt()*.
  - **Excepción:** La excepción es java.lang.NumberFormatException. Esto ocurre porque Interger.parseInt() no puede convertir cadenas vacías y al no encontrar con un try/catch la app colapsa y se cierra automáticamente.  
- **abc**
   - **Método:** El método que genera la excepción lanzada es *java.lang.Integer.parseInt()*.
   - **Excepción:** La excepción es java.lang.NumberFormatException. Esto ocurre porque Interger.parseInt() no puede convertir letras y al no encontrar con un try/catch la app colapsa y se cierra automáticamente. 
- **3.5**
   - **Método:** El método que genera la excepción lanzada es *java.lang.Integer.parseInt()*.
   - **Excepción:** La excepción es java.lang.NumberFormatException. Esto ocurre porque Interger.parseInt() no puede convertir números decimales y al no encontrar con un try/catch la app colapsa y se cierra automáticamente. 
- **0**
   - No salta ninguna excepción.
   - El resultado es válido en Java, pero el resultado es ilógico porque no contemplamos colocar un rango mínimo.
- **-4**
   - No salta ninguna excepción.
   - El resultado es válido en Java porque entiende el signo negativo y procesa el entero, pero la edad negativa y es ilógica
- **99999999999**
   - **Método:** El método que genera la excepción lanzada es *java.lang.Integer.parseInt()*.
   - **Excepción:** La excepción es java.lang.NumberFormatException. Esto ocurre porque Interger.parseInt() no puede convertir números enteros que excedan el límite de 32 bits y al no encontrar con un try/catch la app colapsa y se cierra automáticamente.
- **400000000**
   - No salta ninguna excepción.
   - El número no supera el límite en Java, pero al multiplicarlo por 7 la cifra sobrepasa el límite máximo del entero. Esto provoca que el número se invierta y de un número negativo.

## Paso 2 · Rama creada
Creamos la rama con: 
```bash
git checkout -b v2-validacion
```

## Paso 3 · Corrige los fallos que ya trae la v1

1. **activity_main.xml**
Se repite el cierre en *</TextView>>* la solucion es borrar el segudo cierre.
2. **strings.xml**
Hay una falta de ortografía en te_edad_format "fuero" se corrige por "fuera".
3. **EditText**
Cambiamos el EditText el texto *"Ejemplo: 6"* por un string con el mismo texto y el string lo escribimos con el nombre hint_edad.
4. **MainActivity.java**
Entro en el MainActivity.java y con el *Ctrl + Alt + O*.

## Paso 4 · Añade los mensajes de error

Realizamos los tres nuevos textos en el strings.xml:
```bash
<string name="error_vacio">Introduce la edad de tu perro</string>
<string name="error_no_numero">Introduce un número entero válido</string>
<string name="error_rango">La edad debe estar entre %1$d Y %2$d años</string>
```
### Preguntas

**1. ¿Qué significan %1$d y %2$d?**
Son formatos que actúan como un comodín para insertar valores numéricos enteros.
La *d* viene de decimal/integer y el número de antes $d indica la posición del argumento que reemplazará al texto.

**2. ¿En qué orden se rellenan?**

Al colorar 1 o 2 antes del *$d* indicamos el orden con el que reemplazamos los parametros en Java. El número 1 se remplanzara por el primer parámetro que le pases y el 2 por el segundo.

## Paso 5 · Restringe la entrada desde el layout
Modificamos la etiqueta EditText con los siguientes atributos:

| Atributo | Para qué sirve |
|---|---|
| `inputType="number"` | Muestra el teclado numérico: sin letras, sin signo menos ni decimales. |
| `maxLength="2"` | No deja escribir más de 2 cifras. |
| `gravity="center"` | Centra el número en el campo. |
| `importantForAutofill="no"` | Evita que el sistema ofrezca autocompletar. |

## Paso 6 · Sustituye los números mágicos por constantes

Crear las contánte en el MainActivity y cambiar el siete por el *FACTOR_CANINO*. 

## Paso 7 · Convierte las vistas en atributos de la clase

Realizamos la declaración de los atributos y quitamos la parte declarada de en onCreate() 

### Pregunta

**¿Qué pasaría si dejaras EditText ageEdit = findViewById(...) dentro de onCreate()?**

Pasaría que solo existiría como una variable local y solo se puede utilizar dentro de onCreate() y no podríamos reutilizarlo en otros métodos porque el código daría un error de compilación.

## Paso 8 · Saca la lógica del botón a un método

Realizamos la simplification del *setClickListenaer* llevando toda la lógica a un método calcularEdad()