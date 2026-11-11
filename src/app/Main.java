package app;

import modelo.*;
import servicio.ServicioBancario;
import excepciones.SaldoInsuficienteException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicioBancario banco = new ServicioBancario();
        
        // Datos de prueba
        Cuenta cuentaAlyson = new Cuenta(1001, "Alyson Cortes", 5000.0);
        Cuenta cuentaYoltzin = new Cuenta(1002, "Yoltzin Gonzalez", 1500.0);
        Usuario usuarioAlyson = new Usuario("user01", "Alyson Cortes", "alyson@gmail.com", "12345", cuentaAlyson);
        Tarjeta tarjetaAlyson = new Tarjeta("4152313389012345", "12/28", "789", "1234", true);

        System.out.println("=== SISTEMA BANCARIO OPUS PROVENTUS ===");
        
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Ver perfil y saldo (Alyson)");
            System.out.println("2. Transferir a Yoltzin");
            System.out.println("3. Simular cobro con tarjeta");
            System.out.println("4. Ver historial de transferencias");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            
            try {
                // Capturamos como String y convertimos a int
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        usuarioAlyson.mostrarPerfil();
                        cuentaAlyson.consultarSaldo();
                        break;
                        
                    case 2:
                        System.out.print("Ingrese el monto a transferir a Yoltzin: ");
                        // Si el usuario escribe letras, lanza NumberFormatException[cite: 3, 4]
                        double montoTransferencia = Double.parseDouble(scanner.nextLine()); 
                        
                        // El método transferir ahora lanza excepciones, así que lo atrapamos abajo
                        banco.transferir(cuentaAlyson, cuentaYoltzin, montoTransferencia); 
                        break;
                        
                    case 3:
                        System.out.print("Ingrese el monto a pagar con tarjeta: ");
                        double montoPago = Double.parseDouble(scanner.nextLine());
                        tarjetaAlyson.pagar(montoPago);
                        break;
                        
                    case 4:
                        banco.mostrarHistorial();
                        break;
                        
                    case 5:
                        salir = true;
                        System.out.println("Gracias por usar OPUS PROVENTUS. ¡Hasta luego!");
                        break;
                        
                    default:
                        System.out.println("Opción no valida. Ingrese un número del 1 al 5.");
                }
                
            } catch (NumberFormatException e) {
                // Atiende entrada con formato incorrecto
                System.out.println("\n[ERROR DE FORMATO]: Por favor, ingrese unicamente numeros validos.");
                
            } catch (IllegalArgumentException e) {
                // Captura reglas de negocio rotas (Ej: monto negativo)
                System.out.println("\n[ERROR DE VALIDACION]: " + e.getMessage());
                
            } catch (SaldoInsuficienteException e) {
                System.out.println("\n[OPERACION RECHAZADA - EXCEPCION PROPIA]: " + e.getMessage());
                System.out.println(">> El saldo de su cuenta se ha conservado intacto.");
                
            } catch (Exception e) {
                // Captura cualquier otro error genérico no previsto
                System.out.println("\n[ERROR INESPERADO]: Ha ocurrido un problema en el sistema.");
                
            } finally {
                System.out.println("--- [Procesamiento de solicitud finalizado] ---");
            }
        }
        
        scanner.close();
    }
}