package ej1;

public class PruebaTransferencias {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN TRANSFERENCIAS BANCARIAS ===\n");
        
        // Crear cuentas con diferentes números para probar orden
        Cuenta cuentaA = new Cuenta("CTE-001", 1000);
        Cuenta cuentaB = new Cuenta("CTE-002", 1000);
        
        System.out.println("Cuentas creadas:");
        System.out.println(" - " + cuentaA);
        System.out.println(" - " + cuentaB);
        System.out.println();
        
        // Prueba 1: Versión segura (sin interbloqueo)
        System.out.println("PRUEBA 1: VERSIÓN SEGURA (Sin interbloqueo)");
        System.out.println("==============================================");
        ejecutarPrueba(cuentaA, cuentaB, true, 5000);
        
        // Restablecer saldos
        cuentaA = new Cuenta("CTE-001", 1000);
        cuentaB = new Cuenta("CTE-002", 1000);
        
        System.out.println("\n\nPRUEBA 2: VERSIÓN CON INTERBLOQUEO");
        System.out.println("=====================================");
        ejecutarPrueba(cuentaA, cuentaB, false, 3000);
    }
    
    private static void ejecutarPrueba(Cuenta cuenta1, Cuenta cuenta2, boolean versionSegura, long tiempoEjecucion) {
        Thread hilo1 = new Thread(new HiloTransferencia(cuenta1, cuenta2, 100, versionSegura), "Hilo-1_" + (versionSegura ? "A→B" : "CTE1→CTE2"));
        Thread hilo2 = new Thread(new HiloTransferencia(cuenta2, cuenta1, 100, versionSegura), "Hilo-2_" + (versionSegura ? "B→A" : "CTE2→CTE1"));
        
        System.out.println("Iniciando hilos...");
        hilo1.start();
        hilo2.start();
        
        try {
            // Esperar el tiempo especificado
            Thread.sleep(tiempoEjecucion);
            
            System.out.println("\nTiempo de ejecución completado. Deteniendo hilos...");
            
            // Interrumpir hilos
            hilo1.interrupt();
            hilo2.interrupt();
            
            // Esperar a que terminen
            hilo1.join(1000);
            hilo2.join(1000);
            
            if (hilo1.isAlive() || hilo2.isAlive()) {
                System.out.println("POSIBLE INTERBLOQUEO - Los hilos no terminaron correctamente");
                System.out.println("Hilo-1 vivo: " + hilo1.isAlive());
                System.out.println("Hilo-2 vivo: " + hilo2.isAlive());
            } else {
                System.out.println("Todos los hilos terminaron correctamente");
            }
            
        } catch (InterruptedException e) {
            System.out.println("Prueba interrumpida");
            Thread.currentThread().interrupt();
        }
        
        // Mostrar saldos finales
        System.out.println("\nSaldos finales:");
        System.out.println(" - " + cuenta1);
        System.out.println(" - " + cuenta2);
        
        double total = cuenta1.getSaldo() + cuenta2.getSaldo();
        System.out.println("Saldo total del sistema: " + total);
        System.out.println("Conservación de fondos: " + (Math.abs(total - 2000) < 0.01 ? "CORRECTA" : "ERRÓNEA"));
    }
}