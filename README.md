# Sistema Bancario: Opus Proventus

**Proyecto Integrador — Cuarto Avance (Entrega Final)**  
**Materia:** Programación Orientada a Objetos (POO)  
**Institución:** Facultad de Ingeniería - UNAM  
**Grupo:** 04  

### Integrantes del Equipo:
* Cortés González Alyson Sarahí
* González Casamayor Yoltzin Ociel
* Hernández Valeriano Issis Dalieth
* Sánchez Vargas Monserrat

---

## 1. Descripción del Proyecto
**Opus Proventus** es un sistema bancario interactivo por consola desarrollado en Java bajo el paradigma de Programación Orientada a Objetos. Permite a los clientes del banco autenticar su identidad, consultar su perfil y estado de cuentas en tiempo real, realizar transferencias interbancarias con generación automática de comprobantes, efectuar pagos en comercios y gestionar la seguridad de su tarjeta de débito (bloqueo, desbloqueo y cambio de NIP), así como ejecutar el corte mensual polimórfico para aplicar beneficios según el tipo de cuenta (`CuentaAhorro` o `CuentaInversion`).

---

## 2. Entorno y Requisitos Técnicos
* **Versión de Java:** Java SE / JDK 17 o superior (compatible desde JDK 11+).
* **Herramientas / IDE empleados:** Visual Studio Code / IntelliJ IDEA, Git y GitHub para control de versiones, y ejecución mediante terminal estándar (`javac` y `java`).
* **Dependencias externas:** Ninguna (utiliza exclusivamente la biblioteca estándar de Java `java.util.*`).

---

## 3. Estructura de Paquetes y Clases
El código fuente se encuentra dentro del directorio `src/` organizado en **4 paquetes** con responsabilidades definidas:

* **`app`**: Contiene el punto de entrada de la aplicación y el flujo interactivo por consola.
  * `Main.java`: Inicializa los objetos de prueba, gestiona el inicio de sesión, despliega el menú interactivo de 7 opciones y centraliza el manejo de errores con bloques `try-catch-finally`.
* **`modelo`**: Contiene las entidades del dominio bancario que encapsulan el estado y validaciones internas.
  * `CuentaBase.java`: Clase padre que modela una cuenta financiera general (`numCuenta`, `titular`, `saldo`) con métodos de depósito, retiro validado y `aplicarBeneficio()`.
  * `CuentaAhorro.java`: Subclase de `CuentaBase` (`extends`) que añade `tasaInteres` y sobrescribe (`@Override`) el método `aplicarBeneficio()` para capitalizar intereses de ahorro.
  * `CuentaInversion.java`: Subclase de `CuentaBase` (`extends`) que añade `tasaRendimiento` y sobrescribe (`@Override`) el método `aplicarBeneficio()` para calcular rendimientos de inversión.
  * `Usuario.java`: Representa al cliente del banco (`idUsuario`, `nombre`, `correo`, `contrasena`, `cuenta`) y valida credenciales en `iniciarSesion()`.
  * `Tarjeta.java`: Modela la tarjeta de débito vinculada (`numeroTarjeta`, `fechaExpiracion`, `cvv`, `nip`, `activa`) con operaciones para `pagar()`, `bloquear()`, `desbloquear()` y `cambiarNIP()`.
  * `Transaccion.java`: Registra los movimientos efectuados (`idTransaccion`, `tipo`, `monto`, `cuentaOrigen`, `cuentaDestino`) y formatea comprobantes con `generarComprobante()`.
* **`servicio`**: Contiene la lógica de negocio y coordinación entre entidades.
  * `ServicioBancario.java`: Administra el historial de movimientos (`List<Transaccion>`), ejecuta transferencias seguras entre cuentas (`transferir()`) y registra pagos con tarjeta (`registrarPagoTarjeta()`).
* **`excepciones`**: Contiene las excepciones personalizadas del dominio.
  * `SaldoInsuficienteException.java`: Excepción propia verificada que hereda de `Exception` y se lanza cuando un retiro, transferencia o pago supera el saldo disponible en la cuenta origen.

---

## 4. Instrucciones para Compilar y Ejecutar

Ubicarse desde la terminal en la carpeta raíz del proyecto (`Proyecto-Poo`) y ejecutar los siguientes comandos:

### Paso 1: Compilación desde cero
```bash
javac -d bin src/excepciones/*.java src/modelo/*.java src/servicio/*.java src/app/*.java
```

### Paso 2: Ejecución del programa
```bash
java -cp bin app.Main
```

---

## 5. Datos de Ejemplo Pre-cargados (Para Pruebas y Demostración)
Al iniciar el programa, el sistema carga en memoria los siguientes datos de prueba para validar todas las funcionalidades de inmediato:

* **Credencial de Inicio de Sesión (Contraseña):** `12345` (Usuario: `Alyson Cortes`, ID: `user01`).
* **Cuenta 1 (Polimorfismo - Subclase `CuentaAhorro`):**
  * Número de cuenta: `#1001` | Titular: `Alyson Cortes` | Saldo inicial: `$5000.0` | Tasa de interés: `2% (0.02)`.
