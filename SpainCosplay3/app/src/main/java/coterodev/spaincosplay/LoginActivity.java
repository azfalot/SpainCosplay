package coterodev.spaincosplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by carlos on 08/12/2015.
 */
public class LoginActivity extends AppCompatActivity {

    public Usuario _usuario;
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
        Intent i = new Intent(LoginActivity.this,RegistrarActivity.class);
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
        final String Susuario = usuario.getText().toString();
        final String Semail = email.getText().toString();
        final String Scontrasena = contrasena.getText().toString();
        String correo = "";
        String password = "";


        if ( !Susuario.equals("") && !Semail.equals("") && !Scontrasena.equals("")) {
                try {
                    URL url = new URL("http://coterodev.esy.es/usuarios/auth_user.php?@utz89107$a=" + Susuario);

                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                        String lectura = reader.readLine();
                        if (lectura.length() > 6) {
                            correo = lectura.substring(1, lectura.indexOf(" ")).toString();
                            password = lectura.substring(lectura.indexOf(" ") + 1).toString();
                            //Si contienen datos Acedemos
                                if (Scontrasena.equalsIgnoreCase(password) && Semail.equalsIgnoreCase(correo)) {
                                _usuario = new Usuario(Semail,Susuario,Scontrasena);
                                    CheckBox checkBoxInicio = (CheckBox) findViewById(R.id.checkBoxLogin);

                                    if (checkBoxInicio.isChecked()){
                                        crearInformacionCuenta(_usuario);
                                    }
                                    //Lanzamos MainActivity
                                    Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    if (!Semail.equalsIgnoreCase(correo)) {
                                        toast = Toast.makeText(getApplicationContext(), "Email incorrecto", Toast.LENGTH_SHORT);
                                        toast.show();
                                    } else {
                                        toast = Toast.makeText(getApplicationContext(), "Usuario y/o Constraseña incorrectos", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }

                                }
                        }
                        else{toast = Toast.makeText(getApplicationContext(), "Esta cuenta no existe", Toast.LENGTH_SHORT);
                            toast.show();}
                    }
                    //Aqui no deberia hacer nada
                } catch (MalformedURLException e) {
                    Log.e("Spain Cosplay:", e.getMessage(), e);
                } catch (IOException e) {
                    Log.e("Spain Cosplay:", e.getMessage(), e);
                }
                catch (NullPointerException n){
                    Log.e("Spain Cosplay:", n.getMessage(), n);
                }

        }else {toast = Toast.makeText(getApplicationContext(), "Uno o Varios campos vacios", Toast.LENGTH_SHORT);toast.show();}
    }
    //CHKBOX FUNCIONALIDAD
    private void crearInformacionCuenta(Usuario _usuario) {

        String fichero = "datos.sp";
        FileOutputStream fos;
        try{
            fos = openFileOutput(fichero, Context.MODE_PRIVATE);
            this.getFilesDir();
            fos.write(_usuario.getUsuario().getBytes());
            fos.write(_usuario.getEmail().getBytes());
            fos.write(_usuario.getContraseña().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("Spain Cosplay:",e.getMessage(),e);
        } catch (IOException e) {
            Log.e("Spain Cosplay:", e.getMessage(), e);
        }
    }
}

