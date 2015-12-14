package coterodev.spaincosplay;

/**
 * Created by Azfalot on 14/12/2015.
 */
public class Conexion {

    /*
        usuario = "u412761491_cospa";
        contrasena = "ca123asd";
        ip = "mysql.hostinger.es";
        puerto= "3306";
        catalogo = "Usuarios";

        */
    private String usuario;
    private String contrasena;
    private String ip;
    private String puerto;

    public Conexion(String usuario, String contrasena, String ip, String puerto) {
        this.usuario = "u412761491_cospa";
        this.contrasena = "ca123asd";
        this.ip = "mysql.hostinger.es";
        this.puerto = "3306";
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getIp() {
        return ip;
    }

    public String getPuerto() {
        return puerto;
    }

}
