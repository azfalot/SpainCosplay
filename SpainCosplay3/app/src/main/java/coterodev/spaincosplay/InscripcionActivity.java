package coterodev.spaincosplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by carlos on 08/12/2015.
 */
public class InscripcionActivity extends AppCompatActivity {
    Connection conexionMySQL;
    Conexion datosDB;


    @Override protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.login);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
/*
        TextView registrar = (TextView) findViewById(R.id.registrar);
        registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(),RegistrarActivity.class);
                    startActivity(i);
            }
        });


   }*/
    }

    public void lanzarRegistro(View view){
        Intent i = new Intent(InscripcionActivity.this,RegistrarActivity.class);
        startActivity(i);
    }

}
