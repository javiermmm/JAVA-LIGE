//JugadorHumano.java

package lige.grupo03.pr2.jugador;

import lige.grupo03.pr2.logica.Constantes.Casilla;
import lige.grupo03.pr2.logica.Constantes;
import lige.grupo03.pr2.logica.Posicion;
import lige.grupo03.pr2.logica.Tablero;
import lige.grupo03.pr2.salida.UIConsola;

/**
 * Esta clase simula la accion de un jugador del Hundir la Flota
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see UIConsola
 * @see Posicion
 * @see Constantes
 */
public class JugadorHumano {

	/**
	 * Funcion que representa el flujo de accion de un jugador 
	 * al escoger el siguiente movimiento
	 * @param t Tablero sobre el que se juega
	 * @param consola Consola con la que se le solicita la posicion al jugador
	 * @return posicion en la que el jugador elige disparar.
	 */
	public Posicion escogeMovimiento(Tablero t, UIConsola consola) {

		boolean valida = false;
		int anchoTablero = t.getAncho();
		int altoTablero = t.getAlto();
		Posicion posicion = null;

		do {
			posicion = consola.pidePosicion();
			posicion.setX(posicion.getX()-1);
			posicion.setY(posicion.getY()-1);
			if (((posicion.getX() >= 0) && (posicion.getX() < altoTablero))
					&& ((posicion.getY() >= 0) && (posicion.getY() < anchoTablero))) {

				Casilla estadoCasilla = t.getEstadoCasilla(posicion.getX(), posicion.getY());
				if (estadoCasilla == Casilla.TAPADO)
					valida = true;
			}
			if (!valida)
				consola.mostrarError("ERROR: posicion NO valida");
		} while (!valida); // Nos aseguramos que el usuario siempre introduce
							// una posicion válida

		return posicion;

	}
}
