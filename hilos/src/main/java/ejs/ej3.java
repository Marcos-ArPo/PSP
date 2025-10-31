package ejs;

public class ej3 implements Runnable {
    private int c = 0;

    public void run() {
        for (int i = 0; i < 1000; i++) {
            getC();
        }
    }

    public synchronized void getC() {
        c++;
    }

    public static void main(String[] args) {
        ej3 obj = new ej3();
        Thread hilo1 = new Thread(obj);
        Thread hilo2 = new Thread(obj);
        hilo1.start();
        hilo2.start();
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El resultado es: " + obj.c);
    }
}
