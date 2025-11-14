package practica;

public class ej2 {
    public static void main(String[] args) {
        System.out.println("PRIORIDADES DE HILOS EN JAVA");
        
        System.out.println("Constantes de prioridad");
        System.out.println("Thread.MIN_PRIORITY: " + Thread.MIN_PRIORITY);
        System.out.println("Thread.NORM_PRIORITY: " + Thread.NORM_PRIORITY);
        System.out.println("Thread.MAX_PRIORITY: " + Thread.MAX_PRIORITY);
        
        Thread hiloPrincipal = Thread.currentThread();
        System.out.println("Información del hilo principal");
        System.out.println("Nombre: " + hiloPrincipal.getName());
        System.out.println("Prioridad actual: " + hiloPrincipal.getPriority());
        System.out.println("ID: " + hiloPrincipal.getId());
        
        System.out.println("DEMOSTRACIÓN CON HILOS");
        
        hilpri hiloMin = new hilpri("Hilo-Min-Prioridad");
        hilpri hiloNorm = new hilpri("Hilo-Norm-Prioridad");
        hilpri hiloMax = new hilpri("Hilo-Max-Prioridad");
        
        hiloMin.setPriority(Thread.MIN_PRIORITY);    
        hiloNorm.setPriority(Thread.NORM_PRIORITY);   
        hiloMax.setPriority(Thread.MAX_PRIORITY);     
        
        System.out.println("Prioridades asignadas");
        System.out.println(hiloMin.getName() + " - Prioridad: " + hiloMin.getPriority());
        System.out.println(hiloNorm.getName() + " - Prioridad: " + hiloNorm.getPriority());
        System.out.println(hiloMax.getName() + " - Prioridad: " + hiloMax.getPriority());
        
        System.out.println("Ejecución de hilos");
        hiloMin.start();
        hiloNorm.start();
        hiloMax.start();
        
        System.out.println("Nota: Las prioridades son sugerencias para el planificador,");
        System.out.println("pero la JVM no garantiza el orden de ejecución.");
    }
}

class hilpri extends Thread {
    public hilpri(String nombre) {
        super(nombre);
    }
    
    public void run() {
        System.out.println(getName() + " ejecutándose con prioridad: " + getPriority());
    }
}