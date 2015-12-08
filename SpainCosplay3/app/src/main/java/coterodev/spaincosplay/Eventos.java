package coterodev.spaincosplay;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by carlos on 08/12/2015.
 */
public class Eventos extends ListActivity {
    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.retornarArray()));
    }
}
