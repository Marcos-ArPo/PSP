package ej2;

import java.util.Random;

public class Estudiante implements Runnable {
    private static final Libro[] libros = new Libro[9];
    private static final Random random = new Random();
    private final String nombre;
    private volatile boolean activo = true;
    
    // Inicializar array de libros
    static {
        for (int i = 0; i < libros.length; i++) {
            libros[i] = new Libro("LIBRO-" + (i + 1));
        }
    }
    
    public Estudiante(String nombre) {
        this.nombre = nombre;
    }
    
    public void detener() {
        activo = false;
    }
    
    @Override
    public void run() {
        try {
            while (activo && !Thread.currentThread().isInterrupted()) {
                // Seleccionar dos libros distintos al azar
                int indice1 = random.nextInt(libros.length);
                int indice2;
                do {
                    indice2 = random.nextInt(libros.length);
                } while (indice1 == indice2);
                
                Libro libro1 = libros[indice1];
                Libro libro2 = libros[indice2];
                
                // PREVENCIÓN DE INTERBLOQUEO: Ordenar libros por ID
                Libro primero, segundo;
                if (libro1.getId().compareTo(libro2.getId()) < 0) {
                    primero = libro1;
                    segundo = libro2;
                } else {
                    primero = libro2;
                    segundo = libro1;
                }
                
                // Bloquear en orden consistente
                synchronized (primero) {
                    synchronized (segundo) {
                        // Usar los libros
                        int tiempoUso = random.nextInt(3) + 1; // 1-3 "minutos"
                        System.out.println(nombre + " usando " + libro1.getId() + 
                                         " y " + libro2.getId() + " por " + tiempoUso + " min");
                        
                        // Simular uso (1 ms por minuto)
                        for (int i = 0; i < tiempoUso; i++) {
                            Thread.sleep(1);
                            if (!activo) break;
                        }
                    }
                }
                
                // Descansar
                int tiempoDescanso = random.nextInt(2) + 1; // 1-2 "minutos"
                System.out.println(nombre + " descansando por " + tiempoDescanso + " min");
                
                for (int i = 0; i < tiempoDescanso; i++) {
                    Thread.sleep(1);
                    if (!activo) break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(nombre + " ha terminado de estudiar");
    }
    
    // Método para obtener un libro por índice (para pruebas)
    public static Libro getLibro(int indice) {
        if (indice >= 0 && indice < libros.length) {
            return libros[indice];
        }
        return null;
    }
}