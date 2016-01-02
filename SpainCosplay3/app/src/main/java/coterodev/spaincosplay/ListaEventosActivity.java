package coterodev.spaincosplay;

import android.app.ListActivity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListaEventosActivity extends ListActivity {

    ListaEventosNegocio eventosNegocio = new ListaEventosNegocio();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        eventosNegocio.execute("http://coterodev.esy.es/eventos/eventosFecha.php");
        try {
            eventosNegocio.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);
        setListAdapter(new ArrayAdapter<String>(this,R.layout.objeto_lista,eventosNegocio.getNombres()));
    }
}
