package ejs;

public class Reponedor extends Thread {
    private Inventario inventario;

    public Reponedor(Inventario inventario) {
        super("Reponedor");
        this.inventario = inventario;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (inventario) {
                    if (inventario.getStock() < 5) {
                        inventario.reponer(10, getName());
                    }
                }
                Thread.sleep(500); // Revisa cada 500 ms
            }
        } catch (InterruptedException e) {
            System.out.println("Reponedor interrumpido.");
        }
    }
}
