import java.io.IOException;
import java.io.RandomAccessFile;
public class Ejercicio2 {
    public static void main(String[] args) throws IOException {
        int[] numeros = new int[5];
        // Leer los números enteros del Ejercicio 1
        try (RandomAccessFile origen = new RandomAccessFile("nums.bin", "r")) {
            for (int i = 0; i < 5; i++) {
                numeros[i] = origen.readInt();
            }
        }
        try (RandomAccessFile raf = new RandomAccessFile("slots.bin", "rw")) {
            raf.setLength(0);
            // Cada nº entero en su slot de 4 bytes: posición i * 4
            for (int i = 0; i < 5; i++) {
                raf.seek(1 * 4);
                raf.writeInt(numeros[i]);
            }
            // Leer sólo el 2º valor sin leer los demás y muéstralo en la consola
            raf.seek(1 * 4);
            System.out.println("El 2º valor: " + raf.readInt());
        }
    }
}
