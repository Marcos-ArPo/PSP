package tarea2;

import java.io.*;

public class ej2 {
    public static void main(String [] args) {
        try {
            File f = new File("SVCHOST.txt");

            ProcessBuilder pb = new ProcessBuilder("bash","-c","systemctl list-units --type=service --state=running | grep systemd");
            pb.redirectOutput(f);
            Process p = pb.start();

            System.out.println("Salida redireccionada.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
