package coterodev.spaincosplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by carlos on 08/12/2015.
 */
public class InscripcionActivity extends AppCompatActivity {

    public static  Usuario _usuario;
    @Override protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.login);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        Button button = (Button)findViewById(R.id.iniciarSesion);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                authUsuario();
            }
        });
    }

    public void lanzarRegistro(View view){
        Intent i = new Intent(InscripcionActivity.this,RegistrarActivity.class);
        startActivity(i);
    }

    public void authUsuario(){

        //http://coterodev.esy.es/usuarios/auth_user.php?@utz89107$%=

        //mensajero
        Toast toast;
        //Datos
        EditText usuario = (EditText) findViewById(R.id.user);
        EditText email = (EditText) findViewById(R.id.email);
        EditText contrasena = (EditText) findViewById(R.id.pass);
        String Susuario = usuario.getText().toString();
        String Semail = email.getText().toString();
        String Scontrasena = contrasena.getText().toString();
        String correo = "";
        String password = "";


        if ( !Susuario.equals("") && !Semail.equals("") && !Scontrasena.equals("")) {
                try {
                    URL url = new URL("http://coterodev.esy.es/usuarios/auth_user.php?@utz89107$a=" + Susuario);

                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                        String lectura = reader.readLine();
                        correo = lectura.substring(0, lectura.indexOf(" "));
                        password = lectura.substring(lectura.indexOf(" ") + 1);

                        //if (correo.compareTo(Semail) == 0  && password.compareTo(Scontrasena) == 0){
                        if (correo.contains(Semail) && password.contains(Scontrasena)){
                        /*
                        _usuario.setUsuario(Susuario);
                        _usuario.setEmail(Semail);
                        _usuario.setContraseña(password);*/
                        //Lanzamos MainActivity
                        Intent i = new Intent(InscripcionActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                        else{
                            if (!correo.contains(Semail)){
                                toast = Toast.makeText(getApplicationContext(), "Email incorrecto", Toast.LENGTH_SHORT);toast.show();
                            }
                            else{
                                toast = Toast.makeText(getApplicationContext(), "Usuario y/o Constraseña incorrectos", Toast.LENGTH_SHORT);toast.show();
                            }

                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (NullPointerException n){
                    n.printStackTrace();
                }

        }else {toast = Toast.makeText(getApplicationContext(), "Uno o Varios campos vacios", Toast.LENGTH_SHORT);toast.show();}
    }
}

