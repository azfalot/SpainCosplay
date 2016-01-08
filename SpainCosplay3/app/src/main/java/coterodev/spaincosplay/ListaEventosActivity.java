package coterodev.spaincosplay;

import android.app.ListActivity;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListaEventosActivity extends ListActivity {
    String direccion = "http://coterodev.esy.es/eventos/eventoNombre.php?nombreEvento=";
    ListaEventosNegocio ListaNegocio = new ListaEventosNegocio();
    EventoNegocio negociador = new EventoNegocio();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        ListaNegocio.execute("http://coterodev.esy.es/eventos/eventosFecha.php");
        try {
            ListaNegocio.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.objeto_lista, ListaNegocio.getNombres()));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Evento Salon = null;
        String eventoSeleccionado = l.getItemAtPosition(position).toString();
        String url= eventoSeleccionado.replace(" ","%20");
        negociador.execute(direccion+url);
        try {
            Salon = negociador.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (Salon != null){
            //Lanzar nuevo Activity
            Intent i = new Intent(this, InformacionEventoActivity.class);
            i.putExtra("EventoP", (Serializable) Salon);
            startActivity(i);
        }

    }
}
