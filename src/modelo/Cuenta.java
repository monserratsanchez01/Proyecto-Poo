package modelo;

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
    public boolean depositar(double dinero) {
        if (dinero <= 0) {
            System.out.println("El monto a depositar debe ser mayor a 0");
            return false;
        }
        this.saldo += dinero;
        System.out.println("Deposito exitoso de: $" + dinero + ". Saldo actual: $" + this.saldo);
        return true;
    }

    // Metodo para retirar dinero
    public boolean retirar(double dinero) {
        if (dinero <= 0) {
            System.out.println("El monto a retirar debe ser mayor a 0");
            return false;
        }
        if (dinero > this.saldo) {
            System.out.println("Saldo insuficiente en la cuenta");
            return false;
        }
        this.saldo -= dinero;
        System.out.println("Retiro exitoso de: $" + dinero + ". Saldo restante: $" + this.saldo);
        return true;
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
