//JuegoBarcos.java

package lige.grupo03.pr2.logica;

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
	private UIConsola _consola;
	//-----------------------------

	
	/**
	 * Funcion que centraliza toda la accion del juego
	 * @param modoDebug Verdadero si se usó el parametro -d, Falso e.o.c.  
	 */
	public void aJugar(boolean modoDebug) {

		int anchoTablero = 0;
		int altoTablero = 0;

		_consola = new UIConsola();
		
		do {
			do {
				anchoTablero = _consola.pideAnchoTablero();
			} while ((anchoTablero < Constantes.MINIMO)
					|| (anchoTablero > Constantes.MAXIMO));

			do {
				altoTablero = _consola.pideAltoTablero();
			} while ((altoTablero < Constantes.MINIMO)
					|| (altoTablero > Constantes.MAXIMO));

			_partida = new Partida(_consola, anchoTablero, altoTablero);
			_partida.jugar(modoDebug,anchoTablero, altoTablero);
		} while (_consola.pedirJugarOtra());
	}

}
