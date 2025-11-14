package practica;

public class ej3 {
    public static void main(String[] args) {
        System.out.println("INICIO DEL PROGRAMA PRINCIPAL");
        
        Thread hilo1 = new Thread(new tcp("Hilo-1"));
        Thread hilo2 = new Thread(new tcp("Hilo-2"));
        
        System.out.println("Iniciando hilos...");
        
        hilo1.start();
        hilo2.start();
        
        try {
            System.out.println("Main esperando a que los hilos terminen...");
            
            hilo1.join();
            hilo2.join();
            
            System.out.println("Todos los hilos han terminado");
            
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt(); 
        }
        
        System.out.println("FIN DEL PROGRAMA PRINCIPAL");
    }
}

class tcp implements Runnable {
    private String nombre;
    
    public tcp(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public void run() {
        System.out.println(nombre + " ha comenzado su ejecución");
        
        for (int i = 1; i <= 5; i++) {
            try {
                int tiempoPausa = (int) (Math.random() * 491) + 10;
                
                System.out.println(nombre + " - Iteración " + i + " - Pausa de " + tiempoPausa + "ms");
                
                Thread.sleep(tiempoPausa);
                
            } catch (InterruptedException e) {
                System.out.println(nombre + " fue interrumpido: " + e.getMessage());
                Thread.currentThread().interrupt(); 
                return; 
            }
        }
        
        System.out.println(nombre + " ha terminado su ejecución");
    }
}