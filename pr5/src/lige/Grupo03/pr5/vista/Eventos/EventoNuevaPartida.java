//EventoNuevaPartida.java

package lige.Grupo03.pr5.vista.Eventos;

import lige.Grupo03.pr5.modelo.Constantes.TipoEvento;

/**
 * Clase que recoge la informacion necesaria para atender a un evento de nueva partida
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class EventoNuevaPartida extends Evento {

	//--------------------------------
	//ATRIBUTOS
	//--------------------------------
	private int _ancho;
	private int _alto;
	private boolean _modoDebug;
	private char [][] _tableroInicial;
	//--------------------------------
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param ancho Ancho del tablero de juego
	 * @param alto Alto del tablero de juego
	 * @param tableroInicial Array que representa el tablero de debug
	 * @param modoDebug Booleani que indica si se eligio el modo debug (true) o no (false)
	 */
	public EventoNuevaPartida(int ancho, int alto, char[][] tableroInicial, boolean modoDebug) {
		_tipo = TipoEvento.NUEVAPARTIDA;
		_ancho = ancho;
		_alto = alto;
		_modoDebug = modoDebug;
		_tableroInicial = tableroInicial;
	}
	
	/**
	 * Accedente para el ancho del tablero
	 * 
	 * @return Entero con el ancho del tablero
	 */
	public int getAncho() {
		return _ancho;
	}
	
	/**
	 * Accedente para el alto del tablero
	 * 
	 * @return Entero con el alto del tablero
	 */
	public int getAlto() {
		return _alto;
	}
	
	/**
	 * Accedente para el tablero de debug
	 * 
	 * @return Array que representa el tablero de debug.
	 */
	public char[][] getTableroInicial() {
		return _tableroInicial;
	}

	/**
	 * Accedente para el tipo de juego: debug (true) o normal (false).
	 * 
	 * @return Booleano que identifica si se eligio el modo debug (true) o no (false)
	 */
	public boolean getTipoJuego() {
		return _modoDebug;
	}
}
