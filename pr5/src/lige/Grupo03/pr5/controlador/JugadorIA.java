//JugadorIA.java

package lige.Grupo03.pr5.controlador;

import java.util.Date;
import java.util.Random;

import lige.Grupo03.pr5.modelo.Posicion;
import lige.Grupo03.pr5.modelo.Tablero;

/**
 * Clase abstracta que modela el comportamiento de un jugador IA que dispara aleatoriamente
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Jugador
 * @see Posicion
 * @see Tablero
 *
 */
public class JugadorIA extends JugadorSobreIA{

	@Override
	/**
	 * Funcion que representa el flujo de accion de un jugador 
	 * al escoger el siguiente movimiento (aleatoriamente)
	 * @param t Tablero sobre el que se juego
	 * @return posicion en la que el jugador elige disparar.
	 */
	public Posicion escogeMovimiento(Tablero t) {

		Posicion posicion = null;
		
		Random random = new Random();
		random.setSeed(new Date().getTime());
		do {
			posicion = rellenaPosAleatoriamente(random, t);	
		} while (!posValida (posicion, t)); // Nos aseguramos que se genera siempre
											// una posicion válida
		return posicion;
	}
}
