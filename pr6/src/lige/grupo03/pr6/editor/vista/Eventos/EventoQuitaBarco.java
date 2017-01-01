//EventoQuitaBarco.java

package lige.grupo03.pr6.editor.vista.Eventos;

import lige.grupo03.pr6.editor.modelo.Posicion;
import lige.grupo03.pr6.editor.modelo.Constantes.TipoEvento;

/**
 * Clase que define el evento para informar a la vista de que se quita un barco
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see EventoPonerQuitar
 */
public class EventoQuitaBarco extends EventoPonerQuitar{

	/**
	 * Constructor parametrizado
	 * @param posiciones Array con las posiciones que deben cambiar en el escenario
	 * @param numPortaaviones Numero de portaaviones que quedan por colocar
	 * @param numAcorazados Numero de acorazados que quedan por colocar
	 * @param numFragatas Numero de fragatas que quedan por colocar
	 * @param numSubmarinos Numero de submarinos que quedan por colocar
	 */
	public EventoQuitaBarco(Posicion[] posiciones, int numPortaaviones, int numAcorazados, int numFragatas, int numSubmarinos) {
		super(posiciones, numPortaaviones, numAcorazados, numFragatas, numSubmarinos);
		_tipo = TipoEvento.QUITAR;
	}

}
