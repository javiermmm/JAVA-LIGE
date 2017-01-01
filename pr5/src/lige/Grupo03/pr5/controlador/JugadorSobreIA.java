//JugadorSobreIA.java

package lige.Grupo03.pr5.controlador;

import java.util.Random;

import lige.Grupo03.pr5.modelo.Constantes.Casilla;
import lige.Grupo03.pr5.modelo.Posicion;
import lige.Grupo03.pr5.modelo.Tablero;

/**
 * Clase abstracta que define el comportamiento general de un jugador IA, e implementa algun método comun a este tipo de jugador.
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Posicion
 * @see Tablero
 */
public abstract class JugadorSobreIA {

	/**
	 * Método abstracto que cada tipo de jugador deberá implementar para escoger un movimiento de la manera adecuada
	 * @param t Tablero sobre el que se escoge el movimiento
	 * @return Posicion elegida por el jugador
	 */
	public abstract Posicion escogeMovimiento(Tablero t);
	//Método abstracto
	
	/**
	 * Funcion que rellena una posicion aleatoriamente
	 * @param posicion Posicion objetivo
	 * @param random Objeto para la generacion de numeros aleatorios
	 * @param t Tablero sobre el que se escoge el movimiento
	 */
	protected Posicion rellenaPosAleatoriamente(Random random, Tablero t) {
		Posicion posicion = new Posicion();
		posicion.setX(random.nextInt(t.getAncho()));	
		posicion.setY(random.nextInt(t.getAlto()));	
		return posicion;
	}
	
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
