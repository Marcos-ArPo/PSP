package practica;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ej1 {
    public static void main(String[] args) {
        String proc = "gedit";
        int c = 0;

        try {
            ProcessBuilder pb = new ProcessBuilder("ps", "-e");
            Process pro = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));

            String lin;
            while ((lin = br.readLine()) != null) {
                if (lin.contains(proc)) {
                    c++;
                }
            }

            System.out.println("Procesos encontrados --> "+c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
