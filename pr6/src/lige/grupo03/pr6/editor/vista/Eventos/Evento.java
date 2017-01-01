//Evento.java

package lige.grupo03.pr6.editor.vista.Eventos;

import lige.grupo03.pr6.editor.modelo.Constantes.TipoEvento;

/**
 * Clase que define una clase padre de todos los eventos, que especifica el tipo de evento.
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public abstract class Evento {

	//-------------------------
	//ATRIBUTOS
	//-------------------------
	protected TipoEvento _tipo;
	//-------------------------
	/**
	 * Accedente para el tipo de Evento
	 * @return Tipo del Evento
	 */
	public TipoEvento getTipo(){
		return _tipo;
	}
}
