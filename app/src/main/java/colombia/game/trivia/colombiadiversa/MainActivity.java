package colombia.game.trivia.colombiadiversa;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import mundo.Juego;


public class MainActivity extends AppCompatActivity {

    private ImageButton butPlay;
    private ImageButton butTrophies;
    private ImageButton butAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide the status bar and other OS-level chrome
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butPlay=(ImageButton) findViewById(R.id.butPlay);
        butTrophies=(ImageButton) findViewById(R.id.butTrofeo);
        butAbout=(ImageButton) findViewById(R.id.butAbout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onPlay(View v){

    }

    public void onAbout(View v){

    }

    public void onTrophies(View v){

    }

    public void cambiarActividad(int actividad){
        Juego game = new Juego(null);
        if(actividad == 1) {
            Intent intent = new Intent(this, GameActivity.class);
            //intent.putExtra("Game", game);
            startActivity(intent);
        }else if(actividad == 2){
            Intent intent = new Intent(this, TrophiesActivity.class);
            //intent.putExtra("Game", game);
            startActivity(intent);
        }
    }
}
