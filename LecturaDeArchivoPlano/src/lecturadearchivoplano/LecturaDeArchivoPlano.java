package lecturadearchivoplano;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LecturaDeArchivoPlano {

    public static void main(String[] args){
        System.out.println("ESTE PROGRAMA LEE ARCHIVOS PLANOS");
        System.out.println("Ingrese la ruta del archivo: ");
        Scanner entrada = new Scanner(System.in);
        String ruta = entrada.nextLine();
        
        leerArchivo(ruta);
    }

    private static void leerArchivo(String ruta){
        try{
           BufferedReader archivoMemoria = new BufferedReader(new FileReader(ruta));
           String lineaActual = "";
           while (lineaActual != null);{
                String lienaActual = archivoMemoria.readLine();
                
                if (lienaActual != null ){
                    System.out.println();
                }
            }
           archivoMemoria.close();
           
        } catch (IOException e){
            System.out.println("Archivo no encontrado");
        }
    }
    
}
