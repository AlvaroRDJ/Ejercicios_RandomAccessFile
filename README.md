# Ejercicios RandomAccessFile

Ejercicios de acceso a datos en Java con la clase `RandomAccessFile`, que permite leer y escribir en cualquier posición de un archivo binario mediante un puntero (`seek` y `getFilePointer`).

## Ejercicios

La numeración de las clases sigue el orden en que se realizaron los ejercicios. La columna "Ejercicio del PDF" indica a qué ejercicio del enunciado completo (Tarea 2 RA1) corresponde cada uno.

| Clase | Ejercicio del PDF | Descripción |
|---|---|---|
| `src/Ejercicio1.java` | 2 | Escritura y lectura secuencial de enteros. Escribe 5 enteros en `nums.bin`, vuelve al inicio con `seek(0)` y los lee. Muestra `getFilePointer()` tras escribir (20 bytes). |
| `src/Ejercicio2.java` | 3 | Acceso directo a slots de 4 bytes. Guarda los enteros en `slots.bin`, cada uno en su slot, y lee solo el segundo con `seek(1 * 4)`. |
| `src/Ejercicio3.java` | 4 | Cadenas cortas con `writeUTF` y `readUTF` en `nombres.bin`. No se puede saltar al segundo nombre sin leer el primero porque la longitud es variable. |
| `src/Ejercicio4.java` | 5 | Registros fijos básicos en `registros.bin`: id (4 bytes) + nombre (10 chars, 20 bytes), 24 bytes por registro. Lee directamente el tercero con `seek(2 * 24)`. |
| `src/Ejercicio5.java` | 1 | Fichero aleatorio de empleados en `empleados.dat`. Registro de 36 bytes (id, apellido de 10 chars, departamento y salario `double`). Comprueba el tamaño con `getFilePointer()` y recorre el fichero mostrando todos los registros. |
| `src/Ejercicio6.java` | 6 | Actualización de un campo dentro de un registro fijo en `personas.dat` (48 bytes). Busca el id saltando de 48 en 48 y, con `seek(regInicio + 44)`, sobrescribe solo la edad sin tocar id ni nombre. |
| `src/Ejercicio7.java` | 7 | Índice por id en archivo aparte. `personas.idx` guarda entradas de 12 bytes (`int` id + `long` offset). Busca el id en el índice y salta directamente al registro en `personas.dat` con `seek(offset)`. |

## Cómo ejecutarlos

Desde la carpeta `src`:

```bash
javac Ejercicio1.java
java Ejercicio1
```

Repite cambiando el número para los demás. Algunos ejercicios dependen de archivos generados por otros:

- El ejercicio 2 lee `nums.bin`, que genera el ejercicio 1.
- El ejercicio 7 lee `personas.dat`, que genera el ejercicio 6.

Hay que ejecutarlos en ese orden la primera vez.

## Datos de tamaño fijo frente a tamaño variable

En los datos de tamaño fijo (un `int`, o registros cuyos campos tienen siempre la misma longitud), cada elemento ocupa siempre los mismos bytes. Por eso su posición se calcula con una fórmula (`offset = número de registro × tamaño del registro`) y se puede saltar con `seek` a cualquiera de ellos, o modificar un solo campo sin tocar el resto.

En los datos de tamaño variable (como las cadenas de `writeUTF`), cada elemento ocupa una cantidad distinta de bytes. Su posición depende de lo que ocuparon los anteriores, así que hay que recorrer el fichero desde el principio. A cambio, no se desperdicia espacio en relleno.

Un índice auxiliar, como el del ejercicio 7, permite combinar ambas ideas: guarda la posición de cada registro y evita recorrer siempre el fichero principal.

## Autor

Álvaro Richelle
