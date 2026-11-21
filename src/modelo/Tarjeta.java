package modelo;

import excepciones.SaldoInsuficienteException;

public class Tarjeta {
    // Atributos
    private String numeroTarjeta;
    private String fechaExpiracion;
    private String cvv;
    private String nip;
    private boolean activa;

    // Constructores
    public Tarjeta(String numeroTarjeta, String fechaExpiracion, String cvv, String nip, boolean activa){
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.nip = nip;
        this.fechaExpiracion = fechaExpiracion;
        this.activa = activa;
    }

    // Getters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion.toString();
    }

    public String getNip() {
        return nip;
    }

    public boolean isActiva(){
        return activa;
    }

    // Metodos
    public void pagar(double monto, CuentaBase cuenta, String nipIngresado)
            throws SaldoInsuficienteException {
        if (!this.activa) {
            throw new IllegalArgumentException("No se puede pagar. La tarjeta se encuentra BLOQUEADA.");
        }
        if (cuenta == null) {
            throw new IllegalArgumentException("La tarjeta no tiene una cuenta vinculada.");
        }
        if (!this.nip.equals(nipIngresado)) {
            throw new IllegalArgumentException("NIP incorrecto. Operación rechazada.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a pagar debe ser mayor a 0.");
        }
        // Descuenta el dinero real
        cuenta.retirar(monto);
        System.out.println("Pago de $" + monto + " procesado exitosamente con la tarjeta **** "
                + numeroTarjeta.substring(numeroTarjeta.length() - 4));
    }

    public void bloquear() {
        if (!this.activa) {
            System.out.println("Aviso: La tarjeta ya se encuentra bloqueada.");
        } else {
            this.activa = false;
            System.out.println("La tarjeta ha sido BLOQUEADA correctamente.");
        }
    }


    public void desbloquear() {
        if (this.activa) {
            System.out.println("Aviso: La tarjeta ya se encuentra activa.");
        } else {
            this.activa = true;
            System.out.println("La tarjeta ha sido DESBLOQUEADA correctamente.");
        }
    }


    public void cambiarNIP(String nipActual, String nuevoNip) {
        // 1. Validar que el NIP actual coincida
        if (!this.nip.equals(nipActual)) {
            throw new IllegalArgumentException("El NIP actual es incorrecto.");
        }
        // 2. Validar el formato del nuevo NIP
        if (nuevoNip == null || nuevoNip.length() != 4) {
            throw new IllegalArgumentException("El nuevo NIP debe tener exactamente 4 dígitos.");
        }
        // 3. Aplicar el cambio
        this.nip = nuevoNip;
        System.out.println("NIP actualizado con éxito.");
    }

}
