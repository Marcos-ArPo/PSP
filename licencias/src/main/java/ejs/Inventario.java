package ejs;

public class Inventario {
    private int stock;

    public Inventario(int stockInicial) {
        this.stock = stockInicial;
    }

    public synchronized void vender(String empleado) throws InterruptedException {
        while (stock < 1) {
            System.out.println(empleado + " espera: stock agotado.");
            wait();
        }
        stock--;
        System.out.println(empleado + " vendió 1. Stock restante: " + stock);
    }

    public synchronized void reponer(int cantidad, String reponedor) {
        stock += cantidad;
        System.out.println(reponedor + " repuso " + cantidad + ". Stock actual: " + stock);
        notifyAll();
    }

    public synchronized int getStock() {
        return stock;
    }
}
