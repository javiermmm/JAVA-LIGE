//EventoPonerQuitar.java

package lige.grupo03.pr6.editor.vista.Eventos;

import lige.grupo03.pr6.editor.modelo.Posicion;

/**
 * Clase intermedia que define el codigo comun para los eventos de poner y quitar un barco de/en el escenario
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Evento
 */
public class EventoPonerQuitar extends Evento {
	
	//------------------------------------
	//ATRIBUTOS
	//------------------------------------
	private Posicion[] _posicionesBarco;
	private int _numPortaaviones;
	private int _numAcorazados;
	private int _numFragatas;
	private int _numSubmarinos;
	//------------------------------------
	
	/**
	 * Constructor parametrizado
	 * @param posiciones Array de posiciones que van a cambiar
	 * @param numPortaaviones Numero de portaaviones que quedan por colocar
	 * @param numAcorazados Numero de acorazados que quedan por colocar
	 * @param numFragatas Numero de fragatas que quedan por colocar
	 * @param numSubmarinos Numero de submarinos que quedan por colocar
	 */
	public EventoPonerQuitar(Posicion[] posiciones, int numPortaaviones, int numAcorazados, int numFragatas, int numSubmarinos) {
		_posicionesBarco = posiciones;
		_numPortaaviones = numPortaaviones;
		_numAcorazados = numAcorazados;
		_numFragatas = numFragatas;
		_numSubmarinos = numSubmarinos;
	}
	
	/**
	 * Accedente para las posiciones que cambian
	 * @return Array de posiciones que contiene aquellas que van a verse afectadas en el evento
	 */
	public Posicion[] getPosicionesBarco() {
		return _posicionesBarco;
	}
	
	/**
	 * Accedente para el numero de portaaviones
	 * @return Numero de portaaviones disponibles
	 */
	public int getPortaaviones() {
		return _numPortaaviones;
	}
	
	/**
	 * Accedente para el numero de acorazados
	 * @return Numero de acorazados disponibles
	 */
	public int getAcorazados() {
		return _numAcorazados;
	}
	
	/**
	 * Accedente para el numero de fragatas
	 * @return Numero de fragatas disponibles
	 */
	public int getFragatas() {
		return _numFragatas;
	}
	
	/**
	 * Accedente para el numero de submarinos
	 * @return Numero de submarinos disponibles
	 */
	public int getSubmarinos() {
		return _numSubmarinos;
	}
}
