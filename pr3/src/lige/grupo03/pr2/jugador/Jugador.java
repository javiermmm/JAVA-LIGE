//Jugador.java

package lige.grupo03.pr2.jugador;

import lige.grupo03.pr2.logica.Posicion;
import lige.grupo03.pr2.logica.Tablero;
import lige.grupo03.pr2.logica.Constantes.Casilla;

/**
 * Clase abstracta que define el comportamiento general de un jugador, e implementa algunos métodos comunes en varios tipos de jugadores.
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Posicion
 * @see Tablero
 */
public abstract class Jugador {

	/**
	 * Método abstracto que cada tipo de jugador deberá implementar para escoger un movimiento de la manera adecuada
	 * @param t Tablero sobre el que se escoge el movimiento
	 * @return Posicion elegida por el jugador
	 */
	public abstract Posicion escogeMovimiento(Tablero t);
	//Método abstracto
	
	
	/**
	 * Funcion que dada una posicion en el tablero comprueba si dicha posicion es valida para realizar el movimiento (disparo)
	 * @param posicion Posicion elegida por el jugador
	 * @param t Tablero sobre el que se escoge el movimiento
	 * @return valor booleano que indica si la posicion es valida (true) o no (false)
	 */
	protected boolean posValida(Posicion posicion, Tablero t) {
		
		boolean valida = false;
		if (((posicion.getX() >= 0) && (posicion.getX() < t.getAlto()))
				&& ((posicion.getY() >= 0) && (posicion.getY() < t.getAncho()))) {
			
			Casilla estadoCasilla = t.getEstadoCasilla(posicion.getX(), posicion.getY());
			if (estadoCasilla == Casilla.TAPADO)
				valida = true;
		}
		return valida;
	}
}
