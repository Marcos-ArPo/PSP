package ejs;

public class ej2 implements Runnable{
    private int c = 0;

    public void run() {
        for (int i=0; i<1000; i++) {
            c++;
            System.out.println("Valor de c: " + c);

        }
    }
    public static void main(String [] args) {
        ej2 obj = new ej2();
        Thread hilo1 = new Thread(obj);
        Thread hilo2 = new Thread(obj);
        hilo1.start();
        hilo2.start();
        System.out.println("EL resultado es 2000 ya que estan desioncronizados los hilos, y van haciendo cuentas separadas.");
    }
}
