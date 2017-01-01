//JugadorHumano.java

package lige.grupo03.pr2.jugador;

import lige.grupo03.pr2.logica.Constantes;
import lige.grupo03.pr2.logica.Posicion;
import lige.grupo03.pr2.logica.Tablero;
import lige.grupo03.pr2.salida.UI;
import lige.grupo03.pr2.salida.UIConsola;

/**
 * Esta clase simula la accion de un jugador humano del Hundir la Flota
 * @author Jessica Martin & Javier Martin
 * @version 2.0
 * @see Jugador
 * @see UIConsola
 * @see Posicion
 * @see Constantes
 */
public class JugadorHumano extends Jugador{
	
	//--------------------------
	//ATRIBUTO
	//--------------------------
	private UI _consola;
	//--------------------------

	/**
	 * Constructor parametrizado con la salida necesaria
	 * @param salida tipo de salida
	 */
	public JugadorHumano(UI salida) {
		
		_consola= salida;
	}

	
	@Override
	/**
	 * Funcion que representa el flujo de accion de un jugador 
	 * al escoger el siguiente movimiento
	 * @param t Tablero sobre el que se juego
	 * @return posicion en la que el jugador elige disparar.
	 */
	public Posicion escogeMovimiento(Tablero t) {

		boolean valida = false;
		Posicion posicion = null;

		do {
			posicion = _consola.pidePosicion();
			posicion.setX(posicion.getX()-1);
			posicion.setY(posicion.getY()-1);
			valida = posValida (posicion, t); 
			if (!valida)
				_consola.mostrarError("ERROR: posicion NO valida");
		} while (!valida); // Nos aseguramos que el usuario siempre introduce
							// una posicion válida

		return posicion;

	}
}
