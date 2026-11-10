package excepciones;

// Hereda de Exception
public class SaldoInsuficienteException extends Exception {
    
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}