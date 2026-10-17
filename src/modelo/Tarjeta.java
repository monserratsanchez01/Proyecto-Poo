package modelo;

public class Tarjeta {
    private String numero;
    private String fechaExpiracion;
    private String cvv;
    private String nip;
    private boolean activa;
    private double limiteCompra;
    private Cuenta cuentaVinculada;

    // Constructor
    public Tarjeta(String numero, String fechaExpiracion, String cvv, String nip, double limiteCompra, Cuenta cuentaVinculada) {
        this.numero = numero;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.nip = nip;
        this.activa = true;
        this.limiteCompra = limiteCompra;
        this.cuentaVinculada = cuentaVinculada;
    }

    // Metodo para realizar compras con la tarjeta
    public boolean pagar(double monto, String nipIngresado) {
        if (!this.activa) {
            System.out.println("La tarjeta esta bloqueada");
            return false;
        }
        if (!this.nip.equals(nipIngresado)) {
            System.out.println("El NIP ingresado es incorrecto");
            return false;
        }
        if (monto > this.limiteCompra) {
            System.out.println("El monto supera el limite de compra de la tarjeta");
            return false;
        }
        if (cuentaVinculada == null) {
            System.out.println("No hay cuenta asociada a la tarjeta");
            return false;
        }

        // Se descuenta el dinero de la cuenta
        boolean cobro = cuentaVinculada.retirar(monto);
        if (cobro) {
            System.out.println("Pago con tarjeta aprobado por: $" + monto);
            return true;
        } else {
            System.out.println("No se pudo realizar el pago por falta de fondos");
            return false;
        }
    }

    public void bloquear() {
        this.activa = false;
        System.out.println("La tarjeta ha sido bloqueada");
    }

    public void desbloquear() {
        this.activa = true;
        System.out.println("La tarjeta ha sido desbloqueada");
    }

    public boolean cambiarNIP(String nipActual, String nuevoNip) {
        if (!this.nip.equals(nipActual)) {
            System.out.println("El NIP actual no coincide");
            return false;
        }
        if (nuevoNip.length() != 4) {
            System.out.println("El nuevo NIP debe tener 4 digitos");
            return false;
        }
        this.nip = nuevoNip;
        System.out.println("NIP cambiado correctamente");
        return true;
    }

    // Getters y Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public double getLimiteCompra() {
        return limiteCompra;
    }

    public void setLimiteCompra(double limiteCompra) {
        this.limiteCompra = limiteCompra;
    }

    public Cuenta getCuentaVinculada() {
        return cuentaVinculada;
    }

    public void setCuentaVinculada(Cuenta cuentaVinculada) {
        this.cuentaVinculada = cuentaVinculada;
    }
}
