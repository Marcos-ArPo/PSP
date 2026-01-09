package ejs;

import java.util.Random;

public class Consumidor extends Thread {

    private final Recurso recurso;
    private final int id;
    private final Random rnd = new Random();

    public Consumidor(Recurso r, int id) {
        this.recurso = r;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int necesita = 10 + rnd.nextInt(16); // 10..25
                recurso.consumir(necesita, id);

                int tiempo = 100 + rnd.nextInt(401); // 100..500 ms
                Thread.sleep(tiempo);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumidor " + id + " termina.");
        }
    }
}
