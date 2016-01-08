package coterodev.spaincosplay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class InformacionEventoActivity extends Activity {

    ObtenerImagenEvento obtenerImagenEvento;
    String lugarEvento = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Evento objetoEvento = (Evento)getIntent().getExtras().getSerializable("EventoP");
        setContentView(R.layout.activity_informacion_evento);
        obtenerImagenEvento = new ObtenerImagenEvento();
        TextView nombre = (TextView) findViewById(R.id.InfnombreEvento);
        TextView fecha = (TextView) findViewById(R.id.InffechaEvento);
        TextView lugar = (TextView) findViewById(R.id.InflugarEvento);
        nombre.setText(objetoEvento.getNombre());
        fecha.setText(objetoEvento.getFecha()+" - "+objetoEvento.getFecha_fin());
        lugar.setText(objetoEvento.getLugar());
        lugarEvento = objetoEvento.getLugar();
        ImageView imagenCartel =(ImageView) findViewById(R.id.InfCartelEvento);
        obtenerImagenEvento.execute(objetoEvento.getCartel());
        Bitmap imagen = null;
        try {
            imagen = obtenerImagenEvento.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        imagenCartel.setImageBitmap(imagen);

        Button GeoBoton = (Button)findViewById(R.id.InfgeoLocaBtn);
        GeoBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent GMaps = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr="+lugarEvento));
                startActivity(GMaps);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}