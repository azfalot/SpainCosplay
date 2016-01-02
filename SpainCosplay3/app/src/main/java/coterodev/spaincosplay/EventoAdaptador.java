package coterodev.spaincosplay;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by carlos on 23/12/2015.
 */
public class EventoAdaptador extends BaseAdapter {

    private final Activity actividad;
    private ArrayList<Evento> listaEventos;
    ObtenerImagenEvento obtenerImagenEvento;

    public EventoAdaptador(Activity actividad, ArrayList<Evento> lista) {
        super();
        this.actividad = actividad;
        this.listaEventos = lista;
    }

    @Override
    public int getCount() {
        return listaEventos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        obtenerImagenEvento = new ObtenerImagenEvento();
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.elemento_lista, null, true);
        TextView textoNombre = (TextView) view.findViewById(R.id.nombreEvento);
        textoNombre.setText(listaEventos.get(position).getNombre());
        TextView textoLugar = (TextView) view.findViewById(R.id.lugarEvento);
        textoLugar.setText(listaEventos.get(position).getLugar());
        TextView textoFecha = (TextView) view.findViewById(R.id.fechaEvento);
        textoFecha.setText(listaEventos.get(position).getFecha() + " - " + listaEventos.get(position).getFecha_fin());
        //Construir la imagen a traves de una url
        ImageView imagenCartel =(ImageView)view.findViewById(R.id.cartel);
        obtenerImagenEvento.execute(listaEventos.get(position).getCartel());
        Bitmap imagen = null;
        try {
            imagen = obtenerImagenEvento.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        imagenCartel.setImageBitmap(imagen);
        //imagenCartel.setImageURI(Uri.parse(listaEventos.get(position).getCartel()));
        return view;
    }
}