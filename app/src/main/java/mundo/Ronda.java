package mundo;

public class Ronda 
{
	// ---------------------------------------------------------
	// Constantes
	// ---------------------------------------------------------
	
	public final static int CANT_PREGUNTAS_RONDA = 5;
	public final static int PUNTAJE_PREGUNTA = 10;
	
	// ---------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------
	
	private int puntajeParcial;
	private Pregunta[] preguntas;
	private Pregunta preguntaActual;
	private boolean terminoRonda;
	private int contador;
	
	// ---------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------
	
	public Ronda(Pregunta[] preguntas) 
	{
		puntajeParcial = 0;
		this.preguntas = preguntas;
		contador = 0;
		preguntaActual = preguntas[contador];
		terminoRonda = false;
	}
	
	// ---------------------------------------------------------
	// Metodos
	// ---------------------------------------------------------

	public boolean jugar(int opcion)
	{
		boolean respuesta = preguntaActual.isOpcionCorrecta(opcion);
		contador++;
		puntajeParcial += (respuesta)?PUNTAJE_PREGUNTA:0;
		
		if(contador >= CANT_PREGUNTAS_RONDA)
		{
			terminoRonda = true;
		}
		else	
		{
			preguntaActual = preguntas[contador];
		}
		
		return respuesta;
	}
	
	
	// ---------------------------------------------------------
	// Gets and Sets
	// ---------------------------------------------------------

	public int getPuntajeParcial() {
		return puntajeParcial;
	}

	public void setPuntajeParcial(int puntajeParcial) {
		this.puntajeParcial = puntajeParcial;
	}

	public Pregunta[] getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Pregunta[] preguntas) {
		this.preguntas = preguntas;
	}

	public Pregunta getPreguntaActual() {
		return preguntaActual;
	}

	public void setPreguntaActual(Pregunta preguntaActual) {
		this.preguntaActual = preguntaActual;
	}

	public boolean isTerminoRonda() {
		return terminoRonda;
	}

	public void setTerminoRonda(boolean terminoRonda) {
		this.terminoRonda = terminoRonda;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
}
