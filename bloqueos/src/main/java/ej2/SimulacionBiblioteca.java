package ej2;

public class SimulacionBiblioteca {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== SIMULACIÓN BIBLIOTECA - 4 ESTUDIANTES, 9 LIBROS ===");
        
        // Crear estudiantes
        Estudiante[] estudiantes = {
            new Estudiante("Estudiante-1"),
            new Estudiante("Estudiante-2"), 
            new Estudiante("Estudiante-3"),
            new Estudiante("Estudiante-4")
        };
        
        // Crear y iniciar hilos
        Thread[] hilos = new Thread[estudiantes.length];
        for (int i = 0; i < estudiantes.length; i++) {
            hilos[i] = new Thread(estudiantes[i]);
            hilos[i].start();
        }
        
        // Ejecutar simulación por 10 segundos
        Thread.sleep(10000);
        
        System.out.println("\n--- Finalizando simulación ---");
        
        // Detener todos los estudiantes
        for (Estudiante estudiante : estudiantes) {
            estudiante.detener();
        }
        
        // Esperar a que terminen
        for (Thread hilo : hilos) {
            hilo.interrupt();
            hilo.join(1000);
        }
        
        System.out.println("Simulación terminada");
    }
}
