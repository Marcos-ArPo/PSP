package ej1;

import java.util.Random;

public class HiloTransferencia implements Runnable {
    private Cuenta origen;
    private Cuenta destino;
    private double montoMax;
    private boolean usarVersionSegura;
    private Random random;
    private int numeroTransferencias;

    public HiloTransferencia(Cuenta origen, Cuenta destino, double montoMax, boolean usarVersionSegura) {
        this.origen = origen;
        this.destino = destino;
        this.montoMax = montoMax;
        this.usarVersionSegura = usarVersionSegura;
        this.random = new Random();
        this.numeroTransferencias = 0;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && numeroTransferencias < 20) {
                double monto = 10 + random.nextDouble() * (montoMax - 10);
                boolean exito;
                
                if (usarVersionSegura) {
                    exito = GestorTransferencias.transferirSegura(origen, destino, monto);
                } else {
                    exito = GestorTransferencias.transferirConInterbloqueo(origen, destino, monto);
                }
                
                numeroTransferencias++;
                
                // Espera aleatoria entre transferencias
                int espera = random.nextInt(50);
                Thread.sleep(espera);
                
                System.out.println(Thread.currentThread().getName() + 
                                 " - Transferencia " + numeroTransferencias + 
                                 (exito ? " exitosa" : " fallida") + 
                                 " - Espera: " + espera + "ms");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrumpido");
            Thread.currentThread().interrupt();
        }
        
        System.out.println(Thread.currentThread().getName() + " finalizado. Total transferencias: " + numeroTransferencias);
    }
}