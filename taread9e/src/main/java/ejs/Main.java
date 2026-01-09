package ejs;

public class Main {
    public static void main(String[] args) {

        Recurso recurso = new Recurso();

        // Arrancar suministrador
        Suministrador s = new Suministrador(recurso);
        s.start();

        // Arrancar 10 consumidores
        for (int i = 1; i <= 10; i++) {
            new Consumidor(recurso, i).start();
        }
    }
}
