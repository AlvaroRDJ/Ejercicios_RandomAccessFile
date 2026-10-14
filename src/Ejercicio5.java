import java.io.IOException;
import java.io.RandomAccessFile;
public class Ejercicio5 {
    public static void main(String[] args) throws IOException {
        // Para cada empleado se debe guardar: identificador, apellido, departamento y salario.
        String[] apellidos = {"Richelle", "Mohedano", "Lorca", "Gabilondo"};
        int[] departamentos = {10, 20, 10, 40};
        double[] salarios = {1850.50, 2100.00, 1990.90, 2500.75};

        try (RandomAccessFile raf = new RandomAccessFile("empleados.dat", "rw")) {
            raf.setLength(0);
            // Escribir los empleados (registros de 36 bytes)
            for (int i = 0; i < apellidos.length; i++) {
                int id = i + 1;
                // el identificador debe ser mayor que 0.
                if (id > 0) {
                    raf.writeInt(id);
                    // Apellido de 10 carácteres (se rellena con espacios)
                    String apellido = String.format("%-10s", apellidos[i]).substring(0, 10);
                    for (int j = 0; j < 10; j++) {
                        raf.writeChar(apellido.charAt(j));
                    }
                    raf.writeInt(departamentos[i]);
                    raf.writeDouble(salarios[i]);
                    // Comprobames que cada registro ocupe siempre lo mismo.
                    System.out.println("Registro " + id + " escrito. Puntero: " + raf.getFilePointer());
                }
            }
            System.out.println("Tamaño total del fichero: " + raf.length() + " bytes.");

            // Recorrer el fichero y mostrar todos los registros.
            raf.seek(0);
            System.out.println("--- Contenido del fichero ---");
            while (raf.getFilePointer() < raf.length()) {
                int id = raf.readInt();

                char[] letras = new char[10];
                for (int j = 0; j < 10; j++) {
                    letras[j] = raf.readChar();
                }
                String apellido = new String(letras).trim();
                int departamento = raf.readInt();
                double salario = raf.readDouble();
                System.out.println("ID: " + id + " | Apellido: " + apellido +
                        " | Departamento: " + departamento + " | Salario: " + salario);
            }
        }
    }
}
