package servicio;

import java.util.ArrayList;
import java.util.List;
import modelo.Cuenta;
import modelo.Transaccion;

public class ServicioBancario {

    private List<Transaccion> historial;
    private int siguienteId;

    public ServicioBancario() {
        historial = new ArrayList<>();
        siguienteId = 1;
    }

    public boolean transferir(Cuenta origen, Cuenta destino, double monto) {

        if (origen == null || destino == null) {
            System.out.println("Error: las cuentas deben existir.");
            return false;
        }

        if (origen == destino) {
            System.out.println("Error: no puedes transferir a la misma cuenta.");
            return false;
        }

        if (monto <= 0) {
            System.out.println("Error: el monto debe ser mayor que cero.");
            return false;
        }

        if (origen.getSaldo() < monto) {
            System.out.println("Error: saldo insuficiente.");
            return false;
        }

        boolean retiroExitoso = origen.retirar(monto);

        if (!retiroExitoso) {
            return false;
        }

        boolean depositoExitoso = destino.depositar(monto);

        if (!depositoExitoso) {
            origen.depositar(monto);
            System.out.println("Error: no se pudo completar la transferencia.");
            return false;
        }

        Transaccion transaccion = new Transaccion(
            siguienteId,
            "Transferencia",
            monto,
            origen,
            destino
        );

        historial.add(transaccion);
        siguienteId++;

        System.out.println("¡Transferencia realizada exitosamente!");
        System.out.println(transaccion.generarComprobante());

        return true;
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


