package ejs;

public class Buffer {
    private int dato;
    private boolean disponible = false;

    public Buffer() {
        this.disponible = false;
    }

    public synchronized void producir(int valor) {
        try {
            while (disponible) {
                wait();
            }
            this.dato = valor;
            this.disponible = true;
            System.out.println("Producido: " + valor);
            notify();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized int consumir() {
        int valor = -1;
        try {
            while (!disponible) {
                wait();
            }
            valor = this.dato;
            this.disponible = false;
            System.out.println("Consumido: " + valor);
            notify();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return valor;
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread productor = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                buffer.producir(i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumidor = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                buffer.consumir();
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        productor.start();
        consumidor.start();

        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Ejecución finalizada.");
    }
}
