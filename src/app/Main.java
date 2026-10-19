package app;

import modelo.Cuenta;
import modelo.Usuario;
import modelo.Tarjeta;
import servicio.ServicioBancario;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA BANCARIO OPUS PROVENTUS ===");

        ServicioBancario banco = new ServicioBancario();
        // Creacion de cuentas de prueba
        Cuenta cuentaAlyson = new Cuenta(1001, "Alyson Cortes", 5000.0);
        Cuenta cuentaYoltzin = new Cuenta(1002, "Yoltzin Gonzalez", 1500.0);

        // Creacion del usuario y tarjeta vinculada
        Usuario usuarioAlyson = new Usuario("user01", "Alyson Cortes", "alyson@gmail.com", "12345", cuentaAlyson);
        Tarjeta tarjetaAlyson = new Tarjeta("4152313389012345", "12/28", "789", "1234", true);

        // Prueba de autenticación
        System.out.println("\n--- 1. Auntenticación del cliente ---");
        usuarioAlyson.iniciarSesion("12345");
        usuarioAlyson.mostrarPerfil();

        //Funcionalidad 1: Operación de tranferencia bancaria y el comprobante
        System.out.println("\n--- 2. Funcionaildad 1: Transferencia entre cuentas ---");
        System.out.println("Saldos iniciales:");
        cuentaAlyson.consultarSaldo();
        cuentaYoltzin.consultarSaldo();

        // Tranferencia de $1200 de Alyson a Yoltzin
        banco.transferir(cuentaAlyson, cuentaYoltzin, 1200.0);
        System.out.println("\nSaldos posteriores a la transferencia:");
        cuentaAlyson.consultarSaldo();
        cuentaYoltzin.consultarSaldo();
        
        // Funconalidad 2: Operaciones con tarjeta de debito
        System.out.println("\n--- 3. Funcionaildad 2: Pago con tarjeta y seguridad ---");
        // Caso A: Pago con tarjeta activa
        System.out.println("\n[Caso A: Intento de pago normal]");
        tarjetaAlyson.pagar(450.0);
        // Caso B: Bloqueo preventivo de la taarjeta y rechazo
        System.out.println("\n[Caso B: Bloqueo preventivo de la tarjeta]");
        tarjetaAlyson.bloquear();
        tarjetaAlyson.pagar(200.0); // Se rechaza porque esta bloqueada
        
        // Desbloqueo de tarjeta
        tarjetaAlyson.desbloquear();


        // Historial
        banco.mostrarHistorial();
        System.out.println("\n[FIN DE LA DEMOSTRACIÓN]");
    }
}
