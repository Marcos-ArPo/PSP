package ejs;

public class ej1 extends Thread{
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hola desde el hilo " + this.getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        ej1 hilo1 = new ej1();
        ej1 hilo2 = new ej1();
        hilo1.start();
        hilo2.start();
    }
}