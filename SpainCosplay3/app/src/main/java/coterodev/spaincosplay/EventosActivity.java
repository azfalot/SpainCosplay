package coterodev.spaincosplay;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by carlos on 22/12/2015.
 */
public class EventosActivity extends ListActivity{

    EventosNegocio eventosNegocio = new EventosNegocio();

    //String listado[] = {"Barcelona", "Bilbao", "Madrid", "Sevilla", "Valencia"};
    //String url = "http://coterodev.esy.es/eventos/eventos.php";
    @Override public void onCreate(Bundle savedInstanceState){
        eventosNegocio.execute("http://coterodev.esy.es/eventos/eventos.php");
        try {
            ArrayList arrayList = eventosNegocio.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos_layout);
        setListAdapter(new EventoAdaptador(this, eventosNegocio.cargarEventos()));
    }

}