package ejs;

public class Main {
    public static long tiempoInicio;
    
    public static void main(String[] args) {
        tiempoInicio = System.currentTimeMillis();
        SimulacionUtil.log("Iniciando simulación de biblioteca");
        
        // Crear array de libros
        Libro[] libros = new Libro[SimulacionUtil.TOTAL_LIBROS];
        for (int i = 0; i < libros.length; i++) {
            libros[i] = new Libro(i + 1); // IDs del 1 al 9
        }
        
        // Crear estudiantes
        Estudiante[] estudiantes = new Estudiante[SimulacionUtil.TOTAL_ESTUDIANTES];
        long seed = System.currentTimeMillis(); // Semilla para reproducibilidad
        
        for (int i = 0; i < estudiantes.length; i++) {
            estudiantes[i] = new Estudiante(i + 1, libros, seed); // IDs del 1 al 4
        }
        
        // Iniciar estudiantes
        for (Estudiante estudiante : estudiantes) {
            estudiante.start();
        }
        
        // Ejecutar la simulación por un tiempo determinado
        int duracionSimulacionMs = 60000; // 1 minuto real = 100 horas simuladas aprox.
        
        try {
            SimulacionUtil.log("Simulación ejecutándose por " + 
                             (duracionSimulacionMs / 1000) + " segundos...");
            Thread.sleep(duracionSimulacionMs);
            
            // Detener la simulación
            SimulacionUtil.log("Deteniendo simulación...");
            for (Estudiante estudiante : estudiantes) {
                estudiante.detener();
            }
            
            // Esperar a que todos los estudiantes terminen
            for (Estudiante estudiante : estudiantes) {
                estudiante.join(2000);
            }
            
        } catch (InterruptedException e) {
            SimulacionUtil.log("Main interrumpido");
            Thread.currentThread().interrupt();
        }
        
        SimulacionUtil.log("Simulación finalizada");
    }
}
