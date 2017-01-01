//EventoErrorSintaxis.java

package lige.grupo03.pr6.editor.vista.Eventos;

import lige.grupo03.pr6.editor.modelo.Constantes.TipoEvento;

/**
 * Clase que define el evento para avisar del error de sintaxis
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Evento
 */
public class EventoErrorSintaxis extends Evento{

	/**
	 * Constructor predeterminado
	 */
	public EventoErrorSintaxis() {
		_tipo = TipoEvento.ERRORSINTAXIS;
	}
}
