package mundo;

import android.content.res.AssetManager;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Juego implements Serializable
{
	// ---------------------------------------------------------
	// Constantes
	// ---------------------------------------------------------
	
	public final static String URL_PUNTAJE = "puntaje";
	public final static String URL_PREGUNTAS = "preguntas";
	public final static String URL_TROFEOS = "trofeos";
	public final static int CANT_PREGUNTAS = 50;
	public final static int CANT_TROFEOS = 9;
	
	// ---------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------
	
	private ArrayList<Pregunta> preguntas;
	private Ronda rondaActual;
	private int puntajeTotal;
	private ArrayList<Trofeo> trofeos;
	
	// ---------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------
	
	public Juego(AssetManager asset)
	{
		preguntas = new ArrayList<>();
		rondaActual = null;
		puntajeTotal = cargarPuntajeTotal(asset);
		trofeos = new ArrayList<>();
		cargarPreguntas(asset);
		cargarTrofeos(asset);
	}
	
	// ---------------------------------------------------------
	// Metodos de Carga de Archivos
	// ---------------------------------------------------------

	public int cargarPuntajeTotal(AssetManager asset)
	{
		//File file = new File(URL_PUNTAJE);

        int puntaje;

        puntaje = 0;
        try
		{
            InputStream file = asset.open(URL_PUNTAJE);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(file));
			String[] cadena = entrada.readLine().split("=");
			puntaje = Integer.parseInt(cadena[1]);
		} 
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return puntaje;
	}
	
	public void cerrarJuego()
	{
		File file = new File(URL_PUNTAJE);
		try
		{
			BufferedWriter entrada = new BufferedWriter(new FileWriter(file));
			entrada.write("puntaje="+puntajeTotal);
		} 
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cargarTrofeos(AssetManager asset)
	{
		try
		{
            InputStream file = asset.open(URL_TROFEOS);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(file));
			
			for(int i = 0; i < CANT_TROFEOS; i++)
			{
				String URL = entrada.readLine().substring(2);
				int puntaje = Integer.parseInt(entrada.readLine().substring(8));
				
				Trofeo nuevo = new Trofeo(URL, puntaje);
				trofeos.add(nuevo);
			}
			
			entrada.close();
		} 
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cargarPreguntas(AssetManager asset)
	{
		try
		{
            InputStream file = asset.open(URL_PREGUNTAS);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(file));
			
			for(int i = 1; i <= CANT_PREGUNTAS; i++)
			{				
				String pregunta = entrada.readLine().substring(2);
				int respuesta = Integer.parseInt(entrada.readLine().substring(10));
				boolean isImagen = entrada.readLine().substring(7).equals("si");
				Opcion[] opciones = new Opcion[4];
				
				for(int j = 0; j < 4; j++)
				{
					if(isImagen)
					{
						String[] temp = entrada.readLine().split(";");
						String opcion = temp[1];
						String URL = temp[0];
						
						opciones[j] = new Opcion(opcion, j==respuesta);
						opciones[j].cargarImagen(URL);
					}
					
					else
					{
						String opcion = entrada.readLine();
						opciones[j] = new Opcion(opcion, j==respuesta);
					}
				}
				
				Pregunta nueva = new Pregunta(pregunta, opciones);
				preguntas.add(nueva);
			}
			
			entrada.close();
			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ---------------------------------------------------------
	// Metodos para Ronda
	// ---------------------------------------------------------
	
	public Pregunta[] darPreguntasRonda()
	{
		int contador = 0;
		Pregunta[] preguntasRonda = new Pregunta[Ronda.CANT_PREGUNTAS_RONDA];
		
		while(contador < Ronda.CANT_PREGUNTAS_RONDA)
		{
			System.out.println(Math.random()*preguntas.size());
			int aleatorio = (int)(Math.random()*preguntas.size());
			Pregunta temp = preguntas.get(aleatorio);
			
			if(!temp.isResolved()&&!temp.isElegidaRonda())
			{
				temp.elegirParaRonda();
				preguntasRonda[contador] = temp;
				contador++;
			}
		}
		
		return preguntasRonda;
	}
	
	public void jugarRonda()
	{
		rondaActual = new Ronda(darPreguntasRonda());
	}
	
	public boolean comprobarOpcion(int opcion)
	{
		boolean respuesta = rondaActual.jugar(opcion);
		
		if(terminoRondaActual())
		{
			puntajeTotal += rondaActual.getPuntajeParcial();
		}
		
		return respuesta;
	}
	
	public int darPuntajeParcial()
	{
		return rondaActual.getPuntajeParcial();
	}
	
	public Pregunta preguntaActual()
	{
		rondaActual.getPreguntaActual().quitarEleccionRonda();
		return rondaActual.getPreguntaActual();
	}
	
	public boolean terminoRondaActual()
	{
		return rondaActual.isTerminoRonda();
	}
	
	// ---------------------------------------------------------
	// Metodos para Trofeos
	// ---------------------------------------------------------

	public void desbloquearTrofeos()
	{
		for(int i = 0; i < trofeos.size(); i++)
		{
			Trofeo temp = trofeos.get(i);
			temp.desbloquearTrofeo(puntajeTotal);
		}
	}
	
	public Trofeo darTrofeoCercaDesbloquear()
	{
		Trofeo masCercano = trofeos.get(0);
		
		for(int i = 1; i < trofeos.size(); i++)
		{
			Trofeo temp = trofeos.get(i);
			
			if(masCercano.puntajeFaltante(puntajeTotal)>temp.puntajeFaltante(puntajeTotal))
			{
				masCercano = temp;
			}
		}
		
		return masCercano;
	}
	
	// ---------------------------------------------------------
	// Gets and Sets
	// ---------------------------------------------------------

	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public Ronda getRondaActual() {
		return rondaActual;
	}

	public void setRondaActual(Ronda rondaActual) {
		this.rondaActual = rondaActual;
	}

	public int getPuntajeTotal() {
		return puntajeTotal;
	}

	public void setPuntajeTotal(int puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

	public ArrayList<Trofeo> getTrofeos() {
		return trofeos;
	}

	public void setTrofeos(ArrayList<Trofeo> trofeos) {
		this.trofeos = trofeos;
	}

}
