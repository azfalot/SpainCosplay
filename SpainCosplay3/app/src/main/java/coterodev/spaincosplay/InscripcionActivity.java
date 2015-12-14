package coterodev.spaincosplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    // Consulta de base de datos
    /*
    *   Preguntamos por el usuario el email y la contraseña
    *   si coincide con lo que ha escrito va dentro de la aplicacion
    *   si no coincide se le avisa.
    *
    */
    public void autenticar(){
        EditText inUsuario  = (EditText)findViewById(R.id.user);
        conectarBDMySQL(datosDB.getUsuario(),datosDB.getContrasena(),datosDB.getIp(),datosDB.getPuerto(),"Usuarios");
         //  = (EditText)findViewById(R.id.user)

       String consulta = "Select * from Usuarios Where nombre="+inUsuario.getText().toString();
    }

    public void conectarBDMySQL (String usuario, String contrasena, String ip, String puerto, String catalogo){
        /*
        usuario = "u412761491_cospa";
        contrasena = "ca123asd";
        ip = "mysql.hostinger.es";
        puerto= "3306";
        catalogo = "Usuarios";*/

        if (conexionMySQL == null)
        {
            String urlConexionMySQL = "";
            if (catalogo != "")
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" +	puerto + "/" + catalogo;
            else
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + puerto;
            if (usuario != "" & contrasena != "" & ip != "" & puerto != "")
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexionMySQL =	 DriverManager.getConnection(urlConexionMySQL,
                            usuario, contrasena);
                }
                catch (ClassNotFoundException e)
                {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
                catch (SQLException e)
                {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
