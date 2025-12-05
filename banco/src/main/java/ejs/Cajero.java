package ejs;

public class Cajero extends Thread {
    private CajaFuerte caja;
    private String nombreCajero;
    
    public Cajero(CajaFuerte caja, String nombre) {
        this.caja = caja;
        this.nombreCajero = nombre;
        this.setName(nombre); // Asignar nombre al hilo
    }
    
    @Override
    public void run() {
        System.out.println("[" + nombreCajero + "] iniciando operaciones");
        
        // El cajero funciona indefinidamente
        while (true) {
            try {
                // Generar cantidad aleatoria entre 50 y 500 €
                int cantidadRetiro = generarCantidadAleatoria(50, 500);
                
                // Simular tiempo de procesamiento del cliente (50-200 ms)
                int tiempoAtencion = generarCantidadAleatoria(50, 200);
                Thread.sleep(tiempoAtencion);
                
                // Intentar retirar de la caja fuerte
                boolean exito = caja.retirar(cantidadRetiro, nombreCajero);
                
                if (exito) {
                    // Simular tiempo entre operaciones exitosas
                    Thread.sleep(100);
                } else {
                    // Si hubo interrupción, terminar el hilo
                    break;
                }
                
            } catch (InterruptedException e) {
                System.out.println("[" + nombreCajero + "] interrumpido");
                break;
            }
        }
        
        System.out.println("[" + nombreCajero + "] finalizando operaciones");
    }
    
    private int generarCantidadAleatoria(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
