package coterodev.spaincosplay;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by carlos on 23/12/2015.
 */

public class EventosNegocio extends AsyncTask<String,Void,ArrayList>{
    InputStream is = null;
    String urlEventos = "http://coterodev.esy.es/eventos/eventos.php";
    String line = null;
    String resultado = null;

    public ArrayList<Evento> listaEventos;
    public String[] nombres, lugares, fecha ,fechab ,cartel;

    public void obtenerEventos() {
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

    public ArrayList cargarEventos(){
        listaEventos = new ArrayList<Evento>();
        Evento nuevoEvento;

        for (int i = 0; i < nombres.length; i ++) {
            nuevoEvento = new Evento(nombres[i], lugares[i], fecha[i], fechab[i], cartel[i]);
            listaEventos.add(nuevoEvento);
        }

        return  listaEventos;
    }


    @Override
    protected ArrayList doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
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

        return null;
    }
}
