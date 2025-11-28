package ejs;

import java.util.Random;

public class SimulacionUtil {
    public static final int MS_POR_MINUTO = 10; // 1 minuto simulado = 10 ms reales
    public static final int TOTAL_LIBROS = 9;
    public static final int TOTAL_ESTUDIANTES = 4;
    
    // Método para obtener tiempo aleatorio en un rango [min, max] en minutos
    public static int getTiempoAleatorio(int min, int max, Random random) {
        return min + random.nextInt(max - min + 1);
    }
    
    // Método para convertir minutos simulados a ms reales
    public static long minutosToMs(int minutos) {
        return minutos * MS_POR_MINUTO;
    }
    
    // Método para loggear eventos con timestamp relativo
    public static void log(String mensaje) {
        long tiempoActual = System.currentTimeMillis() - Main.tiempoInicio;
        System.out.printf("[T+%5dms] %s%n", tiempoActual, mensaje);
    }
}
