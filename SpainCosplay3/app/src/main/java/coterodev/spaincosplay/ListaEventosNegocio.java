package coterodev.spaincosplay;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by carlos on 02/01/2016.
 */
public class ListaEventosNegocio extends AsyncTask<String,Void,ArrayList> {
    InputStream is = null;
    String line = null;
    String resultado = null;
    public String[] nombres;

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

            for (int i = 0; i < jArray.length(); i++) {

                jObjeto = jArray.getJSONObject(i);
                nombres[i] = jObjeto.getString("nombre");

            }

        } catch (Exception e) {
           // Log.e("A JSON", e.toString());
        }

        return null;
    }

    public String[] getNombres() {
        return nombres;
    }

}

