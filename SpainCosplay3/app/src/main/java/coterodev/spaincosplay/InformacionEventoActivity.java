package coterodev.spaincosplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.concurrent.ExecutionException;

public class InformacionEventoActivity extends Activity {

    ObtenerImagenEvento obtenerImagenEvento = new ObtenerImagenEvento();
    String lugarEvento, nombrEvento, fechaEvento = null;
    Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contexto =  this.getApplicationContext();
        //Estructura
        setContentView(R.layout.activity_informacion_evento);
        TextView nombre = (TextView) findViewById(R.id.InfnombreEvento);
        TextView fecha = (TextView) findViewById(R.id.InffechaEvento);
        TextView lugar = (TextView) findViewById(R.id.InflugarEvento);
        ImageView imagenCartel =(ImageView) findViewById(R.id.InfCartelEvento);
        Button GeoBoton = (Button)findViewById(R.id.InfgeoLocaBtn);
        Button FbBtn = (Button)findViewById(R.id.fbBtn);
        //Button Compartir = (Button)findViewById(R.id.InfCompartirBtn);
        nombrEvento = nombre.toString();
        //Getter Objeto evento
        final Evento objetoEvento = (Evento)getIntent().getExtras().getSerializable("EventoP");
        //Setters
        nombre.setText(objetoEvento.getNombre());
        String FechaFin = objetoEvento.getFecha_fin().substring(0,objetoEvento.getFecha_fin().indexOf(" "));
        fecha.setText(objetoEvento.getFecha()+" - "+FechaFin);

        if (objetoEvento.getLugar().toString().equals("npi")) {
            lugar.setText("Ubicacion desconocida");
            GeoBoton.setVisibility(View.INVISIBLE);
        }else{
            lugar.setText(objetoEvento.getLugar());
            lugarEvento = objetoEvento.getLugar();
        }

        if (objetoEvento.getFacebook().toString().equals("")){
            FbBtn.setVisibility(View.INVISIBLE);
        }
        //Cargando Imagen
        Bitmap imagen = null;
        try {
            obtenerImagenEvento.execute(objetoEvento.getCartel());
            imagen = obtenerImagenEvento.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        imagenCartel.setImageBitmap(imagen);

        FbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String url = "fb://facewebmodal/f?href=" +objetoEvento.getFacebook().toString();
                try{

                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }catch (Exception d){
                    //Toast.makeText(contexto, "Es probable que no tenga la aplicacion de Facebook instalada",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(objetoEvento.getFacebook().toString()));
                    startActivity(i);
                }

            }
        });
        GeoBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                    Intent GMaps = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr="+lugarEvento));
                    startActivity(GMaps);

            }
        });
        /*Compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Evento: "+nombrEvento+ " se celebrara el próximo " +fechaEvento+ " más información en https://play.google.com/store/apps/details?id=coterodev.spaincosplay");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });*/
    }

    //Deberia entrar SIEMPRE
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}