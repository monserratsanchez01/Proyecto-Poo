package app;

import modelo.Cuenta;
import modelo.Usuario;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA BANCARIO OPUS PROVENTUS ===");

        // Creacion de cuentas de prueba
        Cuenta cuentaAlyson = new Cuenta(1001, "Alyson Cortes", 5000.0);

        // Creacion del usuario vinculado a la cuenta
        Usuario usuarioAlyson = new Usuario("user01", "Alyson Cortes", "alyson@gmail.com", "12345", cuentaAlyson);

        // 1. Prueba de inicio de sesion
        System.out.println("\n--- Prueba de inicio de sesion ---");
        usuarioAlyson.iniciarSesion("12345");
        usuarioAlyson.mostrarPerfil();

        // 2. Operaciones basicas con la cuenta
        System.out.println("\n--- Operaciones de cuenta ---");
        cuentaAlyson.consultarSaldo();

        System.out.println("\nRealizando deposito...");
        cuentaAlyson.depositar(1500.0);

        System.out.println("\nRealizando retiro...");
        cuentaAlyson.retirar(700.0);

        System.out.println("\nSaldo final:");
        cuentaAlyson.consultarSaldo();
    }
}
