package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class pruebaFalopaGrego {
    public static void main(String[] args) {
        // Ruta del archivo
        String rutaArchivo = "Ejecutables/ArchivoConfiguracion.txt";
        
        // Variables para guardar las palabras
        String strategy = null;
        String persistencia = null;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // Leer la primera línea
            strategy = br.readLine();
            // Leer la segunda línea
            persistencia = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir las palabras leídas
        System.out.println("strategy: " + strategy);
        System.out.println("persistencia: " + persistencia);
    }

}
