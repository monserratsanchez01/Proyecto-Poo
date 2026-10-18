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
Opus Proventus es una aplicacion bancaria orientada a objetos en Java que permite a los usuarios gestionar su cuenta, consultar saldos y realizar operaciones de manera segura.

## Estructura de paquetes
El codigo esta organizado en paquetes dentro de `src/`:

* `modelo`: Contiene las clases de la entidad (`Cuenta.java`, `Usuario.java`,`Tarjeta.java`).
* `app`: Contiene la clase ejecutable (`Main.java`).

## Como compilar y ejecutar

### Compilacion:
Desde la terminal en la carpeta principal del proyecto ejecutar:
```bash
javac -d bin src/modelo/*.java src/app/*.java
```

### Ejecucion:
Para correr el programa principal:
```bash
java -cp bin app.Main
```
