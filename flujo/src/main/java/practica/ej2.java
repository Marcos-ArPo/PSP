package practica;

public class ej2 {
    public static void main(String [] args) {
        Thread txt = new Thread(() -> {
            try {
                System.out.println("Abriendo el editor de texto");
                ProcessBuilder pb1 = new ProcessBuilder("gedit");
                Process p1 = pb1.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread calc = new Thread(() -> {
            try {
            System.out.println("Abriendo la calculadora");
            ProcessBuilder pb2 = new ProcessBuilder("gnome-calculator");
            Process p2 = pb2.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        txt.start();
        calc.start();

        try {
            txt.join();
            calc.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Ambos procesos ejecutados.");
    }
}
