package practica;

public class ej1 {
    public static void main(String[] args) {

        Thread hilo1 = new Thread(new tarsim(), "Hilo-1");
        Thread hilo2 = new Thread(new tarsim(), "Hilo-2");
        
        System.out.println("Información del hilo");
        mostrarInformacionHilo(hilo1);
        System.out.println("-------------------------------");
        mostrarInformacionHilo(hilo2);
        
        hilo1.start();
        hilo2.start();
    }
    
    public static void mostrarInformacionHilo(Thread hilo) {
        System.out.println("Nombre: " + hilo.getName());
        System.out.println("ID: " + hilo.getId());
        System.out.println("Estado: " + hilo.getState());
        System.out.println("Prioridad: " + hilo.getPriority());
        System.out.println("Es daemon: " + hilo.isDaemon());
        System.out.println("Está vivo: " + hilo.isAlive());
    }
}

class tarsim implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " está ejecutándose");
    }
}
