package modelo;
public class Cuenta{
	private String titular;
	private int numCuenta;
	private double saldo;

	public Cuenta(String titular, int numCuenta, double saldo){
		this.titular = titular;
		this.numCuenta = numCuenta;
		this.saldo = saldo;
	}	

	public void mostrarInformacion(){
		System.out.println("Titular: " + this.titular + "Cuenta: " + this.numCuenta + "Saldo: " + this.saldo);
	}

	public void depositar(double dinero){
		if(dinero<0){
			System.out.println("No se puede depositar cantidades menores a 0");
			return;
		}
		this.saldo += dinero;
	}

	public void retirar(double dinero){
		if(dinero>this.saldo){
			System.out.println("Saldo insuficiente");
			return;
		}
		this.saldo -= dinero;
	}
	
	public void consultarSaldo(){
		System.out.println("El saldo disponible es: " + saldo);
	}
}