package practica;

import java.io.*;

public class ej3 {
    public static void main(String [] args) {
        try {
            File f = new File("procesos.txt");

            ProcessBuilder pb = new ProcessBuilder("ps","-e");
            pb.redirectOutput(f);
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String lin;
            System.out.println("Procesos: ");
            while ((lin = br.readLine()) != null) {
                System.out.println(lin);
            }

            System.out.println("\nSalida redireccionada al archivo procesos.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
