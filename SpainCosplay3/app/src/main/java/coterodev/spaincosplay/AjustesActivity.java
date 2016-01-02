package coterodev.spaincosplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AjustesActivity extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        Button button = (Button)findViewById(R.id.acercade);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("a");
            }
        });
        Button button2 = (Button)findViewById(R.id.cerrar_sesion);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lanzarVista("c");
            }
        });
    }

    public void lanzarVista(String parametro){
        switch (parametro){
            case "a":
                i = new Intent(this, AcercaDe.class);
                startActivity(i);
                break;
            case "c":
                i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
