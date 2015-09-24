package mundo;

public class Pregunta
{
	// ---------------------------------------------------------
	// Constantes
	// ---------------------------------------------------------

	public final static int CANT_OPCIONES = 4;
	
	// ---------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------
	
	private String pregunta;
	private Opcion[] opciones;
	private boolean isResolved;
	private boolean isElegidaRonda;
	
	// ---------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------
	
	public Pregunta(String pregunta, Opcion[] opciones) 
	{
		this.pregunta = pregunta;
		this.opciones = opciones;
		this.isResolved = false;
		this.isElegidaRonda = false;
	}
	
	// ---------------------------------------------------------
	// Metodos
	// ---------------------------------------------------------
	
	public boolean isOpcionCorrecta(int opcion)
	{
		boolean respuesta = opciones[opcion].isCorrect();
		isResolved = (respuesta)?true:false;
		
		return respuesta;
	}
	
	public void elegirParaRonda()
	{
		isElegidaRonda = true;
	}
	
	public void quitarEleccionRonda()
	{
		isElegidaRonda = false;
	}
	// ---------------------------------------------------------
	// Gets and Sets
	// ---------------------------------------------------------


	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Opcion[] getOpciones() {
		return opciones;
	}

	public void setOpciones(Opcion[] opciones) {
		this.opciones = opciones;
	}

	public boolean isResolved() {
		return isResolved;
	}

	public void setResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}

	public boolean isElegidaRonda() {
		return isElegidaRonda;
	}

	public void setElegidaRonda(boolean isElegidaRonda) {
		this.isElegidaRonda = isElegidaRonda;
	}
	
	
	
}
