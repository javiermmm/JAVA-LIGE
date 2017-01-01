//EventoErrorPosicion.java

package lige.grupo03.pr6.editor.vista.Eventos;

import lige.grupo03.pr6.editor.modelo.Constantes.TipoEvento;

/**
 * Clase que define el evento para avisar del error en una posicion
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Evento
 */
public class EventoErrorPosicion extends Evento {

	/**
	 * Constructor predeterminado
	 */
	public EventoErrorPosicion() {
		_tipo = TipoEvento.ERRORPOSICION;
	}
}
