package coterodev.spaincosplay;

import java.util.Date;

/**
 * Created by Carlos on 03/12/2015.
 */
public class Evento {

    private String Nombre;
    private Date Fecha;
    private String Lugar;

    public Evento(String lugar, String nombre, Date fecha) {
        Lugar = lugar;
        Nombre = nombre;
        Fecha = fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }
}
