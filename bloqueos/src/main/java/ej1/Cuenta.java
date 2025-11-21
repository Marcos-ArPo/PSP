package ej1;

public class Cuenta {
    private final String numeroCuenta;
    private double saldo;

    public Cuenta(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized void retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            System.out.println("Retirados " + monto + " de " + numeroCuenta + " - Saldo: " + saldo);
        } else {
            System.out.println("Fondos insuficientes en " + numeroCuenta + " para retirar " + monto);
        }
    }

    public synchronized void depositar(double monto) {
        saldo += monto;
        System.out.println("Depositados " + monto + " en " + numeroCuenta + " - Saldo: " + saldo);
    }

    @Override
    public String toString() {
        return numeroCuenta + " (Saldo: " + saldo + ")";
    }
}