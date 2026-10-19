# Sistema Bancario: Opus Proventus

Proyecto Integrador - Programacion Orientada a Objetos  
Facultad de Ingenieria - UNAM  
Grupo: 04  

### Integrantes:
* Cortes Gonzalez Alyson Sarahi
* Gonzalez Casamayor Yoltzin Ociel
* Hernandez Valeriano Issis Dalieth
* Sanchez Vargas Monserrat

---

## Descripcion del proyecto
Opus Proventus es una aplicacion bancaria orientada a objetos en Java que permite a los usuarios gestionar su cuenta financiera, consultar saldos y realizar transferencias con comprobante y administrar compras.

## Estructura de paquetes
El codigo esta organizado en tres paquetes dentro de `src/`:

* `modelo`: Contiene las clases de datos y entidades del sistema (`Cuenta.java`, `Usuario.java`,`Tarjeta.java`, `Transaccion.java`).
* `app`: Contiene la clase ejecutable (`Main.java`).
* `servicio`: Contiene la lógica y operaciones bancarias (`ServicioBancario.java`).

## Como compilar y ejecutar

### Compilacion:
Desde la terminal en la carpeta principal del proyecto ejecutar:
```bash
javac -d bin src/modelo/*.java src/servicio/*.java src/app/*.java
```

### Ejecucion:
Para correr el programa principal:
```bash
java -cp bin app.Main
```

## Funcionalidades demostradas
1. **Transferencia bancaria entre cuentas:** Se hizo un retiro de cuenta origen, deposito en cuenta destino y una creación automatica de comprobante con folio.
2. **Operaciones con tarjeta de debito:** Se hicieron pruebas de cobro con tarjeta activa y validacion de seguridad cuando la tarjeta estaba bloqueada.
