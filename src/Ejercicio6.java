import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
public class Ejercicio6 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile raf = new RandomAccessFile("personas.dat", "rw")) {
            // Si el archivo está vacío, se crean 3 registros de prueba.
            // Registro de 48 bytes: ID (4) + Nombre (20 chars = 40) + Edad (4)
            if (raf.length() == 0) {
                int[] ids = {1, 2, 3};
                String[] nombres = {"Álvaro", "Lorena", "Gorka"};
                int[] edades = {41, 35, 39};

                for (int i = 0; i < 3; i++) {
                    raf.writeInt(ids[i]);
                    String nombre = String.format("%-20s", nombres[i]).substring(0, 20);
                    for (int j = 0; j < 20; j++) {
                        raf.writeChar(nombre.charAt(j));
                    }
                    raf.writeInt(edades[i]);
                }
                System.out.println("Archivo vacío, se han escrito 3 registros de ejemplo.");
            }
            // Pedir datos por consola.
            System.out.println("Introduce una ID existente: ");
            int idExistente = sc.nextInt();
            System.out.println("Introduce la edad de alguna de las personas: ");
            int edadPersonas = sc.nextInt();

            // Localizamos el registro recorriendo el fichero de forma secuencial.
            long regInicio = 0;
            boolean encontrado = false;
            while (regInicio < raf.length() && !encontrado) {
                raf.seek(regInicio);
                int id = raf.readInt();

                if (id == idExistente) {
                    encontrado = true; // Nos quedamos en este registro.
                } else {
                    regInicio = regInicio + 48; // Saltamos al siguiente registro.
                }
            }
            if (!encontrado) {
                System.out.println("No existe ningún registro con el ID " + idExistente);
            } else {
                raf.seek(regInicio + 44);
                raf.writeInt(edadPersonas);
                // Volvemos al principio del mismo registro mostrándolo completo
                raf.seek(regInicio);
                int id = raf.readInt();

                char[] letras = new char[20];
                for (int j = 0; j < 20; j++) {
                    letras[j] = raf.readChar();
                }
                String nombre = new String(letras).trim();
                int edad = raf.readInt();
                System.out.println("Registro en el byte " + regInicio + " tras la actualización");
                System.out.println("ID: " + id + " | Nombre: " + nombre + " | Edad: " + edad);
            }
        }
    }
}
