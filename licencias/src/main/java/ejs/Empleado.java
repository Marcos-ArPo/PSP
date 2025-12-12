package ejs;

import java.util.Random;

public class Empleado extends Thread {
    private Inventario inventario;
    private int ventasRealizadas = 0;
    private static final Random rand = new Random();

    public Empleado(String nombre, Inventario inventario) {
        super(nombre);
        this.inventario = inventario;
    }

    @Override
    public void run() {
        try {
            while (ventasRealizadas < 10) { // Realiza solo 10 ventas
                inventario.vender(getName());
                ventasRealizadas++;
                Thread.sleep(rand.nextInt(301) + 100); // Simula tiempo de atención
            }
            System.out.println(getName() + " finalizó después de " + ventasRealizadas + " ventas.");
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrumpido.");
        }
    }
}
