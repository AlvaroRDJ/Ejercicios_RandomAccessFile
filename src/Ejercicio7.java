import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
public class Ejercicio7 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // Recorre personas.dat y construye un segundo fichero llamado personas.idx
        try (RandomAccessFile dat = new RandomAccessFile("personas.dat", "r");
             RandomAccessFile idx = new RandomAccessFile("personas.idx", "rw")) {

            idx.setLength(0);
            long numRegistros = dat.length() / 48; // Cada registro ocupa 48 bytes
            for (int i = 0; i < numRegistros; i++) {
                dat.seek(i * 48L); // Inicio del registro i
                int id = dat.readInt(); // Sólo necesitamos el ID

                idx.writeInt(id); // Entrada del índice: ID (4 bytes)
                idx.writeLong(i * 48L); // y su offset (8 bytes) = 12 bytes por entrada
            }
            System.out.println("Índice creado con " + numRegistros + " entradas");
        }
        // Buscar un ID usando el índice
        System.out.println("Introduce el ID buscado: ");
        int idBuscado = sc.nextInt();

        try (RandomAccessFile idx = new RandomAccessFile("personas.idx", "r");
             RandomAccessFile dat = new RandomAccessFile("personas.dat", "r")) {
                 boolean encontrado = false;
                 // Recorrido lineal del índice (entradas de 12 bytes)
                 while (idx.getFilePointer() < idx.length() && !encontrado) {
                     int id = idx.readInt();
                     long offset = idx.readLong();

                     if (id == idBuscado) {
                         encontrado = true;
                         // Saltar directamente a ese registro en personas.dat
                         dat.seek(offset);
                         int idLeido = dat.readInt();

                         char[] letras = new char[20];
                         for (int j = 0; j < 20; j++) {
                             letras[j] = dat.readChar();
                         }
                         String nombre = new String(letras).trim();
                         int edad = dat.readInt();
                         System.out.println("Offset utilizado: " + offset);
                         System.out.println("ID: " + idLeido + " | Nombre: " + nombre + " | Edad: " + edad);
                     }
                 }
                 if (!encontrado) {
                     System.out.println("El ID buscado " + idBuscado + " no está en el índice");
                 }
             }
    }
}
