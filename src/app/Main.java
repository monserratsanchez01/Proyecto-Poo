package app;

import modelo.*;
import servicio.ServicioBancario;
import excepciones.SaldoInsuficienteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicioBancario banco = new ServicioBancario();
        
        // Datos de prueba
        CuentaBase cuentaAlyson = new CuentaAhorro(1001, "Alyson Cortes", 5000.0, 0.02);
        CuentaBase cuentaYoltzin = new CuentaInversion(1002, "Yoltzin Gonzalez", 1500.0, 0.05);
        List<CuentaBase> cuentasSistema = new ArrayList<>();
        cuentasSistema.add(cuentaAlyson);
        cuentasSistema.add(cuentaYoltzin);
        Usuario usuarioAlyson = new Usuario("user01", "Alyson Cortes", "alyson@gmail.com", "12345", cuentaAlyson);
        Tarjeta tarjetaAlyson = new Tarjeta("4152313389012345", "12/28", "789", "1234", true);

        System.out.println("=== SISTEMA BANCARIO OPUS PROVENTUS ===");

        // Funcionalidad 1: Autenticación inicial antes de operar
        boolean autenticado = false;
        while (!autenticado) {
            System.out.print("Ingrese su contraseña para iniciar sesión (hint: 12345): ");
            String pass = scanner.nextLine();
            if (usuarioAlyson.iniciarSesion(pass)) {
                autenticado = true;
            } else {
                System.out.println("Contraseña incorrecta. Intente de nuevo.\n");
            }
        }
        
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Consultar perfil y saldos");
            System.out.println("2. Transferir dinero (Alyson -> Yoltzin)");
            System.out.println("3. Pagar con tarjeta de débito");
            System.out.println("4. Gestionar tarjeta (Bloquear / Desbloquear / Cambiar NIP)");
            System.out.println("5. Aplicar beneficios mensuales (Demostración Polimorfismo)");
            System.out.println("6. Ver historial de movimientos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                // Capturamos como String y convertimos a int
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        usuarioAlyson.mostrarPerfil();
                        System.out.println("\n--- Estado de Cuentas ---");
                        cuentaAlyson.mostrarInformacion();
                        System.out.println("-------------------------");
                        cuentaYoltzin.mostrarInformacion();
                        break;
                        
                    case 2:
                        // Funcionalidad 2: Transferencias
                        System.out.print("Ingrese el monto a transferir a Yoltzin: ");
                        double montoTransf = Double.parseDouble(scanner.nextLine());
                        banco.transferir(cuentaAlyson, cuentaYoltzin, montoTransf);
                        break;
                        
                    case 3:
                        // Funcionalidad 3: Pago real con tarjeta
                        System.out.print("Ingrese el monto a pagar: ");
                        double montoPago = Double.parseDouble(scanner.nextLine());
                        System.out.print("Ingrese su NIP (4 dígitos): ");
                        String nip = scanner.nextLine();
                        banco.registrarPagoTarjeta(tarjetaAlyson, cuentaAlyson, montoPago, nip);
                        break;
                        
                    case 4:
                        System.out.println("1. Bloquear tarjeta | 2. Desbloquear tarjeta | 3. Cambiar NIP");
                        System.out.print("Elija acción: ");
                        int subOpcion = Integer.parseInt(scanner.nextLine());
                        if (subOpcion == 1) {
                            tarjetaAlyson.bloquear();
                        } else if (subOpcion == 2) {
                            tarjetaAlyson.desbloquear();
                        } else if (subOpcion == 3) {
                            System.out.print("NIP actual: ");
                            String nipActual = scanner.nextLine();
                            System.out.print("Nuevo NIP (4 dígitos): ");
                            String nuevoNip = scanner.nextLine();
                            tarjetaAlyson.cambiarNIP(nipActual, nuevoNip);
                        } else {
                            System.out.println("Subopción no válida.");
                        }
                        break;
                        
                    case 5:
                       // Funcionalidad 4: Polimorfismo con referencias CuentaBase
                        System.out.println("\n=== EJECUTANDO CORTE MENSUAL (POLIMORFISMO) ===");
                        for (CuentaBase c : cuentasSistema) {
                            System.out.println("\nProcesando cuenta de: " + c.getTitular());
                            c.aplicarBeneficio();
                        }
                        break;
                        
                    case 6:
                        banco.mostrarHistorial();
                        break;
                        
                    case 7:
                        salir = true;
                        System.out.println("Sesión cerrada. ¡Hasta luego!");
                        break;    
                    
                    default:
                        System.out.println("Opción no valida. Ingrese un número del 1 al 7.");
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
