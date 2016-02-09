package coterodev.spaincosplay;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaEventosActivity extends ListActivity {
    ListaEventosNegocio ListaNegocio;
    EventoNegocio negociador ;
    String Url;
    String direccion = "http://coterodev.esy.es/eventos/eventoNombre.php?nombreEvento=";
    int contador = 0;
    //final Intent refresh = new Intent(this, ListaEventosActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        Url = getIntent().getStringExtra("url");
        ListaNegocio =  new ListaEventosNegocio();
        if (Url == null) {
            ListaNegocio.execute("http://coterodev.esy.es/eventos/eventosFecha.php");
            contador = 1;
        }else{
            ListaNegocio.execute(Url);
            contador = 1;
        }
        //Consulta PHP
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
        } catch (NullPointerException e) {
            //Toast.makeText(this.getApplicationContext(), "Error de NULL en Adaptador",Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this.getApplicationContext(), getString(R.string.error_conexion),Toast.LENGTH_LONG).show();
            //finish();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Spinner spinner= (Spinner) findViewById(R.id.spinner);
        final EditText textoBusqueda = (EditText) findViewById(R.id.editText);

        //Informacion - Ayuda
        ImageView ayuda = (ImageView) findViewById(R.id.lupa);
        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), getString(R.string.icono_informacion),Toast.LENGTH_LONG).show();
            }
        });

        //Boton de Borrado
        final ImageView Equis = (ImageView) findViewById(R.id.equis);
        Equis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textoBusqueda.setText("");
                //Reload por defecto
                ListaNegocio = new ListaEventosNegocio();
                ListaNegocio.execute("http://coterodev.esy.es/eventos/eventosFecha.php");
                try {
                    ListaNegocio.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.objeto_lista, ListaNegocio.getNombres()));
                Equis.setVisibility(View.INVISIBLE);

            }
        });

        //Spinner Busqueda por Opciones
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        if (contador != 1){
                            switch (position) {
                                case 0:
                                    /*refresh.putExtra("url", "http://coterodev.esy.es/eventos/eventosFecha.php");
                                    refresh.putExtra("posicion",position);
                                    startActivity(refresh);
                                    finish();*/
                                    textoBusqueda.setText("");
                                    ListaNegocio = new ListaEventosNegocio();
                                    ListaNegocio.execute("http://coterodev.esy.es/eventos/eventosFecha.php");
                                    try {
                                        ListaNegocio.get();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    }
                                    setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.objeto_lista, ListaNegocio.getNombres()));
                                    break;
                                case 1:
                                    /*refresh.putExtra("url", "http://coterodev.esy.es/eventos/eventosFinalizados.php");
                                    refresh.putExtra("posicion",position);
                                    startActivity(refresh);
                                    finish();*/
                                    textoBusqueda.setText("");
                                    ListaNegocio = new ListaEventosNegocio();
                                    ListaNegocio.execute("http://coterodev.esy.es/eventos/eventosFinalizados.php");
                                    try {
                                        ListaNegocio.get();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    }
                                    setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.objeto_lista, ListaNegocio.getNombres()));
                                    break;
                                case 2:
                                    /*refresh.putExtra("url", "http://coterodev.esy.es/eventos/eventosOrdenNombre.php");
                                    refresh.putExtra("posicion",position);
                                    startActivity(refresh);
                                    finish();*/
                                    textoBusqueda.setText("");
                                    ListaNegocio = new ListaEventosNegocio();
                                    ListaNegocio.execute("http://coterodev.esy.es/eventos/eventosOrdenNombre.php");
                                    try {
                                        ListaNegocio.get();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    }
                                    setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.objeto_lista, ListaNegocio.getNombres()));
                                    break;
                            }
                        }
                        contador++;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
            });

        //Rellenar Spinner
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add(getString(R.string.spinner_objeto0));
        opciones.add(getString(R.string.spinner_objeto1));
        opciones.add(getString(R.string.spinner_objeto2));

        //Adaptarlo
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_custom, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Buscador de eventos por texto
        TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (filterLongEnough()) {
                    Equis.setVisibility(View.VISIBLE);
                    ListaNegocio = new ListaEventosNegocio();
                    String coincidencia = textoBusqueda.getText().toString();
                    ListaNegocio.execute("http://coterodev.esy.es/eventos/eventoBuscador.php?contiene="+Uri.encode(coincidencia)+"");
                    try {
                        ListaNegocio.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    try {
                        setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.objeto_lista, ListaNegocio.getNombres()));
                    } catch (NullPointerException e) {
                        e.toString();
                    } catch (Exception e){
                        e.toString();
                    }
                }
            }

            private boolean filterLongEnough() {
                return textoBusqueda.getText().toString().trim().length() > 0;
            }
        };
        textoBusqueda.addTextChangedListener(fieldValidatorTextWatcher);

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
            Toast.makeText(this.getApplicationContext(), getString(R.string.error_conexion),Toast.LENGTH_SHORT).show();
        }
        if (Salon != null){
            //Lanzar nuevo Activity
            Intent i = new Intent(this, InformacionEventoActivity.class);
            i.putExtra("EventoP", (Serializable) Salon);
            startActivity(i);
        }

    }

}
