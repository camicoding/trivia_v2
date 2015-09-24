package mundo;

public class Trofeo 
{
	// ---------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------
	
	private String URL;
	private boolean isBloqued;
	private int puntajeRequerido;
	
	// ---------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------
	
	public Trofeo(String URL, int puntaje) 
	{
		this.URL = URL;
		this.puntajeRequerido = puntaje;
		isBloqued = true;
	}
	
	// ---------------------------------------------------------
	// Metodos
	// ---------------------------------------------------------

	public int puntajeFaltante(int puntaje)
	{
		return puntaje-puntajeRequerido;
	}
	
	public boolean desbloquearTrofeo(int puntaje)
	{
		if(puntajeRequerido <= puntaje)
		{
			isBloqued = false;
		}
		
		return isBloqued;
	}
	
	// ---------------------------------------------------------
	// Gets and Sets
	// ---------------------------------------------------------

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public boolean isBloqued() {
		return isBloqued;
	}

	public void setBloqued(boolean isBloqued) {
		this.isBloqued = isBloqued;
	}

	public int getPuntajeRequerido() {
		return puntajeRequerido;
	}

	public void setPuntajeRequerido(int puntajeRequerido) {
		this.puntajeRequerido = puntajeRequerido;
	}
	
}
