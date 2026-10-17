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
Opus Proventus es una aplicacion bancaria orientada a objetos en Java que permite a los usuarios gestionar su cuenta, consultar saldos, realizar transferencias y compras con tarjeta de manera segura.

## Estructura de paquetes
El codigo esta organizado en tres paquetes principales dentro de `src/`:

* `modelo`: Contiene las clases de datos (`Cuenta.java`, `Tarjeta.java`, `Usuario.java`, `Transaccion.java`).
* `servicio`: Contiene la logica de las operaciones bancarias (`ServicioBancario.java`).
* `app`: Contiene la clase ejecutable (`Main.java`).

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