* **Cuenta 2 (Polimorfismo - Subclase `CuentaInversion`):**
  * Número de cuenta: `#1002` | Titular: `Yoltzin Gonzalez` | Saldo inicial: `$1500.0` | Tasa de rendimiento: `5% (0.05)`.
* **Tarjeta de Débito vinculada a Cuenta #1001:**
  * Número: `4152313389012345` (terminación `2345`) | NIP inicial: `1234` | Estado inicial: `Activa`.

---

## 6. Menú y Funcionalidades del Sistema
Una vez autenticado con la contraseña `12345`, el menú principal ofrece 7 opciones conectadas a las **4 funcionalidades principales**:

1. **Consultar perfil y saldos (Funcionalidad 1 - Autenticación y Consulta):** Muestra los datos del cliente autenticado y el estado actual de las cuentas `#1001` y `#1002`.
2. **Transferir dinero (Funcionalidad 2 - Transferencia de fondos):** Transfiere saldo de la cuenta de Alyson (`#1001`) a la cuenta de Yoltzin (`#1002`), validando montos positivos y saldo suficiente, y emitiendo un comprobante foliado.
3. **Pagar con tarjeta de débito (Funcionalidad 3 - Procesamiento de pagos y Gestión de Tarjeta):** Solicita monto y NIP (`1234`), verifica que la tarjeta esté activa, descuenta el saldo real de la cuenta `#1001` y genera el comprobante en el historial.
4. **Gestionar tarjeta (Complemento Funcionalidad 3):** Permite bloquear (`1`), desbloquear (`2`) o cambiar el NIP de 4 dígitos (`3`) validando el NIP actual.
5. **Aplicar beneficios mensuales (Funcionalidad 4 - Polimorfismo):** Recorre la lista polimórfica `List<CuentaBase>` e invoca `c.aplicarBeneficio()`, ejecutando dinámicamente el cálculo de interés (2%) para `CuentaAhorro` y de rendimiento (5%) para `CuentaInversion`.
6. **Ver historial de movimientos:** Imprime todos los comprobantes de transferencias y pagos con tarjeta registrados en la sesión.
7. **Salir:** Cierra la sesión de forma segura y libera el `Scanner`.

---

## 7. Guía Rápida para Reproducir las Pruebas de Cierre (P1 a P8)
Durante la ejecución con `java -cp bin app.Main` (iniciando sesión con `12345`):

* **P1 y P2 (Polimorfismo en ambas subclases):** Seleccionar la opción `5`. Se observará cómo la misma llamada `c.aplicarBeneficio()` sobre referencias `CuentaBase` calcula `$100.0` de interés en `CuentaAhorro` (`#1001`) y `$75.0` de rendimiento en `CuentaInversion` (`#1002`).
* **P3 (Entrada de formato incorrecto - `NumberFormatException`):** Seleccionar opción `2` e ingresar el texto `cien` como monto. El sistema captura el error con mensaje claro sin cerrarse.
* **P4 (Dato que viola una regla - `IllegalArgumentException`):** Seleccionar opción `2` e ingresar `-300` (o `0`). El sistema rechaza el monto negativo y conserva los saldos intactos.
* **P5 (Excepción propia - `SaldoInsuficienteException`):** Seleccionar opción `2` e ingresar `8000` (mayor al saldo disponible). Se activa `SaldoInsuficienteException` y al consultar en opción `1` se comprueba que el saldo no sufrió cambios parciales.
* **P6 (Acción válida después del error):** Inmediatamente después de P5, seleccionar opción `2` e ingresar `1200`. La transferencia se procesa con éxito y genera el comprobante `ID: 1`.
* **P7 (Bloque `finally` en éxito y fallo):** Verificar en consola que tanto en intentos exitosos como en intentos rechazados se imprime siempre al final la línea `--- [Procesamiento de solicitud finalizado] ---`.
* **P8 (Flujo completo integrado):** Iniciar el programa -> Opción `2` (transferir `500`) -> Opción `3` (pagar con tarjeta `500` y NIP `1234`) -> Opción `1` (verificar saldos actualizados: `$4000.0` y `$2000.0`) -> Opción `5` (aplicar beneficios mensuales: nuevos saldos `$4080.0` y `$2100.0`) -> Opción `6` (ver historial con ambos comprobantes).

---

## 8. Limitaciones Conocidas y Alcance
* **Persistencia en memoria:** Los datos y movimientos se conservan en memoria durante la sesión activa; al cerrar el programa con la opción `7`, los saldos regresan a su estado inicial de prueba (no utiliza base de datos ni archivos externos).
* **Usuarios y cuentas de sesión:** El entorno interactivo está acotado a los perfiles pre-cargados en consola para facilitar la verificación reproducible de las reglas del dominio.
* **Elementos opcionales:** No se emplearon hilos (*threads*), interfaz gráfica (GUI) ni patrones de diseño complejos, dado que la arquitectura modular por capas (`app`, `modelo`, `servicio`, `excepciones`) resuelve de forma limpia y directa los requerimientos del sistema.
