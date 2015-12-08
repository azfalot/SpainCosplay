package coterodev.spaincosplay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carlos on 04/12/2015.
 */
public class SQLDatabase extends SQLiteOpenHelper {

    public SQLDatabase(Context context){

        super(context,"eventos", null, 1);
    }
    @Override public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE eventos ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT," +
                "lugar TEXT," +
                "fecha DATETIME )");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){
    }
}