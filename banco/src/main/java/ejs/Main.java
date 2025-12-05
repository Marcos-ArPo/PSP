package ejs;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SIMULACIÓN DE CAJEROS AUTOMÁTICOS ===\n");
        
        // Crear la caja fuerte con 10.000 € iniciales
        CajaFuerte cajaFuerte = new CajaFuerte(10000);
        System.out.println("Caja fuerte creada con " + cajaFuerte.getDineroDisponible() + " €\n");
        
        // Crear 3 cajeros automáticos
        Cajero cajero1 = new Cajero(cajaFuerte, "Cajero-1");
        Cajero cajero2 = new Cajero(cajaFuerte, "Cajero-2");
        Cajero cajero3 = new Cajero(cajaFuerte, "Cajero-3");
        
        System.out.println("Cajeros creados: " + cajero1.getName() + ", " + 
                         cajero2.getName() + ", " + cajero3.getName() + "\n");
        
        // Iniciar los hilos de los cajeros
        cajero1.start();
        cajero2.start();
        cajero3.start();
        
        // Hilo para reponer dinero automáticamente
        Thread reponedor = new Thread(() -> {
            while (true) {
                try {
                    // Esperar entre 2 y 5 segundos antes de reponer
                    Thread.sleep((int) (Math.random() * 3000) + 2000);
                    
                    // Reponer entre 500 y 2000 €
                    int cantidadReponer = (int) (Math.random() * 1500) + 500;
                    cajaFuerte.depositar(cantidadReponer, "Reponedor");
                    
                } catch (InterruptedException e) {
                    System.out.println("[Reponedor] interrumpido");
                    break;
                }
            }
        });
        reponedor.start();
        
        // Simular la ejecución por un tiempo determinado (por ejemplo, 30 segundos)
        try {
            Thread.sleep(30000); // Ejecutar por 30 segundos
            
            // Interrumpir todos los hilos
            System.out.println("\n=== FINALIZANDO SIMULACIÓN ===\n");
            cajero1.interrupt();
            cajero2.interrupt();
            cajero3.interrupt();
            reponedor.interrupt();
            
            // Esperar a que los hilos terminen
            cajero1.join();
            cajero2.join();
            cajero3.join();
            reponedor.join();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== SIMULACIÓN FINALIZADA ===");
        System.out.println("Saldo final en caja fuerte: " + cajaFuerte.getDineroDisponible() + " €");
    }
}
