import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile raf = new RandomAccessFile("nums.bin", "rw")) {
            raf.setLength(0);
            // Escribir los 5 números enteros.
            for (int i = 1; i <= 5; i++) {
                System.out.println("Introduce tú nº favorito " + i + ": ");
                int n = sc.nextInt();
                raf.writeInt(n);
            }
            // Posición del puntero tras escribir:
            System.out.println("getFilePointer tras escribir: " + raf.getFilePointer());

            // Volver al inicio y leer:
            raf.seek(0);
            System.out.println("Números leídos del fichero: ");
            for (int i = 0; i < 5; i++) {
                System.out.println(raf.readInt());
            }
        }
    }
}