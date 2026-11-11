package servicio;

import java.util.ArrayList;
import java.util.List;
import modelo.CuentaBase;
import modelo.Transaccion;
import excepciones.SaldoInsuficienteException;

public class ServicioBancario {

    private List<Transaccion> historial;
    private int siguienteId;

    public ServicioBancario() {
        historial = new ArrayList<>();
        siguienteId = 1;
    }

    public void transferir(CuentaBase origen, CuentaBase destino, double monto)
            throws IllegalArgumentException, SaldoInsuficienteException {
        
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Las cuentas deben existir.");
        }

        if (origen.equals(destino)) {
            throw new IllegalArgumentException("No puedes transferir a la misma cuenta.");
        }

        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a transferir debe ser mayor que cero.");
        }

        // Validación de saldo lanzando la excepción
        if (origen.getSaldo() < monto) {
            throw new SaldoInsuficienteException(
                "Transferencia fallida. Saldo insuficiente en la cuenta de origen. Saldo actual: $" + origen.getSaldo()
            );
        }

        // Modificación de datos solo si las validaciones pasaron

        origen.retirar(monto); 
        destino.depositar(monto);

        // Registro de la transacción
        Transaccion transaccion = new Transaccion(siguienteId, "Transferencia", monto, origen, destino);
        historial.add(transaccion);
        siguienteId++;
        
        System.out.println("¡Transferencia realizada exitosamente!");
        System.out.println(transaccion.generarComprobante());
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay transferencias registradas.");
            return;
        }

        System.out.println("===== HISTORIAL DE TRANSFERENCIAS =====");

        for (Transaccion transaccion : historial) {
            System.out.println(transaccion.generarComprobante());
        }
    }

    public List<Transaccion> getHistorial() {
        return new ArrayList<>(historial);
    }
}


