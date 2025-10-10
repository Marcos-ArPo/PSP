package tarea2;

import java.io.*;

public class ej1 {
    public static void main(String[] args) {
        File f = new File("./proc.txt");
        try {
            ProcessBuilder pb = new ProcessBuilder("ps", "-e");
            pb.redirectOutput(f);
            Process p = pb.start();

            System.out.println("Salida redireccionada");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
