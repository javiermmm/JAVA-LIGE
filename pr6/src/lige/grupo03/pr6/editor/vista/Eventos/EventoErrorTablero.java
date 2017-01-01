//EventoErrorTablero.java

package lige.grupo03.pr6.editor.vista.Eventos;

import lige.grupo03.pr6.editor.modelo.Constantes.TipoEvento;

/**
 * Clase que define el evento para avisar del error en el tablero
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Evento
 */
public class EventoErrorTablero extends Evento {

	/**
	 * Constructor predeterminado
	 */
	public EventoErrorTablero() {
		_tipo = TipoEvento.ERRORTABLERO;
	}
}
