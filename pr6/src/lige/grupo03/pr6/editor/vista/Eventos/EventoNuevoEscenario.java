//EventoNuevoEscenario.java

package lige.grupo03.pr6.editor.vista.Eventos;

import lige.grupo03.pr6.editor.modelo.Constantes.TipoEvento;

/**
 * Clase que define el evento con la informacion necesaria para crear un nuevo escenario
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see EventoSobreAbrirYNuevo
 */
public class EventoNuevoEscenario extends EventoSobreAbrirYNuevo{

	//------------------
	//ATRIBUTOS
	//------------------
	private int _ancho;
	private int _alto;
	//------------------
	
	/**
	 * Constructor parametrizado
	 * @param ancho Ancho del tablero
	 * @param alto Alto del tablero
	 */
	public EventoNuevoEscenario(int ancho, int alto) {
		_tipo = TipoEvento.NUEVO;
		_ancho = ancho;
		_alto = alto;
	}
	
	/**
	 * Accedente para el ancho
	 * @return Ancho del tablero
	 */
	public int getAncho() {
		return _ancho;
	}
	
	/**Accedente para el alto
	 * @return Alto del tablero
	 */
	public int getAlto() {
		return _alto;
	}
}
