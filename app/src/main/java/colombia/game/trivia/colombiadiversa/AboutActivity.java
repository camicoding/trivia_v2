package colombia.game.trivia.colombiadiversa;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import mundo.Juego;


public class AboutActivity extends AppCompatActivity {

    private Juego juego;
    private TextView txtAbout;
    private TextView title;

    private ImageButton volverJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        txtAbout = (TextView) findViewById(R.id.txtAbout);
        title = (TextView) findViewById(R.id.title);
        volverJugar = (ImageButton) findViewById(R.id.btnVolverA);

        // 2. get person object from intent
        juego = (Juego) getIntent().getSerializableExtra("mundo.Juego");

        this.changeFont();
    }

    /**
     * Método encargado de hacer el cambio de las fuentes de los text view elegidos
     *
     */
    public  void changeFont() {

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/trebuchet.ttf");

        txtAbout.setTypeface(custom_font);
        title.setTypeface(custom_font);


    }

    public void onVolverA(View v){

        Intent i = new Intent(AboutActivity.this, MainActivity.class);
        i.putExtra("mundo.Juego",juego);
        startActivity(i);
        volverJugar.setBackgroundColor(getResources().getColor(R.color.Cambio_Blue));
        this.finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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
