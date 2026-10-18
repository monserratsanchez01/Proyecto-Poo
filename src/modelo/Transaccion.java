package modelo;

public class Transaccion{
    private String tipo;
    private double monto ;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private int idTransaccion;

    public Transaccion ( int idTransaccion, String tipo, double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino){

        this.idTransaccion = idTransaccion;
        this.tipo = tipo;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public String getTipo(){
        return tipo;
    }

    public double getMonto(){
        return monto;
    }

    public Cuenta getCuentaOrigen(){
        return cuentaOrigen;
    }

    public Cuenta getCuentaDestino(){
        return getCuentaDestino;
    }

    public int getIdTransaccion(){
        return idTransaccion;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setMonto ( double monto){
        if ( monto > 0){
            this.monto = monto;
        }
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen ){
        this.cuentaOrigen = cuentaOrigen;
    }

    public void setCuentaDestino (Cuenta cuentaDestino){
        this.cuentaDestino = cuentaDestino;
    }

    public String generarComprobante (){
        String comprobante = "----COMPROBANTE BANCARIO ----\n" + "ID: "+ idTransaccion +"\n"+"Tipo: " + tipo + "\n" + "Monto: $" + monto + "\n";
        if ( cuentaOrigen != null){
            comprobante += "Cuenta origen: " + cuentaOrigen.getNumCuenta() + "\n";
        }    

        if (cuentaDestino != null){
            comprobante += "Cuenta destino: " + cuentaDestino.getNumCuenta() + "\n";
        }

        return comprobante + "-------";


    }



}