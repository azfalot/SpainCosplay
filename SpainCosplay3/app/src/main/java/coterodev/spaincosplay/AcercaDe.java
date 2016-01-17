package coterodev.spaincosplay;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Carlos on 03/12/2015.
 */
public class AcercaDe extends Activity {
    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acercade);
        Button FbBtn = (Button)findViewById(R.id.faceBtn);
        FbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String url = "fb://facewebmodal/f?href=" +"https://www.facebook.com/SpainCosplay";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });
    }
}