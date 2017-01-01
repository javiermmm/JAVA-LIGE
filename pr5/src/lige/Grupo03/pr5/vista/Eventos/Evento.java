//Evento.java

package lige.Grupo03.pr5.vista.Eventos;

import lige.Grupo03.pr5.modelo.Constantes.TipoEvento;

/**
 * Clase que define una clase padre de todos los eventos, que especifica el tipo de evento.
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class Evento {

	//-------------------------
	//ATRIBUTOS
	//-------------------------
	protected TipoEvento _tipo;
	//-------------------------
	
	
	public TipoEvento getTipo(){
		return _tipo;
	}
}
