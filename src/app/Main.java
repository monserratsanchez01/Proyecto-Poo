package app;

import modelo.Cuenta;
import modelo.Tarjeta;
import modelo.Usuario;
import servicio.ServicioBancario;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA BANCARIO OPUS PROVENTUS ===");

        // Creacion de cuentas de prueba
        Cuenta cuentaAlyson = new Cuenta(1001, "Alyson Cortes", 5000.0);
        Cuenta cuentaYoltzin = new Cuenta(1002, "Yoltzin Gonzalez", 1500.0);

        // Creacion de tarjeta vinculada a la cuenta de Alyson
        Tarjeta tarjetaAlyson = new Tarjeta("4152313389012345", "12/28", "789", "1234", 3000.0, cuentaAlyson);

        // Creacion del usuario
        Usuario usuarioAlyson = new Usuario("user01", "Alyson Cortes", "alyson@gmail.com", "12345", cuentaAlyson, tarjetaAlyson);

        // Servicio del banco
        ServicioBancario banco = new ServicioBancario();

        // 1. Probar inicio de sesion
        System.out.println("\n--- Prueba de inicio de sesion ---");
        usuarioAlyson.iniciarSesion("12345");
        usuarioAlyson.mostrarPerfil();

        // 2. Funcionalidad 1: Transferencia entre cuentas
        System.out.println("\n--- Funcionalidad 1: Transferencia entre cuentas ---");
        System.out.println("Saldos iniciales:");
        cuentaAlyson.consultarSaldo();
        cuentaYoltzin.consultarSaldo();

        // Hacemos una transferencia de 1200
        banco.transferir(cuentaAlyson, cuentaYoltzin, 1200.0);

        System.out.println("\nSaldos finales tras la transferencia:");
        cuentaAlyson.consultarSaldo();
        cuentaYoltzin.consultarSaldo();

        // 3. Funcionalidad 2: Pago con tarjeta
        System.out.println("\n--- Funcionalidad 2: Pago con tarjeta ---");
        // Pago exitoso con NIP correcto
        System.out.println("\nIntento 1: Pago normal");
        banco.realizarPagoConTarjeta(tarjetaAlyson, 450.0, "1234");

        // Pago con NIP incorrecto
        System.out.println("\nIntento 2: Pago con NIP malo");
        banco.realizarPagoConTarjeta(tarjetaAlyson, 200.0, "9999");

        // Bloqueo de tarjeta y prueba
        System.out.println("\nIntento 3: Bloquear tarjeta y probar");
        tarjetaAlyson.bloquear();
        banco.realizarPagoConTarjeta(tarjetaAlyson, 100.0, "1234");
        tarjetaAlyson.desbloquear();

        // 4. Ver historial final
        banco.mostrarHistorial();
        
        System.out.println("Saldo final de la cuenta de Alyson:");
        cuentaAlyson.consultarSaldo();
    }
}
