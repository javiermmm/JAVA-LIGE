//JugadorIAMejor.java

package lige.Grupo03.pr5.controlador;

import java.util.Date;
import java.util.Random;

import lige.Grupo03.pr5.modelo.Constantes.Casilla;
import lige.Grupo03.pr5.modelo.Constantes.Direccion;
import lige.Grupo03.pr5.modelo.Posicion;
import lige.Grupo03.pr5.modelo.Tablero;

/**
 * Clase abstracta que modela el comportamiento de un jugador IA que dispara
 * aleatoriamente si no ha descubierto ningun barco y con intencion de hundir en caso contrario
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Jugador
 * @see Posicion
 * @see Tablero
 *
 */
public class JugadorIAMejor extends JugadorSobreIA {

	//-------------------------------------------------
	//ATRIBUTOS
	//-------------------------------------------------
	private int _numDisparos;
	private int _tocadosDeBarco;
	private Posicion _ultimoDisparo;
	private Posicion _ultimoTocadoDeBarco;
	private Posicion _primerTocadoDeBarco;
	private Direccion _ultimaDireccionTomadaAlAcertar;
	//-------------------------------------------------
	
	
	/**
	 * Constructor por defecto que inicializa los atributos
	 */
	public JugadorIAMejor () {
		_numDisparos = 0;
		_tocadosDeBarco =0;
		_ultimoDisparo = null;
		_ultimoTocadoDeBarco = null;
		_primerTocadoDeBarco = null;
		_ultimaDireccionTomadaAlAcertar = null;
	}
	
	@Override
	/**
	 * Funcion que representa el flujo de accion de la IA mejorada 
	 * al escoger el siguiente movimiento. La IA disparará aleatoriamente si no tiene mas informacion o 
	 * tratando de hundir barco basándose en el disparo anterior
	 * @param t Tablero sobre el que se juego
	 * @return posicion en la que el jugador elige disparar.
	 */
	public Posicion escogeMovimiento(Tablero t) {
		
		//Generamos una posicion Aleatoria
		Random random = new Random();
		random.setSeed(new Date().getTime());
		
		if ((_numDisparos==0) || ((_ultimoTocadoDeBarco == null) && (ultimoAgua(t)))) //Si estamos realizando el primer disparo o he hecho agua (sin haber tocado un barco)
			return disparaAleatoriamente(t, random);
		
		if ((_ultimoTocadoDeBarco != null) && ( ultimoAgua(t))) //Si he hecho agua habiendo tocado un barco anteriormente
			return disparaConIntencion (t, random);
		
		if (ultimoTocado(t)) { //En el ultimo disparo toque un barco
			_ultimoTocadoDeBarco = _ultimoDisparo;
			return disparaConIntencion (t, random);
		}
		
		if (ultimoHundido(t)) { //En el ultimo disparo hundí un barco
			_ultimoTocadoDeBarco = null;
			_ultimaDireccionTomadaAlAcertar = null;
			_tocadosDeBarco =0;
			return disparaAleatoriamente(t, random);
		}
		return null;
	}

	/**
	 * Funcion para actualizar los atributos al hacer un disparo nuevo
	 * @param pos posicion en la que se dispara
	 */
	private void actualizaUltimoDisparo (Posicion pos) {
		
		_numDisparos++;
		_ultimoDisparo = pos;
	}
	
	/**
	 * Funcion que dispara con intencion despues de haber encontrado
	 * un barco. Siempre devuelve un disparo valido
	 * @param t Tablero sobre el que se juego
	 * @param random Objeto para la generacion de numeros aleatorios
	 * @return posicion en la que se elige disparar.
	 */
	private Posicion disparaConIntencion (Tablero t, Random random) {
		
		Posicion pos = null;
		do {
			pos = generaDisparoConIntencion(t, random);
		}while (!posValida (pos, t));
		actualizaUltimoDisparo (pos);

		return pos;
	}
	
