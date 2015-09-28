package colombia.game.trivia.colombiadiversa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import mundo.Juego;
import mundo.Opcion;
import mundo.Pregunta;


public class GameActivity extends AppCompatActivity {

    private ImageButton[] btnRespuestas;
    private TextView question;
    private int puntuacion;

    //Lista de preguntas
    private Pregunta pregunta;
    private Opcion[] opciones;
    private TextView[] textos;

    private Juego juego;

    private int cuenta=0;
    private int buenas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        // 2. get person object from intent
        juego = (Juego) getIntent().getSerializableExtra("mundo.Juego");

        if(juego.getRondaActual().isTerminoRonda()){
            juego.jugarRonda();
        }

        btnRespuestas = new ImageButton[4];
        btnRespuestas[0] = (ImageButton) findViewById(R.id.butOp1);
        btnRespuestas[1] = (ImageButton) findViewById(R.id.butOp2);
        btnRespuestas[2] = (ImageButton) findViewById(R.id.butOp3);
        btnRespuestas[3] = (ImageButton) findViewById(R.id.butOp4);

        textos = new TextView[4];
        textos[0] = (TextView) findViewById(R.id.p1);
        textos[1] = (TextView) findViewById(R.id.p2);
        textos[2] = (TextView) findViewById(R.id.p3);
        textos[3] = (TextView) findViewById(R.id.p4);
        /*
        btnRespuestas[0].setImageResource(R.drawable.blank);
        btnRespuestas[1].setImageResource(R.drawable.blank);
        btnRespuestas[2].setImageResource(R.drawable.blank);
        btnRespuestas[3].setImageResource(R.drawable.blank);
        */



        refescar();

        changeFont();
        //setContentView(R.layout.game_layout);
    }

    /**
     * Método encargado de hacer el cambio de las fuentes de los text view elegidos
     *
     */
    public void changeFont() {

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/trebuchet.ttf");

        question.setTypeface(custom_font);
        textos[0].setTypeface(custom_font);
        textos[1].setTypeface(custom_font);
        textos[2].setTypeface(custom_font);
        textos[3].setTypeface(custom_font);

    }
    public void refescar(){
        if(cuenta > 5){
            darResultados();
        }
        else {
            pregunta = juego.getRondaActual().getPreguntaActual();
            opciones = pregunta.getOpciones();
            puntuacion = juego.getPuntajeTotal();

            int i = 0;
            for (ImageButton img : btnRespuestas) {
                img.setImageResource(getImageId(this, opciones[i].getURL()));
                textos[i].setText(opciones[i].getOpcion());
                if (i==0 || i==3){

                    btnRespuestas[i].setBackgroundColor(getResources().getColor(R.color.Primary_Green));

                }else if(i ==1 || i == 2){

                    btnRespuestas[i].setBackgroundColor(getResources().getColor(R.color.Accent));
                }
                i++;
            }
            question = (TextView) findViewById(R.id.pregunta);
            question.setText(pregunta.getPregunta());

            cuenta++;
        }
    }

    public void darResultados(){
        Intent i = new Intent(GameActivity.this,ResultsActivity.class);
        i.putExtra("mundo.Juego",juego);
        i.putExtra("puntaje",MainActivity.puntos);
        i.putExtra("buenas",buenas);
        int malas = 5-buenas;
        i.putExtra("malas",malas);
        startActivity(i);
        finish();
    }

    public void option1(View v){

        btnRespuestas[0].setBackgroundColor(getResources().getColor(R.color.Cambio_Verde_Dark));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean correcta = juego.comprobarOpcion(0);

                if (correcta) {
                    MainActivity.puntos += 10;
                    buenas++;
                    Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Correcta" + puntuacion, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Incorrecta" + puntuacion, Toast.LENGTH_SHORT);
                    toast.show();
                }

                refescar();
            }
        }, 100);


        //nextQuestion();
    }

    public void option2(View v){

        btnRespuestas[1].setBackgroundColor(getResources().getColor(R.color.Cambio_verde_light));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean correcta = juego.comprobarOpcion(1);
                if (correcta) {
                    MainActivity.puntos += 10;
                    buenas++;
                    Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Correcta" + puntuacion, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Incorrecta" + puntuacion, Toast.LENGTH_SHORT);
                    toast.show();
                }
                refescar();
            }
        }, 100);

        //nextQuestion();
    }

    public void option3(View v){

        btnRespuestas[2].setBackgroundColor(getResources().getColor(R.color.Cambio_Verde_Dark));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean correcta = juego.comprobarOpcion(2);
                if (correcta) {
                    MainActivity.puntos += 10;
                    buenas++;
                    Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Correcta" + puntuacion, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Incorrecta" + puntuacion, Toast.LENGTH_SHORT);
                    toast.show();
                }
                refescar();
            }
        }, 100);

        //nextQuestion();
    }

    public void option4(View v){

        btnRespuestas[3].setBackgroundColor(getResources().getColor(R.color.Cambio_verde_light));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean correcta = juego.comprobarOpcion(3);
                if (correcta) {
                    MainActivity.puntos += 10;
                    buenas++;
                    Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Correcta" + puntuacion, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Incorrecta" + puntuacion, Toast.LENGTH_SHORT);
                    toast.show();
                }
                refescar();
            }
        }, 100);

        //nextQuestion();
    }

    @Override
    public void onBackPressed() {
        // do nothing.
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    public void verificarCorrecta(int  img){
        boolean correcta = juego.comprobarOpcion(img);

        if(correcta){
            Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Correcta - Tu puntaje es: " + puntuacion , Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Respuesta Incorrecta - Tu puntaje es: " + puntuacion , Toast.LENGTH_SHORT);
            toast.show();
        }
        nextQuestion();


    }

    public void nextQuestion(){
        Intent intent = new Intent(this, GameActivity.class);
        //intent.putExtra("Game", game);
        startActivity(intent);
    }
}
