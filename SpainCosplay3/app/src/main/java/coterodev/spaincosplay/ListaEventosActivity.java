package coterodev.spaincosplay;

import android.app.ListActivity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        //super.onListItemClick(l, v, position, id);
        String selection = l.getItemAtPosition(position).toString();
        //segun el texto seleccionado hacer una consulta para ese evento y cargar un layout con la informacion deseada
        Toast.makeText(this, selection, Toast.LENGTH_LONG).show();

    }
}
