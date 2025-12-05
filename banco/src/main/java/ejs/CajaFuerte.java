package ejs;

public class CajaFuerte {
    private int dineroDisponible;
    
    public CajaFuerte(int dineroInicial) {
        this.dineroDisponible = dineroInicial;
    }
    
    public synchronized boolean retirar(int cantidad, String cajero) {
        // Mostrar intento de retiro
        System.out.println("[" + cajero + "] intenta retirar " + cantidad + 
                         " € | dinero disponible: " + dineroDisponible + " €");
        
        // Esperar mientras no haya suficiente dinero (USAR while, NO if)
        while (dineroDisponible < cantidad) {
            System.out.println("[" + cajero + "] espera: dinero insuficiente");
            try {
                wait(); // Libera el monitor y espera a ser notificado
            } catch (InterruptedException e) {
                System.out.println("[" + cajero + "] interrumpido mientras esperaba");
                return false;
            }
        }
        
        // Realizar el retiro
        dineroDisponible -= cantidad;
        System.out.println("[" + cajero + "] retiro exitoso | dinero restante: " + 
                         dineroDisponible + " €");
        
        return true;
    }
    
    public synchronized void depositar(int cantidad, String cajero) {
        dineroDisponible += cantidad;
        System.out.println("[" + cajero + "] depositó " + cantidad + 
                         " € | dinero total: " + dineroDisponible + " €");
        
        // IMPORTANTE: Notificar a todos los hilos en espera
        notifyAll();
    }
    
    public synchronized int getDineroDisponible() {
        return dineroDisponible;
    }
}
