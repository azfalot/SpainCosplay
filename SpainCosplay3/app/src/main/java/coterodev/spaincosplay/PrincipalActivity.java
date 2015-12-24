package coterodev.spaincosplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Azfalot on 24/12/2015.
 */
public class PrincipalActivity extends Activity{

    @Override
    public void onCreate(Bundle IstanciaSalvada){
        super.onCreate(IstanciaSalvada);
        setContentView(R.layout.principal_layout2);

        Button button = (Button)findViewById(R.id.verEventos);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("e");
            }
        });
        Button button2 = (Button)findViewById(R.id.ajustes);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("a");
            }
        });
        Button button3 = (Button)findViewById(R.id.salir);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("s");
            }
        });
    }

    public void lanzarVista(String parametro){
        switch (parametro){
            case "e":
                Intent i = new Intent(PrincipalActivity.this, EventosActivity.class);
                startActivity(i);
                break;
            case "a":

                break;
            case "s":
                finish();
                System.exit(0);
                break;
        }
    }
}
