package coterodev.spaincosplay;

import android.app.ListActivity;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ListaEventosActivity extends ListActivity {
    String direccion = "http://coterodev.esy.es/eventos/eventoNombre.php?nombreEvento=";
    ListaEventosNegocio ListaNegocio = new ListaEventosNegocio();
    EventoNegocio negociador ;
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
        try {
            setListAdapter(new ArrayAdapter<String>(this, R.layout.objeto_lista, ListaNegocio.getNombres()));
        }catch (Exception e){
            Toast.makeText(this.getApplicationContext(), "No se ha podido cargar la información, compruebe su conexión de red",Toast.LENGTH_LONG).show();
            finish();
        }

        //Rellenar Spinner
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("Proximos");
        opciones.add("Finalizados");
        opciones.add("Nombre");

        //Adaptarlo
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_custom, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner= (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        final EditText textoBusqueda = (EditText) findViewById(R.id.editText);
        ImageView lupa = (ImageView) findViewById(R.id.lupa);

        lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textoBusqueda.requestFocus();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {

                        switch (position){
                            case 0:

                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        negociador = new EventoNegocio();
        Evento Salon = null;
        String eventoSeleccionado = l.getItemAtPosition(position).toString();
        String url = Uri.encode(eventoSeleccionado);
        //String url= eventoSeleccionado.replace(" ","%20");
        try {
            negociador.execute(direccion+url);
            Salon = negociador.get();
        } catch (InterruptedException e) {
            Toast.makeText(this.getApplicationContext(),"Interrumpido",Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            Toast.makeText(this.getApplicationContext(),"Error con tarea asyncrona",Toast.LENGTH_SHORT).show();
        }catch (NullPointerException e){
            Toast.makeText(this.getApplicationContext(), "No se ha podido cargar la información, compruebe su conexión de red",Toast.LENGTH_SHORT).show();
        }
        if (Salon != null){
            //Lanzar nuevo Activity
            Intent i = new Intent(this, InformacionEventoActivity.class);
            i.putExtra("EventoP", (Serializable) Salon);
            startActivity(i);
        }

    }


}
