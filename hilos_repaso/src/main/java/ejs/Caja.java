package ejs;

import java.util.Random;

public class Caja extends Thread{
    public void run() {
        Random ran = new Random();
        for (int i = 0; i <= 10; i++) {
            System.out.println(this.getName()+" completada");
            try {
                Thread.sleep(1000*ran.nextInt(1, 6));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.getName()+" cerrada.");
    }

    public static void main(String [] args) {
        Caja Caja1 = new Caja();
        Caja Caja2 = new Caja();
        Caja Caja3 = new Caja();

        Caja1.start();
        Caja2.start();
        Caja3.start();
    }
}
