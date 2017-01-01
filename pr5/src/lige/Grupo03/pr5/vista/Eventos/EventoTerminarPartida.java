//EventoTerminaPartida.java

package lige.Grupo03.pr5.vista.Eventos;

import lige.Grupo03.pr5.modelo.Constantes.TipoEvento;

/**
 * Clase que recoge la informacion necesaria para atender a un evento para terminar partida
 * Apenas tiene informacion porque lo esencial es el aviso para destruir los paneles de
 * juego, es decir que se recibo un objeto de esta clase para indicar que se ha terminado
 * la partida sin haber hundido los barcos
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class EventoTerminarPartida extends Evento {

	public EventoTerminarPartida () {
		_tipo = TipoEvento.TERMINAPARTIDA;
	}
}
