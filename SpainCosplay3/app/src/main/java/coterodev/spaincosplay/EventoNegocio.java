package coterodev.spaincosplay;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by carlos on 07/01/2016.
 */
public class EventoNegocio extends AsyncTask<String,Void,Evento>{

    String resultado, line;
    Evento eventoRespuesta;


    @Override
    protected Evento doInBackground(String... params) {
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

            for (int i = 0; i < jArray.length(); i++) {
                jObjeto = jArray.getJSONObject(i);
                eventoRespuesta = new Evento(jObjeto.getString("nombre"),
                                             jObjeto.getString("lugar"),
                                             jObjeto.getString("fecha"),
                                             jObjeto.getString("fecha_fin"),
                                             jObjeto.getString("cartel"));
            }

        } catch (Exception e) {
            Log.e("A JSON", e.toString());
        }


        return eventoRespuesta;
    }
}
