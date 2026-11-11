package modelo;
import excepciones.SaldoInsuficienteException;

public class CuentaBase{
    private int numCuenta;
    private String titular;
    private double saldo;

    public CuentaBase(int numCuenta, String titular, double saldo){

        if(titular == null || titular.trim().isEmpty()){
            throw new IllegalArgumentException("El titular es obligatorio");
        }
        if(saldo < 0){
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.numCuenta = numCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }
    public void depositar(double dinero){
        if (dinero < 0){
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        this.saldo += dinero;
    }

    public void retirar(double dinero) throws SaldoInsuficienteException{
        if(dinero <= 0){
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        if(dinero > this.saldo){
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo actual: $" + this.saldo);
        }
        this.saldo -= dinero;
    }

    public void mostrarInformacion(){
        System.out.println("Cuenta: $" + numCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: $" + saldo);
    }

    public void aplicarBeneficio(){
        System.out.println("La cuenta no tiene beneficios");
    }

    public int getNumCuenta(){
        return numCuenta;
    }

    public String getTitular(){
        return titular;
    }

    public double getSaldo(){
        return saldo;
    }

    public void setSaldo(double saldo){
        if(saldo >= 0){
            this.saldo = saldo;
        }
    }

}