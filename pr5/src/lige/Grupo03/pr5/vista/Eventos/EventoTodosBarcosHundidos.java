//EventoTodosBarcosHundidos.java

package lige.Grupo03.pr5.vista.Eventos;

import javax.swing.JOptionPane;

import lige.Grupo03.pr5.modelo.Constantes.Casilla;
import lige.Grupo03.pr5.modelo.Constantes.TipoEvento;

/**
 * Clase que recoge la informacion necesaria para atender a un evento que acaba la partida
 * una vez has hundido todos los barcos
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Constantes
 */
public class EventoTodosBarcosHundidos extends Evento{

	//---------------------------------
	//ATRIBUTOS
	//---------------------------------
	private Casilla [][] _tabLigero;
	private int _anchoTablero;
	private int _altoTablero;
	private float _puntuacion;
	private int _numDisparos;
	//---------------------------------
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param tablero Array de Casilla que representa el tablero sobre el que se juega
	 * @param puntuacion Puntuacion obtenida en la partida
	 * @param numDisparos Numero de disparos realizados en la partida
	 * @param ancho Ancho del tablero
	 * @param alto Alto del tablero
	 */
	public EventoTodosBarcosHundidos (Casilla[][] tablero, float puntuacion, int numDisparos, int ancho, int alto) {
		_tabLigero = tablero;
		_anchoTablero = ancho;
		_altoTablero = alto;
		_tipo = TipoEvento.BARCOSHUNDIDOS;
		_puntuacion = puntuacion;
		_numDisparos = numDisparos;
	}
	
	/**
	 * Accedente para la puntuacion
	 * 
	 * @return Numero real con la puntuacion obtenida en la partida
	 */
	public float getPuntuacion() {
		return _puntuacion;
	}
	
	/**
	 * Accedente para el numero de disparos realizados durante la partida
	 * 
	 * @return Numero de disparos efectuados 
	 */
	public int getNumDisparos() {
		return _numDisparos;
	}
	
	/**
	 * Metodo que devuelve el mensaje que se va a mostrar cuando se acabe la partida
	 * habiendo hundido todos los barcos
	 * 
	 * @return Mensaje final de enhorabuena
	 */
	public String getMensajeFin() {
		_puntuacion = _puntuacion*100;
		String mensajeComún = "¡¡¡ENHORABUENA!!!. Has hundido todos los barcos enemigos.\n"
								+ "Has obtenido una puntuacion de " + _puntuacion + "puntos.\n"
								+ "Te has ganado el rango de... ";
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			String error = "Problema al mostrar el rango de marinero. Por favor, continuá realizando alguna de las siguientes acciones:\n"
							+ "- Terminar Partida\n" 
							+ "- Iniciar una Nueva Partida\n" 
							+ "- Cerrar la aplicación";
			JOptionPane.showMessageDialog(null, error, "Problema Inesperado", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return mensajeComún + rango();
	}

	/**
	 * Metodo que devuelve una cadena con el rango obtenido en base a 
	 * la puntuacion de la partida
	 * 
	 * @return Cadena con el rango conseguido
	 */
	private String rango() {
		if (_puntuacion > 95)
			return "Capitán General";
		if (_puntuacion > 87)
			return "Almirante";
		if (_puntuacion > 80)
			return "Alférez";
		if (_puntuacion > 70)
			return "Sargento";
		if (_puntuacion > 60)
			return "Cabo";
		if (_puntuacion > 50)
			return "LimpiaCubiertas";
		
		return "Polizón";
	}

	/**
	 * Accedente para el ancho del tablero
	 * 
	 * @return Entero que representa el ancho del tablero
	 */
	public int getAncho() {
		return _anchoTablero;
	}
	
	/**
	 * Accedente para el alto del tablero
	 * 
	 * @return Entero que representa el alto del tablero
	 */
	public int getAlto() {
		return _altoTablero;
	}
	
	/**
	 * Accedente para el array de Casilla que representa el tablero de juego
	 * 
	 * @return Array con la representacion ligera del tablero de juego (el array)
	 */
	public Casilla[][] getTablero() {
		return _tabLigero;
	}
}
