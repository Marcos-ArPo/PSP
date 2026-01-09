package ejs;

public class Recurso {

    private int cantidad = 0;
    private final int MAX = 600;

    // Un consumidor intenta coger "n" unidades
    public synchronized void consumir(int n, int id) throws InterruptedException {
        while (cantidad < n) {
            System.out.println("Consumidor " + id + " espera (" + cantidad + "/" + n + ")");
            wait();  // espera no activa
        }

        cantidad -= n;
        System.out.println("Consumidor " + id + " obtiene " + n + " → quedan " + cantidad);
        notifyAll();
    }

    // El suministrador añade producto
    public synchronized void producir(int n) throws InterruptedException {
        while (cantidad + n > MAX) {
            wait();   // espera hasta que haya espacio
        }

        cantidad += n;
        System.out.println("Suministrador repone " + n + " → total " + cantidad);
        notifyAll();
    }
}
