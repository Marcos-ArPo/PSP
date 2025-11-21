package ej1;

public class GestorTransferencias {
    
    // VERSIÓN SEGURA: Siempre bloquea en orden alfabético (EVITA INTERBLOQUEO)
    public static boolean transferirSegura(Cuenta desde, Cuenta hacia, double monto) {
        // Determinar orden de bloqueo basado en el número de cuenta
        Cuenta primera, segunda;
        
        int comparacion = desde.getNumeroCuenta().compareTo(hacia.getNumeroCuenta());
        if (comparacion < 0) {
            primera = desde;
            segunda = hacia;
        } else if (comparacion > 0) {
            primera = hacia;
            segunda = desde;
        } else {
            // Misma cuenta (no debería pasar)
            return false;
        }
        
        // Bloquear en orden consistente
        synchronized (primera) {
            synchronized (segunda) {
                System.out.println(Thread.currentThread().getName() + 
                                 " - Bloqueadas: " + primera.getNumeroCuenta() + " -> " + segunda.getNumeroCuenta());
                
                if (desde.getSaldo() >= monto) {
                    desde.retirar(monto);
                    hacia.depositar(monto);
                    System.out.println("✓ Transferencia COMPLETADA: " + monto + " de " + 
                                     desde.getNumeroCuenta() + " a " + hacia.getNumeroCuenta());
                    return true;
                } else {
                    System.out.println("✗ Transferencia FALLIDA: Fondos insuficientes en " + desde.getNumeroCuenta());
                    return false;
                }
            }
        }
    }
    
    // VERSIÓN CON INTERBLOQUEO: Bloquea en el orden dado (PELIGROSO)
    public static boolean transferirConInterbloqueo(Cuenta desde, Cuenta hacia, double monto) {
        // BLOQUEO PELIGROSO: sin orden consistente
        synchronized (desde) {
            System.out.println(Thread.currentThread().getName() + " - Bloqueada: " + desde.getNumeroCuenta());
            
            // Pequeña pausa para aumentar probabilidad de interbloqueo
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
            
            synchronized (hacia) {
                System.out.println(Thread.currentThread().getName() + 
                                 " - Bloqueadas: " + desde.getNumeroCuenta() + " -> " + hacia.getNumeroCuenta());
                
                if (desde.getSaldo() >= monto) {
                    desde.retirar(monto);
                    hacia.depositar(monto);
                    System.out.println("✓ Transferencia COMPLETADA: " + monto + " de " + 
                                     desde.getNumeroCuenta() + " a " + hacia.getNumeroCuenta());
                    return true;
                } else {
                    System.out.println("✗ Transferencia FALLIDA: Fondos insuficientes en " + desde.getNumeroCuenta());
                    return false;
                }
            }
        }
    }
}