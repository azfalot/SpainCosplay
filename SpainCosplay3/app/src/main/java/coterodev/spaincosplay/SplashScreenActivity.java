package coterodev.spaincosplay;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by Carlos on 04/12/2015.
 */
public class SplashScreenActivity extends Activity {

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Comprobar si existe el archivo
                /*String ret = "";
                try {
                   //FileInputStream fis = new FileInputStream(getFileStreamPath("datos.sp"));

                    //InputStream inputStream = openFileInput("/data/user/0/coterodev.spaincosplay/files/datos.sp");
                    //InputStream inputStream = openFileInput("/data/data/coterodev.spaincosplay/files/datos.sp");
                    InputStream inputStream = openFileInput("datos.sp");
                    if (inputStream != null) {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString = "";
                        StringBuilder stringBuilder = new StringBuilder();

                        while ((receiveString = bufferedReader.readLine()) != null) {
                            stringBuilder.append(receiveString);
                        }

                        inputStream.close();
                        ret = stringBuilder.toString();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (ret != ""){

                    if(comprobarUsuario(ret)== true){
                        // Start the next activity
                        Intent principalIntent = new Intent().setClass(
                                SplashScreenActivity.this, PrincipalActivity.class);
                        startActivity(principalIntent);
                    }

                }else{
                    // Start the next activity
                    Intent LoginIntent = new Intent().setClass(
                            SplashScreenActivity.this, LoginActivity.class);
                    startActivity(LoginIntent);
                }*/


                // TODO EL CODIGO COMENTADO ANTES ES PARA LAS NUEVAS FUNCIONALIDADES
                Intent Principal = new Intent().setClass(
                SplashScreenActivity.this, PrincipalActivity.class);
                startActivity(Principal);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    public boolean comprobarUsuario(String datos){
        boolean acertijo = false;
        String usuario = datos.substring(datos.indexOf("1")+1,datos.indexOf("2"));
        String email = datos.substring(datos.indexOf("2")+1,datos.indexOf("3"));
        String contraseña = datos.substring(datos.indexOf("3")+1);
        String correo, password;

        try{
            URL url = new URL("http://coterodev.esy.es/usuarios/auth_user.php?@utz89107$a=" + usuario);

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String lectura = reader.readLine();
                if (lectura.length() > 6) {
                    correo = lectura.substring(1, lectura.indexOf(" ")).toString();
                    password = lectura.substring(lectura.indexOf(" ") + 1).toString();
                    //Si contienen datos Acedemos
                    if (contraseña.equalsIgnoreCase(password) && email.equalsIgnoreCase(correo)) {
                        acertijo =  true;
                    } else {
                        acertijo = false;
                    }
                }
                else{return  false;}
            }

        } catch (MalformedURLException e) {
            //Log.e("Spain Cosplay:", e.getMessage(), e);
        } catch (IOException e) {
            //Log.e("Spain Cosplay:", e.getMessage(), e);
        }
        return  acertijo;
    }


}
