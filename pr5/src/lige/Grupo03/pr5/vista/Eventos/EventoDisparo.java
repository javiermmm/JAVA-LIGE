//EventoDisparo.java

package lige.Grupo03.pr5.vista.Eventos;

import lige.Grupo03.pr5.modelo.Constantes.Casilla;
import lige.Grupo03.pr5.modelo.Constantes.TipoEvento;
import lige.Grupo03.pr5.modelo.Posicion;

/**
 * Clase que recoge la informacion necesaria para atender a un evento de disparo
 * Este evento sirve para todos los tipos de disparos normal, de dispersion, y sónico
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Posicion
 * @see Constantes
 */
public class EventoDisparo extends Evento {

	//--------------------------------
	//ATRIBUTOS
	//--------------------------------
	private Casilla [][] _tabLigero;
	private Posicion _disparo;
	private int _anchoTablero;
	private int _altoTablero;
	private int _numDisparos;
	private int _numIntactos;
	private int _numTocados;
	private int _numHundidos;
	private Posicion[] _posicionesAfectadas;
	private boolean _sonico;
	private boolean _dispersion;
	private int _numDispersiones;
	private int _numSonicos;
	//--------------------------------

	/**
	 * Constructor parametrizado
	 * 
	 * @param posicionesBarco Array de posiciones con todas las posiciones que se ven
	 * afectadas durante el disparo
	 * @param disparo Posicion sobre la que se efectua el disparo
	 * @param tablero Array de Casilla que representa el estado de las posiciones
	 * en el tablero de juego
	 * @param ancho Ancho del tablero de juego
	 * @param alto Alto del tablero de juego
	 * @param intactos Numero de barcos intactos
	 * @param numTocados Numero de barcos tocados
	 * @param numHundidos Numero de barcos hundidos
	 * @param sonico Booleano que indica si el disparo es sonico (true) o no (false)
	 * @param dispersion Booleano que indica si el disparo es de dispersion (true) o no (false)
	 * @param disparos Numero de disparos realizados hasta el momento
	 * @param numDispersiones Numero de disparos de dispersion restantes
	 * @param numSonicos Numero de disparos sonicos restantes.
	 */
	public EventoDisparo(Posicion[] posicionesBarco, Posicion disparo, Casilla[][] tablero,
						int ancho, int alto, int intactos, int numTocados, int numHundidos,
						boolean sonico, boolean dispersion, int disparos, 
						int numDispersiones, int numSonicos) {

		_sonico = sonico;
		_dispersion = dispersion;
		_numDispersiones = numDispersiones;
		_numSonicos = numSonicos;
		_disparo = disparo;
		_tabLigero = tablero;
		_anchoTablero = ancho;
		_altoTablero = alto;
		_numDisparos = disparos;
		_tipo = TipoEvento.DISPARO;
		_numIntactos = intactos;
		_numTocados = numTocados;
		_numHundidos = numHundidos;
		_posicionesAfectadas = posicionesBarco;
	}
	
	/**
	 * Accedente para el tablero "ligero"
	 * 
	 * @return Array que representa el tablero con los estados de las Casilla 
	 */
	public Casilla[][] getTablero() {
		return _tabLigero;
	}
	
	/**
	 * Accedente para el numero de disparos realizados
	 * 
	 * @return Entero con el numero de disparos realizados 
	 */
	public int getNumDisparos() {
		return _numDisparos;
	}
	
	/**
	 * Accedente para el numero de barcos intactos
	 * 
	 * @return Entero con el numero de barcos intactos 
	 */
	public int getNumIntactos() {
		return _numIntactos;
	}
	
	/**
	 * Accedente para el numero de barcos tocados
	 * 
	 * @return Entero con el numero de barcos tocados 
	 */
	public int getNumTocados() {
		return _numTocados;
	}
	
	/**
	 * Accedente para el numero de barcos hundidos
	 * 
	 * @return Entero con el numero de barcos hundidos 
	 */
	public int getNumHundidos() {
		return _numHundidos;
	}
	
	/**
	 * Accedente para las posiciones afectadas por el disparo
	 * 
	 * @return Array de posiciones con todas las posiciones que se han visto afectadas
	 * por el disparo realizado
	 */
	public Posicion[] getPosicionesAfectadas() {
		return _posicionesAfectadas;
	}
	
	/**
	 * Accedente para el alto del tablero
	 * 
	 * @return Entero con el alto del tablero 
	 */
	public int getAlto(){
		return _altoTablero;
	}
	
	/**
	 * Accedente para el ancho del tablero
	 * 
	 * @return Entero con el ancho del tablero 
	 */
	public int getAncho() {
		return _anchoTablero;
	}
	
	/**
	 * Accedente para la posicion objetivo donde se disparo 
	 * 
	 * @return Posicion objetivo del disparo
	 */
	public Posicion getDisparo() {
		return _disparo;
	}
	
	/**
	 * Accedente para el booleano que indica si el disparo es de dispersion
	 * 
	 * @return Booleano que indica si el disparo es de dispersion (true) o no (false)
	 */
	public boolean isDispersion() {
		return _dispersion;
	}
	
	/**
	 * Accedente para el booleano que indica si el disparo es sonico
	 * 
	 * @return Booleano que indica si el disparo es sonico (true) o no (false)
	 */
	public boolean isSonico() {
		return _sonico;
	}
	
	/**
	 * Accedente para el numero de disparos de dispersion restantes
	 * 
	 * @return Entero con el numero de disparos de dispersion restantes
	 */
	public int getNumDispersiones(){
		return _numDispersiones;
	}
	
	/**
	 * Accedente para el numero de disparos sonicos restantes
	 * 
	 * @return Entero con el numero de disparos sonicos restantes
	 */
	public int getNumSonicos() {
		return _numSonicos;
	}

	/**
	 * Accedente para conseguir el estado de la casilla del tablero ligero correspondiente 
	 * a la i-ésima posicion afectada por el disparo
	 * 
	 * @param i Entero que designa la posicion que ocupa la Posicion cuyo estado devolvemos
	 * @return Enumerado de tipo Casilla con el estado de esa posicion
	 */
	public Casilla getPosTablero(int i) {
		return _tabLigero[_posicionesAfectadas[i].getX()][_posicionesAfectadas[i].getY()];
	}
}
