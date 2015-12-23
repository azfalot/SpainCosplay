package coterodev.spaincosplay;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    InputStream is = null;
    String urlEventos = "http://coterodev.esy.es/eventos/eventos.php";
    String line = null;
    String resultado = null;

    public String[] nombres, lugares, fecha ,fechab ,cartel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //drawer_layout -> activity_main
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Una vez Cargada la informacion
        //Hacer peticion de datos al Servidor
        //Obtener datos en un Array o un JsonObject
        //Establecer un adaptador
        //pasar ese adaptador al listview-> de content_main que contiene un listview con id=list
        cargarEventos();

        //Listview lv =(ListView) findViewById(R.id.)


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Menu acercade
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acerca_de) {
            lanzarAcercaDe(null);
        }

        return super.onOptionsItemSelected(item);
    }

    //Opciones de menu desplegable
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_eventos) {

        } else if (id == R.id.nav_galeria) {

        } else if (id == R.id.nav_noticias) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void lanzarAcercaDe(View view){
        Intent i = new Intent(this, AcercaDe.class);
        startActivity(i);
    }

    public void cargarEventos(){
        llamarServicioWeb();
    }

    public void llamarServicioWeb() {
        try {
            URL url = new URL(urlEventos);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                resultado = sb.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Parsear texto a JSON
        try {

            JSONArray jArray = new JSONArray(resultado);
            JSONObject jObjeto = null;

            //nombres, lugares, fecha ,fechab ,cartel;

            nombres = new String[jArray.length()];
            lugares = new String[jArray.length()];
            fecha = new String[jArray.length()];
            fechab = new String[jArray.length()];
            cartel = new String[jArray.length()];

            for (int i = 0; i < jArray.length(); i++) {

                jObjeto = jArray.getJSONObject(i);
                nombres[i] = jObjeto.getString("nombre");
                lugares[i] = jObjeto.getString("lugar");
                fecha[i] = jObjeto.getString("fecha");
                fechab[i] = jObjeto.getString("fecha_fin");
                cartel[i] = jObjeto.getString("cartel");

            }
        } catch (Exception e) {
            Log.e("A JSON", e.toString());
        }
    }

}
