package coterodev.spaincosplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by carlos on 08/12/2015.
 */
public class InscripcionActivity extends Activity {
    @Override public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.login);

        TextView registrar = (TextView)  findViewById(R.id.registrar);
        registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    Intent i = new Intent(getBaseContext(),RegistrarActivity.class);
                    startActivity(i);
            }
        });

    }
}
