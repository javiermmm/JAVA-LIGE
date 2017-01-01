/**
 * 
 */
package lige.grupo03.pr2.jugador;

import java.util.Random;

import lige.grupo03.pr2.logica.Posicion;
import lige.grupo03.pr2.logica.Tablero;

/**
 * Clase abstracta que define el comportamiento general de un jugador IA, e implementa algun método comun a este tipo de jugador.
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Posicion
 * @see Tablero
 */
public abstract class JugadorSobreIA extends Jugador {

	/**
	 * Funcion que rellena una posicion aleatoriamente
	 * @param posicion Posicion objetivo
	 * @param random Objeto para la generacion de numeros aleatorios
	 * @param t Tablero sobre el que se escoge el movimiento
	 */
	protected void rellenaPosAleatoriamente(Posicion posicion, Random random, Tablero t) {
		posicion.setX(random.nextInt(t.getAncho()));	
		posicion.setY(random.nextInt(t.getAlto()));	
	}
}
