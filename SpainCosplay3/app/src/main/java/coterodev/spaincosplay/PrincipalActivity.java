package coterodev.spaincosplay;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import java.util.Locale;

public class PrincipalActivity extends Activity {
    Toast toast;
    Locale myLocale;
    @Override
    public void onCreate(Bundle IstanciaSalvada){
        super.onCreate(IstanciaSalvada);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.principal_layout2);
        //Declaracion botones
        Button verEventos = (Button)findViewById(R.id.verEventosBtn);
        Button acercaDe = (Button)findViewById(R.id.acercaDeBtn);
        Button salirBtn = (Button)findViewById(R.id.salirBtn);
        Button ingles = (Button)findViewById(R.id.ingles);
        Button español = (Button)findViewById(R.id.español);
        Button japones = (Button)findViewById(R.id.japones);
        Button compartir = (Button)findViewById(R.id.share);

       /* Button button3 = (Button)findViewById(R.id.cerrarSesionBtn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("cerrarsesion");
            }
        });*/

        //Eventos
        verEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("eventos");
            }
        });
        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("acercade");
            }
        });
        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("salir");
            }
        });
        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("compartir");
            }
        });
        ingles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setLocale("en");
            }
        });
        español.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setLocale("es");
            }
        });
        japones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setLocale("ja");
            }
        });
    }

    public void lanzarVista(String parametro){
        switch (parametro){
            case "eventos":
                Intent i = new Intent(PrincipalActivity.this, ListaEventosActivity.class);
                startActivity(i);
                toast = Toast.makeText(getApplicationContext(), R.string.cargando, Toast.LENGTH_SHORT);
                toast.show();
                break;
            case "acercade":
                i = new Intent(this, AcercaDe.class);
                startActivity(i);
                break;
            case "cerrarsesion":
                i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
            case "salir":
                finish();
                System.exit(0);
                break;
            case "compartir":
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Comprueba los próximos eventos a través de esta aplicación: https://play.google.com/store/apps/details?id=coterodev.spaincosplay");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
        }
    }

    public void setLocale(String lang){
            myLocale = new Locale(lang);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, PrincipalActivity.class);
            startActivity(refresh);
            finish();
    }

    public void cargarNotificacion(){
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification;
        final String appPackageName = getPackageName();
        int icon = R.drawable.icono;
        //Icon icono = Icon.createWithResource(getApplicationContext(),R.drawable.icono);
        CharSequence tickerText = "Nueva Actualizacion Disponible";
        long when = System.currentTimeMillis(); //cuando se lanza la nota
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(getApplicationContext())
                    .setContentText(tickerText)
                    .setSmallIcon(icon)
                   // .setLargeIcon(icono)
                    .setWhen(when)
                    .build();

        }
        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        notification.

    }
}