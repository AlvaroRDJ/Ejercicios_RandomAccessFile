import java.io.IOException;
import java.io.RandomAccessFile;
public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile("nombres.bin", "rw")) {
            raf.setLength(0);
            // Escribe 3 nombres cortos usando writeUTF:
            raf.writeUTF("Álvaro");
            raf.writeUTF("Lorena");
            raf.writeUTF("Gorka");

            // Volver al inicio y leerlos:
            raf.seek(0);
            for (int i = 0; i < 3; i++) {
                System.out.println(raf.readUTF());
            }
        }
    }
}
