package coterodev.spaincosplay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Azfalot on 24/12/2015.
 */
public class PrincipalActivity extends Activity {
    Toast toast;
    Locale myLocale;
    @Override
    public void onCreate(Bundle IstanciaSalvada){
        super.onCreate(IstanciaSalvada);
        setContentView(R.layout.principal_layout2);
        Button button = (Button)findViewById(R.id.verEventosBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("eventos");
            }
        });
        Button button2 = (Button)findViewById(R.id.acercaDeBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("acercade");
            }
        });
       /* Button button3 = (Button)findViewById(R.id.cerrarSesionBtn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("cerrarsesion");
            }
        });*/
        Button button4 = (Button)findViewById(R.id.salirBtn);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("salir");
            }
        });
        Button ingles = (Button)findViewById(R.id.ingles);
        Button español = (Button)findViewById(R.id.español);
        Button japones = (Button)findViewById(R.id.japones);
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
                toast = Toast.makeText(getApplicationContext(), R.string.cargando, Toast.LENGTH_SHORT);
                toast.show();
                Intent i = new Intent(PrincipalActivity.this, ListaEventosActivity.class);
                startActivity(i);
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
}