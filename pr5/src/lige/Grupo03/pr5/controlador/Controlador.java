//Controlador.java

package lige.Grupo03.pr5.controlador;

import lige.Grupo03.pr5.modelo.Partida;
import lige.Grupo03.pr5.modelo.Posicion;
import lige.Grupo03.pr5.vista.VentanaPrincipal;

/**
 * Clase que modela el comportamiento del Controlador en el patron MVC.
 * Adem�s se incluye el tratamiento de la IA, si se selecciona.
 * 
 * @author Jessica 	Martin & Javier Martin
 * @version 1.0
 * @see Partida
 * @see JugadorSobreIA
 * @see Controlador
 * @see VentanaPrincipal
 */
public class Controlador {

	//---------------------------------
	//ATRIBUTOS
	//---------------------------------
	private Partida _modelo;
	private JugadorSobreIA _jugador;
	private int _intervaloIA;
	//---------------------------------
	
	/**
	 * Constructor del controlador de la aplicaci�n
	 * @param modelo Partida que act�a como modelo de la aplicacion [Patr�n MVC]
	 */
	public Controlador (Partida modelo) {
		_modelo = modelo;
		_jugador = null;
	}

	/**
	 * M�todo que delega la creacion de una nueva partida
	 * @param ancho Ancho del tablero de juego
	 * @param alto Alto del tablero de juego
	 * @param modoDebug Booleano que indica si se eligi� el modo Debug (true) o no (false)
	 * @param opJugador Entero que identifica el tipo de jugador seleccionado para jugar
	 */
	public void nuevaPartida(int ancho, int alto, boolean modoDebug, int opJugador) {
		_modelo.prepararPartida(ancho, alto, modoDebug, opJugador);
		_jugador = null;
		if (opJugador == 2)
			_jugador = new JugadorIA();
		if (opJugador ==3)
			_jugador = new JugadorIAMejor();
	}

	/**
	 * M�todo que delega la terminacion (a eleccion del jugador) de una partida.
	 */
	public void terminaPartida() {
		_jugador = null;
		_modelo.terminaPartida();
	}

	/**
	 * M�todo que delega la realizacion de un disparo
	 * @param disparo Posicion en la que se quiere realizar el disparo
	 */
	public void dispara(Posicion disparo) {
		_modelo.dispara(disparo);
	}

	/**
	 * M�todo que delega la activacion de un disparo especial de tipo dispersion
	 */
	public void activaDispersion() {
		_modelo.activaDispersion();
		
	}

	/**
	 * M�todo que delega la activacion de un disparo especial de tipo s�nico
	 */
	public void activaSonico() {
		_modelo.activaSonico();
	}

	/**
	 * M�todo que establece el intervalo de actuacion de la IA elegida
	 * @param intervalo Entero que identificar� los milisegundos entre disparos de la IA
	 */
	public void setIntervaloIA(int intervalo) {
		_intervaloIA=intervalo;
	}

	/**
	 * M�todo que obliga a la IA a disparar hasta que hunda todos los barcos
	 */
	public void bucleIA() {
		while(true){
			try{Thread.sleep(_intervaloIA);}
			catch(InterruptedException  e){
				e.printStackTrace();
			}
			if (_jugador!=null){
				if (!_modelo.finJuego()){
					dispara(_jugador.escogeMovimiento(_modelo.getTablero()));
				}
			}
		}
	}
	
}
