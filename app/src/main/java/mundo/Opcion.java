package mundo;

public class Opcion 
{
	// ---------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------
	
	private String opcion;
	private String URL;
	private boolean isImagen;
	private boolean isCorrect;
	
	// ---------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------
	
	public Opcion(String opcion, boolean isCorrect) 
	{
		this.opcion = opcion;
		this.URL = "";
		this.isImagen = false;
		this.isCorrect = isCorrect;
	}
	
	// ---------------------------------------------------------
	// Metodos
	// ---------------------------------------------------------

	public void cargarImagen(String URL)
	{
		this.URL = URL;
		isImagen = true;
	}	
	
	// ---------------------------------------------------------
	// Gets and Sets
	// ---------------------------------------------------------

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public boolean isImagen() {
		return isImagen;
	}

	public void setImagen(boolean isImagen) {
		this.isImagen = isImagen;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}
