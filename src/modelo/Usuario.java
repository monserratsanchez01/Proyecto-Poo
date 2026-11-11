package modelo;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private CuentaBase cuenta;

    // Constructor
    public Usuario(String idUsuario, String nombre, String correo, String contrasena, CuentaBase cuenta) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.cuenta = cuenta;
    }

    // Metodo para validar inicio de sesion
    public boolean iniciarSesion(String passIngresada) {
        if (this.contrasena.equals(passIngresada)) {
            System.out.println("Inicio de sesion correcto. Bienvenido " + this.nombre);
            return true;
        }
        System.out.println("Contrasena incorrecta para el usuario " + this.nombre);
        return false;
    }

    public void mostrarPerfil() {
        System.out.println("--- DATOS DEL USUARIO ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + idUsuario);
        System.out.println("Correo: " + correo);
        if (cuenta != null) {
            System.out.println("Cuenta vinculada: " + cuenta.getNumCuenta());
        }
        System.out.println("-------------------------");
    }

    // Getters y Setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public CuentaBase getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBase cuenta) {
        this.cuenta = cuenta;
    }
}
