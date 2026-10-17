package servicio;

import modelo.Cuenta;
import modelo.Tarjeta;
import modelo.Transaccion;
import java.util.ArrayList;

public class ServicioBancario {
    private ArrayList<Transaccion> listaTransacciones;
    private int contador;

    public ServicioBancario() {
        this.listaTransacciones = new ArrayList<>();
        this.contador = 1;
    }

    // Metodo para hacer transferencias entre dos cuentas
    public boolean transferir(Cuenta origen, Cuenta destino, double monto) {
        System.out.println("\nIniciando transferencia...");
        if (origen.getNumCuenta() == destino.getNumCuenta()) {
            System.out.println("No puedes transferir a la misma cuenta");
            return false;
        }
        if (monto <= 0) {
            System.out.println("El monto a transferir debe ser mayor a 0");
            return false;
        }

        // Intenta retirar de la cuenta origen
        boolean retiroOk = origen.retirar(monto);
        if (!retiroOk) {
            System.out.println("No se pudo completar la transferencia: fondos insuficientes");
            return false;
        }

        // Deposita en la cuenta destino
        destino.depositar(monto);

        // Registra el movimiento
        String folio = "TRX-00" + contador;
        contador++;
        Transaccion t = new Transaccion(folio, "Transferencia", monto, origen.getNumCuenta(), destino.getNumCuenta());
        listaTransacciones.add(t);

        System.out.println("Transferencia realizada con exito");
        t.generarComprobante();
        return true;
    }

    // Metodo para pagar con tarjeta
    public boolean realizarPagoConTarjeta(Tarjeta tarjeta, double monto, String nip) {
        System.out.println("\nProcesando pago con tarjeta...");
        boolean resultado = tarjeta.pagar(monto, nip);
        if (resultado) {
            String folio = "PAGO-00" + contador;
            contador++;
            int cuenta = tarjeta.getCuentaVinculada() != null ? tarjeta.getCuentaVinculada().getNumCuenta() : 0;
            Transaccion t = new Transaccion(folio, "Pago con Tarjeta", monto, cuenta, 0);
            listaTransacciones.add(t);
            t.generarComprobante();
            return true;
        }
        return false;
    }

    // Metodo para ver todas las transacciones realizadas
    public void mostrarHistorial() {
        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
        if (listaTransacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas todavia");
        } else {
            for (Transaccion t : listaTransacciones) {
                System.out.println(t.getIdTransaccion() + " | " + t.getTipo() + " | $" + t.getMonto() + " | Fecha: " + t.getFecha());
            }
        }
        System.out.println("--------------------------------");
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }
}
