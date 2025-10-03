package com.ejst1psp;

import java.util.concurrent.TimeUnit;

public class Ej3 {
    public static void main(String [] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("firefox");
            Process p = pb.start();
            
            boolean pr = p.waitFor(10, TimeUnit.SECONDS);

            if (pr) {
                System.out.println("Proceso ya terminado");
            } else {
                System.out.println("Terminando proceso...");
                p.destroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
