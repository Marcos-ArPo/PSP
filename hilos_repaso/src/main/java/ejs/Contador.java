package ejs;

public class Contador implements Runnable{
    private static int c = 0;

    public void run() {
        for (int i = 1;i <= 1000; i++) {
            getC();
        }
    }

    private synchronized void getC() {
        c++;
    }

    public static void main(String [] args) {
        Contador obj = new Contador();
        Thread t1 = new Thread(obj);
        Thread t2 = new Thread(obj);
        Thread t3 = new Thread(obj);
        Thread t4 = new Thread(obj);
        Thread t5 = new Thread(obj);
        Thread t6 = new Thread(obj);
        Thread t7 = new Thread(obj);
        Thread t8 = new Thread(obj);
        Thread t9 = new Thread(obj);
        Thread t10 = new Thread(obj);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Contador final --> "+c);
    }
}