	/**
	 * Funcion que dispara con intencion despues de haber encontrado
	 * un barco. Siempre devuelve un disparo valido
	 * @param t Tablero sobre el que se juego
	 * @param random Objeto para la generacion de numeros aleatorios
	 * @return posicion en la que se elige disparar.
	 */
	private Posicion disparaAleatoriamente (Tablero t, Random random) {
		
		Posicion pos = null;
		do {
			pos = rellenaPosAleatoriamente(random, t);
		}while (!posValida(pos, t) || tieneAdyacentesHundidas (pos, t));
		actualizaUltimoDisparo (pos);
		return pos;
	}
	
	/**
	 * Funcion que comprueba si el resultado del ultimo disparo realizado fue agua
	 * @param t Tablero sobre el que se juego
	 * @return valor booleano que indica si el disparo anterior fue agua (true) o no (false).
	 */
	private boolean ultimoAgua(Tablero t) {
		if (_ultimoDisparo == null)
			return false;
		else
			return (t.getEstadoCasilla(_ultimoDisparo.getX(), _ultimoDisparo.getY()) == Casilla.AGUA);
	}

	/**
	 * Funcion que comprueba si el resultado del ultimo disparo realizado fue tocado
	 * @param t Tablero sobre el que se juego
	 * @return valor booleano que indica si el disparo anterior fue tocado (true) o no (false).
	 */
	private boolean ultimoTocado(Tablero t) {
		
		if (_ultimoDisparo == null)
			return false;
		else
			return (t.getEstadoCasilla(_ultimoDisparo.getX(), _ultimoDisparo.getY()) == Casilla.TOCADO);
	}
	
	/**
	 * Funcion que comprueba si el resultado del ultimo disparo realizado fue hundido
	 * @param t Tablero sobre el que se juego
	 * @return valor booleano que indica si el disparo anterior fue hundido (true) o no (false).
	 */
	private boolean ultimoHundido(Tablero t) {
		
		if (_ultimoDisparo == null)
			return false;
		else
			return (t.getEstadoCasilla(_ultimoDisparo.getX(), _ultimoDisparo.getY()) == Casilla.HUNDIDO);
	}

	/**
	 * Funcion que comprueba si una posicion tiene casillas adyacenes con estdo hundido
	 * @param pos Posicion del tablero que se comprueba
	 * @param t Tablero sobre el que se juego
	 * @return valor booleano que indica si la posicion tiene casillas hundidas (true) o no (false).
	 */
	private boolean tieneAdyacentesHundidas(Posicion pos, Tablero t) {
		
		Posicion posAux = new Posicion();
		boolean tiene = false;
		int i = -1;
		while (i < 2 && !tiene) {
			int j = -1;
			while (j < 2 && !tiene) {
				posAux.setX(pos.getX() + i);
				posAux.setY(pos.getY() + j);
				if (dentroTablero (posAux, t))
					if (t.getEstadoCasilla(posAux.getX(), posAux.getY()) == Casilla.HUNDIDO)
						tiene = true;
				j++;
			}
			i++;
		}
		return tiene;
	}
	
	/**
	 * Funcion que comprueba si una posicion esta dentro del tablero
	 * @param pos Posicion que se comprueba
	 * @param t Tablero sobre el que se juego
	 * @return valor booleano que indica si la posicion esta dentro del tablero (true) o no (false).
	 */
	private boolean dentroTablero(Posicion pos, Tablero t) {
		
		if (((pos.getX() >= 0) && (pos.getX() < t.getAlto())) && ((pos.getY() >= 0) && (pos.getY() < t.getAncho())))
			return true;
		else
			return false;
	}
			
