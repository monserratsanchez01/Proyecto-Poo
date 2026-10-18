package modelo;
import java.time.YearMonth; 

public class Tarjeta {
    // Atributos
    private string numeroTarjeta;
    private YearMonth fechaExpiracion;
    private string cvv;
    private string nip;
    private boolean activa;

    // Constructores
    public Tarjeta(string numeroTarjeta, YearMonth fechaExpiracion, string cvv, string nip, boolean activa){
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.nip = nip;
        this.fechaExpiracion = fechaExpiracion;
        this.activa = true;
    }

    // Getters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public String getNip() {
        return nip;
    }

    // Metodos
    public boolean pagar(double monto) {
        // 1. Validar que la tarjeta no esté bloqueada
        if (!this.activa) {
            System.out.println("Error: No se puede pagar. La tarjeta se encuentra BLOQUEADA.");
            return false;
        }

        // 2. Validar que el monto sea positivo
        if (monto <= 0) {
            System.out.println("Error: El monto a pagar debe ser mayor a 0.");
            return false;
        }

        // Cobro exitoso
        System.out.println("Pago de $" + monto + " procesado exitosamente con la tarjeta **** " 
                            + numeroTarjeta.substring(numeroTarjeta.length() - 4));
        return true;
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


    public boolean cambiarNIP(String nipActual, String nuevoNip) {
        // 1. Validar que el NIP actual coincida
        if (!this.nip.equals(nipActual)) {
            System.out.println("Error: El NIP actual es incorrecto.");
            return false;
        }

        // 2. Validar el formato del nuevo NIP
        if (nuevoNip == null || nuevoNip.length() != 4) {
            System.out.println("Error: El nuevo NIP debe tener exactamente 4 números.");
            return false;
        }

        // 3. Aplicar el cambio
        this.nip = nuevoNip;
        System.out.println("NIP actualizado con éxito.");
        return true;
    }

}
