//JuegoBarcos.java

package lige.grupo03.pr2.logica;

import lige.grupo03.pr2.salida.UI;
import lige.grupo03.pr2.salida.UIConsola;

/**
 * Clase de alto nivel que "contiene" el juego en sí (partida y salida por consola)
 * @author Jessica Martin & Javier Martin
 * @version 2.0
 * @see Partida
 * @see UIConsola
 */
public class JuegoBarcos {

	//-----------------------------
	//ATRIBUTOS
	//-----------------------------
	private Partida _partida;
	private UI _salida;
	//-----------------------------

	
	/**
	 * Funcion que centraliza toda la accion del juego
	 * @param modoDebug Verdadero si se usó el parametro -d, Falso e.o.c.  
	 */
	public void aJugar(boolean modoDebug) {

		int anchoTablero = 0;
		int altoTablero = 0;
		int opJugador = 0;

		_salida = new UIConsola(modoDebug);
		
		do {
			do {
				anchoTablero = _salida.pideAnchoTablero();
			} while ((anchoTablero < Constantes.MINIMO)
					|| (anchoTablero > Constantes.MAXIMO));

			do {
				altoTablero = _salida.pideAltoTablero();
			} while ((altoTablero < Constantes.MINIMO)
					|| (altoTablero > Constantes.MAXIMO));
			
			do {
				opJugador = _salida.pideTipoJugador();
			} while ((opJugador != 1)&& (opJugador != 2) && (opJugador != 3));

			_partida = new Partida(_salida, anchoTablero, altoTablero, opJugador);
			_partida.jugar(anchoTablero, altoTablero);
		} while (_salida.pedirJugarOtra());
	}

}
