package ejs;

import java.util.Random;

public class Suministrador extends Thread {

    private final Recurso recurso;
    private final Random rnd = new Random();

    public Suministrador(Recurso r) {
        this.recurso = r;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int cantidad = 50 + rnd.nextInt(71); // 50..120
                recurso.producir(cantidad);
                Thread.sleep(1000); // 1 segundo
            }
        } catch (InterruptedException e) {
            System.out.println("Suministrador termina.");
        }
    }
}
