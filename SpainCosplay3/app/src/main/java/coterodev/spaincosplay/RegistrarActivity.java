package coterodev.spaincosplay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by carlos on 08/12/2015.
 */
public class RegistrarActivity extends Activity {
    @Override public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.registro);
        Button button = (Button)findViewById(R.id.registarUsuarioBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onRegNuevoUserClick();
            }
        });
    }
    //Abrir conexion, hacer un insert, con los datos recogidos
    public void onRegNuevoUserClick(){
        //mensajero
        Toast toast;
        //Datos
        EditText nombre = (EditText) findViewById(R.id.nameR);
        EditText email = (EditText) findViewById(R.id.emailR);
        EditText usuario = (EditText) findViewById(R.id.userR);
        EditText contrasena1 = (EditText) findViewById(R.id.passR);
        EditText contrasena2 = (EditText) findViewById(R.id.passR2);
        String Snombre = nombre.getText().toString();
        String Semail = email.getText().toString();
        String Susuario = usuario.getText().toString();
        String passA = contrasena1.getText().toString();
        String passB = contrasena2.getText().toString();

        if ( !Snombre.equals("") && !Semail.equals("") && !Susuario.equals("") && !passA.equals("") && !passB.equals("")) {
            if (passA.equals(passB)) {
                try {
                    URL url = new URL("http://coterodev.esy.es/usuarios/registro.php?nombre=" + nombre.getText().toString()
                            + "&email=" + email.getText().toString()
                            + "&usuario=" + usuario.getText().toString() +
                            "&contrasena=" + contrasena1.getText().toString());

                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                        String linea = reader.readLine();
                        if (linea == null){linea="navidad";}
                        if (linea.equals("navidad")){
                            toast = Toast.makeText(getApplicationContext(), "Registro completado", Toast.LENGTH_SHORT);
                            toast.show();
                            reader.close();
                            super.onBackPressed();
                        }
                        if (!linea.equals("")) {
                            if (linea.contains(" key 'PRIMARY'")) {
                                toast = Toast.makeText(getApplicationContext(), "Ya existe una cuenta vinculada con este e-mail.", Toast.LENGTH_LONG);
                                toast.show();
                            }
                            if (linea.contains("key 'Usuario_")) {
                                toast = Toast.makeText(getApplicationContext(), "Ya existe una cuenta con ese usuario.", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }


                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                toast = Toast.makeText(getApplicationContext(), "No coinciden las contraseñas/No aporto un email", Toast.LENGTH_SHORT);
                toast.show();

            }
        }else {toast = Toast.makeText(getApplicationContext(), "Uno o Varios campos vacios", Toast.LENGTH_SHORT);toast.show();}
    }
}
