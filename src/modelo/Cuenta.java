package modelo;

import excepciones.SaldoInsuficienteException;

public class Cuenta {
    private int numCuenta;
    private String titular;
    private double saldo;

    // Constructor
    public Cuenta(int numCuenta, String titular, double saldoInicial) {
        this.numCuenta = numCuenta;
        this.titular = titular;
        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        } else {
            this.saldo = 0;
            System.out.println("El saldo inicial no puede ser negativo, se asigno 0");
        }
    }

    // Metodo para depositar dinero
    public void  depositar(double dinero) {
        if (dinero <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que cero.");
        }
        this.saldo += dinero;
    }

    // Metodo para retirar dinero
    public void retirar(double dinero) throws SaldoInsuficienteException {
        // Validar
        if (dinero <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor que cero.");
        }
        
        // Validar situación del dominio
        if (dinero > this.saldo) {
            // Se rechaza la operación y el saldo se conserva intacto
            throw new SaldoInsuficienteException(
                "Saldo insuficiente para retirar. Saldo actual: $" + this.saldo
            );
        }
        
        // Se descuenta el dinero
        this.saldo -= dinero;
    }

    // Metodo para consultar saldo
    public void consultarSaldo() {
        System.out.println("Cuenta: " + numCuenta + " | Titular: " + titular + " | Saldo: $" + saldo);
    }

    public void mostrarInformacion() {
        System.out.println("Titular: " + titular + ", NumCuenta: " + numCuenta + ", Saldo: $" + saldo);
    }

    // Getters y Setters
    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }
}
