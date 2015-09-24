package colombia.game.trivia.colombiadiversa;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import mundo.Juego;


public class ResultsActivity extends AppCompatActivity {

    private Juego juego;

    private EditText buenas;
    private EditText malas;
    private EditText puntosGanados;
    private EditText puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        buenas=(EditText) findViewById(R.id.editText);
        malas=(EditText) findViewById(R.id.editText2);
        puntosGanados=(EditText) findViewById(R.id.editText3);
        puntaje=(EditText) findViewById(R.id.editText4);

        // 2. get person object from intent
        juego = (Juego) getIntent().getSerializableExtra("mundo.Juego");
        int numpuntaje = (Integer) getIntent().getSerializableExtra("puntaje");
        int numbuenas = (Integer) getIntent().getSerializableExtra("buenas");
        int nummalas = (Integer) getIntent().getSerializableExtra("malas");
        int numpuntajeGanado = numbuenas*10;

        buenas.setText(numbuenas+"");
        malas.setText(nummalas+"");
        puntosGanados.setText(numpuntajeGanado+"");
        puntaje.setText(MainActivity.puntos+" "+numpuntaje);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
