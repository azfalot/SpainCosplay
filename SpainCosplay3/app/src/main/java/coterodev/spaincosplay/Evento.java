package coterodev.spaincosplay;

/**
 * Created by carlos on 23/12/2015.
 */
public class Evento {

    private String nombre;
    private String lugar;
    private String fecha;
    private String fecha_fin;
    private String cartel;

    public Evento(String nombre, String lugar, String fecha, String fecha_fin, String cartel) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.fecha = fecha;
        this.fecha_fin = fecha_fin;
        this.cartel = cartel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getCartel() {
        return cartel;
    }

    public void setCartel(String cartel) {
        this.cartel = cartel;
    }
}
