//EventoAbrir.java

package lige.grupo03.pr6.editor.vista.Eventos;

import java.util.ArrayList;

import lige.grupo03.pr6.editor.modelo.Barco;
import lige.grupo03.pr6.editor.modelo.Constantes.TipoEvento;

/**
 * Clase que define el evento generado como respuesta a la apertura de un fichero XML
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see EventoSobreAbrirYNuevo
 */
public class EventoAbrir extends EventoSobreAbrirYNuevo {
	
	//-------------------------------
	//ATRIBUTOS
	//-------------------------------
	private int _ancho;
	private int _alto;
	private ArrayList<Barco> _barcos;
	private int _numPortaaviones;
	private int _numAcorazados;
	private int _numFragatas;
	private int _numSubmarinos;
	//-------------------------------
	
	/**
	 * Constructor parametrizado
	 * @param ancho Ancho del tablero
	 * @param alto Alto del tablero
	 * @param barcos ArrayList con los barcos del Tablero.
	 * @param numPortaaviones Numero de portaaviones
	 * @param numAcorazados Numero de acorazados
	 * @param numFragatas Numero de fragatas
	 * @param numSubmarinos Numero de submarinos
	 */
	public EventoAbrir(int ancho, int alto, ArrayList<Barco> barcos, int numPortaaviones, int numAcorazados, int numFragatas, int numSubmarinos) {
		_tipo = TipoEvento.ABRIR;
		_ancho = ancho;
		_alto = alto;
		_barcos = barcos;
		_numPortaaviones = numPortaaviones;
		_numAcorazados = numAcorazados;
		_numFragatas = numFragatas;
		_numSubmarinos = numSubmarinos;
	}
	
	/**
	 * Accedente para el ancho del tablero
	 * @return Ancho del tablero
	 */
	public int getAncho() {
		return _ancho;
	}
	
	/**
	 * Accedente para el alto del tablero
	 * @return Alto del tablero
	 */
	public int getAlto() {
		return _alto;
	}
	
	/**
	 * Accedente para el numero de portaaviones
	 * @return Numero de portaaviones
	 */
	public int getNumPortaaviones() {
		return _numPortaaviones;
	}
	
	/**
	 * Accedente para el numero de acorazados
	 * @return Numero de acorazados
	 */
	public int getNumAcorazados() {
		return _numAcorazados;
	}
	
	/**
	 * Accedente para el numero de fragatas
	 * @return Numero de fragatas
	 */
	public int getNumFragatas() {
		return _numFragatas;
	}
	
	/**
	 * Accedente para el numero de submarinos
	 * @return Numero de submarinos
	 */
	public int getNumSubmarinos() {
		return _numSubmarinos;
	}
	
	/**
	 * Accedente para los barcos
	 * @return barcos del tablero
	 */
	public ArrayList<Barco> getBarcos() {
		return _barcos;
	}
}
