package coterodev.spaincosplay;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class PrincipalActivity extends Activity {
    Toast toast;
    Locale myLocale;


    @Override
    public void onCreate(Bundle IstanciaSalvada) {
        super.onCreate(IstanciaSalvada);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.principal_layout2);
        //Declaracion botones
        Button verEventos = (Button) findViewById(R.id.verEventosBtn);
        Button acercaDe = (Button) findViewById(R.id.acercaDeBtn);
        Button salirBtn = (Button) findViewById(R.id.salirBtn);
        Button ingles = (Button) findViewById(R.id.ingles);
        Button español = (Button) findViewById(R.id.español);
        Button japones = (Button) findViewById(R.id.japones);
        Button compartir = (Button) findViewById(R.id.share);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

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


        int version = 0;
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        comprobarActualizacion(version);
    }
    //Lanzamiento de vistas
    public void lanzarVista(String parametro) {
        switch (parametro) {
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

    //Cambiar Idioma
    public void setLocale(String lang) {
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

    //1 - Construimos la Notificacion
    //2- La lanzamos a traves de un NotificactionManager
    private void comprobarActualizacion(int version) {
        URL url = null;
        String lectura;
        try {
            url = new URL("http://coterodev.esy.es/app/version.php");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(3000);
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                lectura = reader.readLine();
                lectura = lectura.substring(0,3);
                String valor = lectura.replaceAll("\\D+","");
                int versionNueva = Integer.parseInt(valor);
                if (version<versionNueva){
                    NotificationCompat.Builder notificacionVersion =
                            (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                                    .setSmallIcon(R.drawable.ic_notify)
                                    .setContentTitle(getString(R.string.update))
                                    .setContentText(getString(R.string.update2));
                    //Crear un Intent Explicito
                    Intent intentoMarket = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName()));
                    //Pila
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                    stackBuilder.addNextIntentWithParentStack(intentoMarket);
                    //Pending intent
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    //Establecer el ContentIntent
                    notificacionVersion.setContentIntent(resultPendingIntent);
                    notificacionVersion.setAutoCancel(true);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(getTaskId(),notificacionVersion.build());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this.getApplicationContext(), "Es posible que tenga problemas de red",Toast.LENGTH_LONG).show();

        }

    }
}