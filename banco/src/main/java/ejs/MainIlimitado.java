package ejs;

import java.util.*;

public class MainIlimitado {
    public static void main(String[] args) {
        System.out.println("=== SIMULACIÓN CON NÚMERO ILIMITADO DE CAJEROS ===\n");
        
        // Crear la caja fuerte con 10.000 € iniciales
        CajaFuerte cajaFuerte = new CajaFuerte(10000);
        
        // Crear un número ilimitado de cajeros
        List<Cajero> cajeros = new ArrayList<>();
        int numCajeros = 10; // Puedes cambiar este valor
        
        System.out.println("Creando " + numCajeros + " cajeros...\n");
        
        for (int i = 1; i <= numCajeros; i++) {
            Cajero cajero = new Cajero(cajaFuerte, "Cajero-" + i);
            cajeros.add(cajero);
            cajero.start();
        }
        
        // Hilo para reponer dinero automáticamente
        Thread reponedor = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep((int) (Math.random() * 3000) + 2000);
                    int cantidadReponer = (int) (Math.random() * 2000) + 1000;
                    cajaFuerte.depositar(cantidadReponer, "Reponedor");
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        reponedor.start();
        
        // Ejecutar por 20 segundos
        try {
            Thread.sleep(20000);
            
            System.out.println("\n=== FINALIZANDO SIMULACIÓN ===\n");
            
            // Interrumpir todos los cajeros
            for (Cajero cajero : cajeros) {
                cajero.interrupt();
            }
            reponedor.interrupt();
            
            // Esperar a que todos terminen
            for (Cajero cajero : cajeros) {
                cajero.join();
            }
            reponedor.join();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nSaldo final: " + cajaFuerte.getDineroDisponible() + " €");
    }
}
