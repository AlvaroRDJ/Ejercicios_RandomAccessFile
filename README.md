# Ejercicios RandomAccessFile

Ejercicios de acceso a datos en Java con la clase `RandomAccessFile`, que permite leer y escribir en cualquier posición de un archivo binario mediante un puntero (`seek` y `getFilePointer`).

## Ejercicios

| Ejercicio | Archivo | Descripción |
|---|---|---|
| 1 | `src/Ejercicio1.java` | Escribe 5 enteros en `nums.bin`, vuelve al inicio con `seek(0)` y los lee. Muestra `getFilePointer()` tras escribir (20 bytes). |
| 2 | `src/Ejercicio2.java` | Guarda los enteros en `slots.bin`, cada uno en un slot de 4 bytes, y lee solo el segundo con `seek(1 * 4)`. |
| 3 | `src/Ejercicio3.java` | Escribe tres nombres en `nombres.bin` con `writeUTF` y los recupera con `readUTF`. No se puede saltar al segundo sin leer el primero porque la longitud es variable. |
| 4 | `src/Ejercicio4.java` | Registros de tamaño fijo en `registros.bin` (id de 4 bytes + nombre de 10 chars, 20 bytes; 24 bytes por registro). Lee directamente el tercero con `seek(2 * 24)`. |

## Cómo ejecutarlos

Desde la carpeta `src`:

```bash
javac Ejercicio1.java
java Ejercicio1
```

Repite cambiando el número para los demás. El ejercicio 2 necesita el archivo `nums.bin`, que genera el ejercicio 1, así que hay que ejecutarlos en orden.

## Autor

Álvaro Richelle
