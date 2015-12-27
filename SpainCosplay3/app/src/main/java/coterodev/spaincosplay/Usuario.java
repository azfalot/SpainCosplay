package coterodev.spaincosplay;

/**
 * Created by Azfalot on 17/12/2015.
 */
public class Usuario {
    private String Email;
    private String Usuario;
    private String Contraseña;

    public Usuario(String email, String usuario, String contraseña) {
        Email = email;
        Usuario = usuario;
        Contraseña = contraseña;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
