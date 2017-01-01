//EventoErrorBarco.java

package lige.grupo03.pr6.editor.vista.Eventos;

import lige.grupo03.pr6.editor.modelo.Constantes.TipoEvento;

/**
 * Clase que define el evento para avisar del error en un barco
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Evento
 */
public class EventoErrorBarco extends Evento{

	/**
	 * Constructor predeterminado
	 */
	public EventoErrorBarco() {
		_tipo = TipoEvento.ERRORBARCO;
	}
}