	/**
	 * Funcion que genera un disparo con intencion. Una vez tocado un barco, hubiera sido mas facil 
	 * recorrer sus posiciones ocupadas e ir disparandolas una a una, pero para simular un comportamiento 
	 * mas humano hacemos que la IA no sepa esa informacion y tenga que probar disparando en posiciones adyacentes.
	 * @param t Tablero sobre el que se juego
	 * @param random Objeto para la generacion de numeros aleatorios
	 * @return valor booleano que indica si el disparo anterior fue agua (true) o no (false).
	 */
	private Posicion generaDisparoConIntencion(Tablero t, Random random) {
		
		if (ultimoTocado(t)) {
			_tocadosDeBarco++;
			if (_tocadosDeBarco == 1){
				_primerTocadoDeBarco = _ultimoDisparo;
				return generaAdyacenteEnCruz(_ultimoDisparo, random, t);
			}
			if (_tocadosDeBarco >1){
				calculaDireccionDeDisparo();
				Posicion posAux =_ultimoTocadoDeBarco.calculaPosicionSiguiente(_ultimaDireccionTomadaAlAcertar);
				if (posValida (posAux, t))
					return posAux;
				else {
					invierteDireccion();
					return _primerTocadoDeBarco.calculaPosicionSiguiente(_ultimaDireccionTomadaAlAcertar);
				}			
			}
		}	
		if (ultimoAgua(t) && _ultimaDireccionTomadaAlAcertar == null)
			return generaAdyacenteEnCruz (_ultimoTocadoDeBarco, random, t);
		if (ultimoAgua(t) && _ultimaDireccionTomadaAlAcertar != null) {
			invierteDireccion();
			return _primerTocadoDeBarco.calculaPosicionSiguiente(_ultimaDireccionTomadaAlAcertar);
		}	
		return null;
	}
	
	/**
	 * Funcion que convierte una direccion a la cardinalmente opuesta.
	 */
	private void invierteDireccion() {
		
		if (_ultimaDireccionTomadaAlAcertar == Direccion.ESTE)
			_ultimaDireccionTomadaAlAcertar = Direccion.OESTE;
		else
			if (_ultimaDireccionTomadaAlAcertar == Direccion.OESTE)
				_ultimaDireccionTomadaAlAcertar = Direccion.ESTE;
			else
				if (_ultimaDireccionTomadaAlAcertar == Direccion.NORTE)
					_ultimaDireccionTomadaAlAcertar = Direccion.SUR;
				else
					if (_ultimaDireccionTomadaAlAcertar == Direccion.SUR)
						_ultimaDireccionTomadaAlAcertar = Direccion.NORTE;
	}

	/**
	 * Funcion que a partir de una posicion, genera otra que sea adyacente a ella 
	 * en direccion norte, sur, este u oeste.
	 * @param pos Posicion original de referencia
	 * @param random Objeto para la generacion de numeros aleatorios
	 * @param t Tablero sobre el que se juego
	 * @return posicion adyacente generada
	 */
	private Posicion generaAdyacenteEnCruz(Posicion pos, Random random, Tablero t) {
		
		Posicion posAux = pos.copia();
		do {		
			int dir = random.nextInt(4);	
			if (dir== 0){//NORTE
				posAux.setX(pos.getX()-1);
				posAux.setY(pos.getY());
			}
			if (dir== 1){//ESTE
				posAux.setY(pos.getY()+1);
				posAux.setX(pos.getX());
			}
			if (dir== 2){//SUR
				posAux.setX(pos.getX()+1);
				posAux.setY(pos.getY());
			}
			if (dir== 3){//OESTE
				posAux.setY(pos.getY()-1);
				posAux.setX(pos.getX());
			}
		}while (!posValida(posAux, t) || tieneAdyacentesHundidas (posAux, t));
		return posAux;
	}

	/**
	 * Funcion que a partir de las posiciones del primer y ultimo tocado de un barco
	 * identifica la direccion seguida para seguir disparando por ahí
	 */
	private void calculaDireccionDeDisparo() {
		
		if (_primerTocadoDeBarco.getX() == _ultimoTocadoDeBarco.getX()){
			if (_primerTocadoDeBarco.getY() < _ultimoTocadoDeBarco.getY())
				_ultimaDireccionTomadaAlAcertar = Direccion.ESTE;
			if (_primerTocadoDeBarco.getY() > _ultimoTocadoDeBarco.getY())
				_ultimaDireccionTomadaAlAcertar = Direccion.OESTE;
		}
		if (_primerTocadoDeBarco.getY() == _ultimoTocadoDeBarco.getY()){
			if (_primerTocadoDeBarco.getX() < _ultimoTocadoDeBarco.getX())
				_ultimaDireccionTomadaAlAcertar = Direccion.SUR;
			if (_primerTocadoDeBarco.getX() > _ultimoTocadoDeBarco.getX())
				_ultimaDireccionTomadaAlAcertar = Direccion.NORTE;
		}
	}

}
