package ejs;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario(30);

        // Crear empleados
        for (int i = 1; i <= 5; i++) {
            new Empleado("Empleado" + i, inventario).start();
        }

        // Crear reponedor
        new Reponedor(inventario).start();
    }
}
