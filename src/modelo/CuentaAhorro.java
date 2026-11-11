package modelo;
public class CuentaAhorro extends CuentaBase{
    private double tasaInteres;

    public CuentaAhorro(int numCuenta, String titular, double saldo, double tasaInteres){
        super(numCuenta, titular, saldo);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void aplicarBeneficio(){
        double interes = getSaldo() * tasaInteres;

        setSaldo(getSaldo() + interes);

         System.out.println("Beneficio de cuenta de ahorro aplicado");
        System.out.println("Interes generado: $" + interes);
        System.out.println("Nuevo saldo: $" + getSaldo());
    }
}