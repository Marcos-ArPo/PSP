package ejs;

import java.util.Random;

public class Estudiante extends Thread {
    private final int id;
    private final Libro[] libros;
    private final Random random;
    private volatile boolean ejecutando;
    
    public Estudiante(int id, Libro[] libros, long seed) {
        this.id = id;
        this.libros = libros;
        this.random = new Random(seed + id); // Semilla diferente para cada estudiante
        this.ejecutando = true;
        this.setName("Estudiante-" + id);
    }
    
    @Override
    public void run() {
        SimulacionUtil.log("Estudiante " + id + " ha comenzado");
        
        int ciclo = 0;
        while (ejecutando && !Thread.currentThread().isInterrupted()) {
            ciclo++;
            try {
                // 1. Seleccionar dos libros al azar
                Libro[] librosSeleccionados = seleccionarDosLibros();
                Libro libro1 = librosSeleccionados[0];
                Libro libro2 = librosSeleccionados[1];
                
                SimulacionUtil.log("Estudiante " + id + " (ciclo " + ciclo + 
                                 ") quiere usar " + libro1 + " y " + libro2);
                
                // 2. Adquirir los libros en orden (para evitar deadlock)
                adquirirLibrosOrdenados(libro1, libro2);
                
                // 3. Simular uso de los libros
                int tiempoUso = SimulacionUtil.getTiempoAleatorio(60, 180, random); // 1-3 horas
                SimulacionUtil.log("Estudiante " + id + " usando " + libro1 + 
                                 " y " + libro2 + " por " + tiempoUso + " minutos");
                
                Thread.sleep(SimulacionUtil.minutosToMs(tiempoUso));
                
                // 4. Liberar los libros
                liberarLibros(libro1, libro2);
                SimulacionUtil.log("Estudiante " + id + " liberó " + libro1 + 
                                 " y " + libro2);
                
                // 5. Descansar
                int tiempoDescanso = SimulacionUtil.getTiempoAleatorio(60, 120, random); // 1-2 horas
                SimulacionUtil.log("Estudiante " + id + " descansando por " + 
                                 tiempoDescanso + " minutos");
                
                Thread.sleep(SimulacionUtil.minutosToMs(tiempoDescanso));
                
            } catch (InterruptedException e) {
                SimulacionUtil.log("Estudiante " + id + " interrumpido");
                ejecutando = false;
                Thread.currentThread().interrupt();
            }
        }
        
        SimulacionUtil.log("Estudiante " + id + " ha terminado");
    }
    
    private Libro[] seleccionarDosLibros() {
        int idx1, idx2;
        do {
            idx1 = random.nextInt(SimulacionUtil.TOTAL_LIBROS);
            idx2 = random.nextInt(SimulacionUtil.TOTAL_LIBROS);
        } while (idx1 == idx2);
        
        Libro libro1 = libros[idx1];
        Libro libro2 = libros[idx2];
        
        // Ordenar por ID para evitar deadlock
        if (libro1.getId() > libro2.getId()) {
            Libro temp = libro1;
            libro1 = libro2;
            libro2 = temp;
        }
        
        return new Libro[]{libro1, libro2};
    }
    
    private void adquirirLibrosOrdenados(Libro libro1, Libro libro2) {
        // Ya están ordenados por ID
        libro1.adquirir();
        try {
            libro2.adquirir();
        } catch (Exception e) {
            // Si falla al adquirir el segundo, liberar el primero
            libro1.liberar();
            throw e;
        }
    }
    
    private void liberarLibros(Libro libro1, Libro libro2) {
        // Liberar en orden inverso (buena práctica)
        libro2.liberar();
        libro1.liberar();
    }
    
    public void detener() {
        ejecutando = false;
        this.interrupt();
    }
}
