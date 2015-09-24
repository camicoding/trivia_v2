package colombia.game.trivia.colombiadiversa;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import mundo.Juego;
import mundo.Opcion;
import mundo.Pregunta;


public class GameActivity extends AppCompatActivity {

    private ImageButton[] btnRespuestas;
    private EditText question;
    private int puntuacion;

    //Lista de preguntas
    private Juego game;
    private Pregunta pregunta;
    private Opcion[] opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        // Inicializan con datos el mapa
        game = (Juego) getIntent().getSerializableExtra("Game");
        if(game.getRondaActual().isTerminoRonda()){
            game.jugarRonda();
        }

        pregunta = game.getRondaActual().getPreguntaActual();
        opciones = pregunta.getOpciones();
        puntuacion = game.getPuntajeTotal();
        btnRespuestas = new ImageButton[4];
        btnRespuestas[0] = (ImageButton) findViewById(R.id.butOp1);
        btnRespuestas[1] = (ImageButton) findViewById(R.id.butOp2);
        btnRespuestas[2] = (ImageButton) findViewById(R.id.butOp3);
        btnRespuestas[3] = (ImageButton) findViewById(R.id.butOp4);

        int i = 0;
        for(ImageButton img : btnRespuestas){
            // Añado la respuesta que deberia mostrarse en los cuadros
            // No se como poner de forma automatica el id del drawable
            img.setImageResource(R.drawable.q15_o4);
            i++;
        }
        question = (EditText) findViewById(R.id.editText);
        question.setText(pregunta.getPregunta());

        //setContentView(R.layout.game_layout);
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
        boolean correcta = game.comprobarOpcion(img);

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
