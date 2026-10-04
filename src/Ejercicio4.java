import java.io.IOException;
import java.io.RandomAccessFile;
public class Ejercicio4 {
    public static void main(String[] args) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile("registros.bin", "rw")) {
            raf.setLength(0);

            int[] ids = {1, 2, 3};
            String[] nombres = {"Álvaro", "Lorena", "Gorka"};
            // Define un registro con id (int, 4 bytes) y nombres (10 chars, 20 bytes) con writeChar.
            for (int i = 0; i < 3; i++) {
                raf.writeInt(ids[i]);
                // Rellenamos con espacios hasta ocupar 10 carácteres:
                String nombre = String.format("%-10s", nombres[i]).substring(0, 10);
                for (int j = 0; j < 10; j++) {
                    raf.writeChar(nombre.charAt(j));
                }
            }
            // Leer directamente el 3º registro.
            raf.seek(2 * 24);
            int id = raf.readInt();

            char[] letras = new char[10];
            for (int j = 0; j < 10; j++) {
                letras[j] = raf.readChar();
            }
            String nombre = new String(letras).trim();
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
        }
    }
}
