package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
    private String idTransaccion;
    private String tipo;
    private double monto;
    private int cuentaOrigen;
    private int cuentaDestino;
    private String fecha;

    public Transaccion(String idTransaccion, String tipo, double monto, int cuentaOrigen, int cuentaDestino) {
        this.idTransaccion = idTransaccion;
        this.tipo = tipo;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.fecha = LocalDateTime.now().format(formato);
    }

    public void generarComprobante() {
        System.out.println("--- COMPROBANTE DE PAGO ---");
        System.out.println("Folio: " + idTransaccion);
        System.out.println("Fecha: " + fecha);
        System.out.println("Tipo: " + tipo);
        System.out.println("Monto: $" + monto);
        if (cuentaOrigen > 0) {
            System.out.println("Cuenta Origen: " + cuentaOrigen);
        }
        if (cuentaDestino > 0) {
            System.out.println("Cuenta Destino: " + cuentaDestino);
        }
        System.out.println("---------------------------");
    }

    // Getters
    public String getIdTransaccion() {
        return idTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public String getFecha() {
        return fecha;
    }
}
