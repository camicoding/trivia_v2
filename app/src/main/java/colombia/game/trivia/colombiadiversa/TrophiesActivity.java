package colombia.game.trivia.colombiadiversa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import mundo.Juego;
import mundo.Trofeo;


public class TrophiesActivity extends AppCompatActivity {

    private ImageView butTrofeo1;
    private ImageView butTrofeo2;
    private ImageView butTrofeo3;
    private ImageView butTrofeo4;
    private ImageView butTrofeo5;
    private ImageView butTrofeo6;
    private ImageView butTrofeo7;
    private ImageView butTrofeo8;
    private ImageView butTrofeo9;
    private ImageButton volverJugar;

    private int puntuacion;
    private ImageView[]  trofeos;
    private ArrayList<Trofeo> trofeos2 ;

    private Juego juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trofeos);

        trofeos = new ImageView[9];
        trofeos2 = new ArrayList<Trofeo>(9);

        volverJugar = (ImageButton) findViewById(R.id.btnVolverT);

        butTrofeo1=(ImageView) findViewById(R.id.butTro1);
        butTrofeo2=(ImageView) findViewById(R.id.butTro2);
        butTrofeo3=(ImageView) findViewById(R.id.butTro3);
        butTrofeo4=(ImageView) findViewById(R.id.butTro4);
        butTrofeo5=(ImageView) findViewById(R.id.butTro5);
        butTrofeo6=(ImageView) findViewById(R.id.butTro6);
        butTrofeo7=(ImageView) findViewById(R.id.butTro7);
        butTrofeo8=(ImageView) findViewById(R.id.butTro8);
        butTrofeo9=(ImageView) findViewById(R.id.butTro9);

        trofeos[0] = (ImageView) findViewById(R.id.butTro1);
        trofeos[1] = (ImageView) findViewById(R.id.butTro2);
        trofeos[2] = (ImageView) findViewById(R.id.butTro3);
        trofeos[3] = (ImageView) findViewById(R.id.butTro4);
        trofeos[4] = (ImageView) findViewById(R.id.butTro5);
        trofeos[5] = (ImageView) findViewById(R.id.butTro6);
        trofeos[6] = (ImageView) findViewById(R.id.butTro7);
        trofeos[7] = (ImageView) findViewById(R.id.butTro8);
        trofeos[8] = (ImageView) findViewById(R.id.butTro9);

        // 2. get person object from intent
        juego = (Juego) getIntent().getSerializableExtra("mundo.Juego");

        trofeos2 = juego.getTrofeos();
       // puntuacion = juego.getPuntajeTotal();
        puntuacion = MainActivity.puntos;

        int i = 0;
        for(Trofeo t : trofeos2){
            if(puntuacion >= t.getPuntajeRequerido()){
                //trofeos[i].setVisibility(View.VISIBLE);
                trofeos[i].setImageResource(getImageId(this, "trofeo"+(i+1)));
            }
            /*else{
                trofeos[i].setVisibility(View.INVISIBLE);
            }
            */
            i++;
        }
        //setContentView(R.layout.trofeos);
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    public void destaparTrofeos(int puntaje){
       /*
       if(puntaje>=50){
            butTrofeo1.setBackgroundResource(R.drawable.id);
        }
        */
    }

    public void onVolverT(View v){

        Intent i = new Intent(TrophiesActivity.this, MainActivity.class);
        i.putExtra("mundo.Juego",juego);
        startActivity(i);
        volverJugar.setBackgroundColor(getResources().getColor(R.color.Cambio_Blue));
        this.finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trophies, menu);
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
