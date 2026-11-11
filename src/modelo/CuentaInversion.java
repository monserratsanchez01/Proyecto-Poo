package modelo;
public class CuentaInversion extends CuentaBase{
    private double tasaRendimiento;

    public CuentaInversion(int numCuenta, String titular, double saldo, double tasaRendimiento){
        super(numCuenta, titular, saldo);
        this.tasaRendimiento = tasaRendimiento;
    }

    @Override
    public void aplicarBeneficio(){
        double rendimiento = getSaldo() * tasaRendimiento;
        setSaldo(getSaldo() + rendimiento);

        System.out.println("Beneficio de cuenta de inversion aplicado");
        System.out.println("Rendimiento generado: $" + rendimiento);
        System.out.println("Nuevo saldo: $" + getSaldo());
    }
}