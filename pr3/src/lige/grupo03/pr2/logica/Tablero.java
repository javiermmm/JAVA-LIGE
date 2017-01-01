//Tablero.java

package lige.grupo03.pr2.logica;

import java.util.ArrayList;
import java.util.Iterator;
import lige.grupo03.pr2.logica.Constantes.*;

/**
 * Clase que modela lo que seria un tablero del juego de los barquitos
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Constantes
 * @see Barco
 */
public class Tablero {

	// ------------------------------
	// ATRIBUTOS
	// ------------------------------
	private Casilla[][] _tablero;
	private ArrayList<Barco> _barcos;
	private int _ancho;
	private int _alto;
	private int _numTocados;
	private int _numHundidos;

	// ------------------------------

	/**
	 * Constructor del tablero, parametrizado con su alto y su ancho
	 * @param ancho Ancho que tendra el tablero
	 * @param alto Alto que tendra el tablero
	 */
	public Tablero(int ancho, int alto) {

		_numTocados = 0;
		_numHundidos = 0;
		_alto = alto;
		_ancho = ancho;
		_barcos = new ArrayList<Barco>();
		_tablero = new Casilla[_alto][_ancho];
		for (int i = 0; i < _alto; ++i)
			for (int j = 0; j < _ancho; ++j) {
				_tablero[i][j] = Casilla.TAPADO;
			}
	}

	/**
	 * Funcion que comprueba si hay espacio suficiente en el tablero como para
	 * que, dadas una posicion inicial, la direccioon y la longitud de un barco,
	 * se pueda colocar en el tablero o no
	 * @param tipo tipo de barco que se pretende colocar
	 * @param posIni Posicion inicial del barco a colocar
	 * @param dir Direccion a seguir por el barco a colocar
	 * @param longitud Longitud del barco que se quiere colocar
	 * @return valor booleano que indica si se puede colocar el barco (true) o no (false).
	 */
	public boolean seHaPodidoColocarBarco(TipoBarco tipo, Posicion posIni,
			Direccion dir, int longitud) {

		boolean sePuede = true;
		int i = 0;
		Posicion pos = posIni.copia();
		while (sePuede && (i < longitud)) {
			if (casillaValida(pos) && entornoDespejado(pos)) {
				pos = pos.calculaPosicionSiguiente(dir);
				i++;
			} else
				sePuede = false;
		}

		if (sePuede) {
			Barco barco = new Barco(tipo, posIni, dir);
			_barcos.add(barco);
		}

		return sePuede;
	}

	/**
	 * Funcion auxiliar que comprueba si una posicion es un objetivo valido para
	 * colocar un barco. Una posicion sera objetivo valido si esta dentro del
	 * tablero Y no ha sido disparada
	 * @param pos Posicion en la que se encuentra la casilla cuya validez queremos comprobar
	 * @return valor booleano que indica si efectivamente la casilla es valida (true) o no (false)
	 */
	private boolean casillaValida(Posicion pos) {

		return (pos.getX() >= 0) && (pos.getX() < _alto) && (pos.getY() >= 0)
				&& (pos.getY() < _ancho) && (casillaVacia(pos));
		// La casilla esta dentro del tablero y este vacia
	}

	/**
	 * Funcion que comprueba si una posicion esta vacia buscandola entre las
	 * posiciones de los barcos
	 * @param pos Posicion que queremos comprobar
	 * @return valor booleano que indica si la casilla esta vacia (true) o no (false).
	 */
	private boolean casillaVacia(Posicion pos) {

		Iterator<Barco> iterador = _barcos.iterator();
		boolean encontrada = false;
		while (iterador.hasNext()) {
			Barco b = iterador.next();
			int i = 0;
			while (!encontrada && i < Barco.getLongitud(b.getTipoBarco())) {
				if (b.getPosicion(i).equals(pos))
					encontrada = true;
				else
					++i;
			}
		}
		return !encontrada;
	}

	/**
	 * Funcion que comprueba si una posicion tiene las casillas a su alrededor despejadas
	 * @param pos Posicion que queremos comprobar
	 * @return valor booleano que indica si el entorno esta despejado (true) o no (false).
	 */
	public boolean entornoDespejado(Posicion pos) {

		Posicion posAux = new Posicion();
		boolean despejado = true;
		int i = -1;
		while (i < 2 && despejado) {
			int j = -1;
			while (j < 2 && despejado) {
				posAux.setX(pos.getX() + i);
				posAux.setY(pos.getY() + j);
				if (fuera(posAux)&& ((posAux.getX() == -1) || (posAux.getX() == 1) || (posAux.getY() == -1) || (posAux.getY() == 1)))
					despejado = true;
				if (!fuera(posAux)) {
					Barco b = hayBarco(posAux);
					if (b == null)
						despejado = true;
					else
						despejado = false;
				}
				j++;
			}
			i++;
		}
		return despejado;
	}

	/**
	 * Funcion que comprueba si una posicion esta fuera del tablero
	 * posiciones de los barcos
	 * @param pos Posicion que queremos comprobar
	 * @return valor booleano que indica si la casilla esta fuera del tablero (true) o no (false).
	 */
	private boolean fuera(Posicion pos) {
		return (pos.getX() < 0) || (pos.getX() >= _alto) || (pos.getY() < 0)
				|| (pos.getY() >= _ancho);
	}

	/**
	 * Accedente para los barcos que hay en el tablero
	 * @return Los barcos que hay en el tablero en un ArrayList
	 */
	public ArrayList<Barco> getBarcos() {

		return _barcos;
	}

	/**
	 * Accedente para el alto del tablero
	 * @return alto del tablero
	 */
	public int getAlto() {

		return _alto;
	}

	/**
	 * Accedente para el ancho del tablero
	 * @return ancho del tablero
	 */
	public int getAncho() {

		return _ancho;
	}

	/**
	 * Accedente para el tablero de juego. Para no devolver el tablero completo
	 * solicitamos una coordenada concreta
	 * @param x entero que representa la coordenada x
	 * @param y entero que representa la coordenada y
	 * @return estado de la casilla en esas coordenadas (Tapado, agua, tocado, hundido)
	 */
	public Casilla getEstadoCasilla(int x, int y) {

		return _tablero[x][y];
	}

	/**
	 * Funcion que actualiza el estado de la partida, cambiando aquellos
	 * componentes que se vean afectados (tablero y barco principalmente)
	 * @param objetivo Posicion a la que se quiere disparar
	 */
	public void dispara(Posicion objetivo) {
		if (_tablero[objetivo.getX()][objetivo.getY()] == Casilla.TAPADO) {
			Barco b = hayBarco(objetivo);
			if (b == null)
				_tablero[objetivo.getX()][objetivo.getY()] = Casilla.AGUA;
			else {
				int i = 0;
				boolean encontrada = false;
				while ((i < Barco.getLongitud(b.getTipoBarco())) && !encontrada) {
					Posicion pos = b.getPosicion(i);
					if (pos.equals(objetivo)) {
						if (b.tocado()) {// Si el barco ya estaba tocado antes del disparo...
							b.setPosicion(i, pos, true);
							_tablero[objetivo.getX()][objetivo.getY()] = Casilla.TOCADO;
							siHayQueHundirHunde(b);
						}
						if (b.getEstado() == EstadoBarco.INTACTO) {// Si el barco tiene estado intacto ANTES del disparo...
							b.setPosicion(i, pos, true);
							if (b.tocado()) {
								_tablero[objetivo.getX()][objetivo.getY()] = Casilla.TOCADO;
								_numTocados++;
							}
							siHayQueHundirHunde(b);
						}
						encontrada = true;
					} else
						i++;
				}
			}
		}
	}

	/**
	 * Funcion que comprueba si un barco esta hundido y si lo esta actualiza el
	 * tablero y el numero de barcos hundidos
	 * @param b Barco que se quiere comprobar/hundir
	 */
	private void siHayQueHundirHunde(Barco b) {
		if (b.hundido()) {
			hunde(b);
			_numHundidos++;
			if (!(b.getTipoBarco() == TipoBarco.SUBMARINO))
				_numTocados--;
		}
	}

	/**
	 * Funcion que comprueba si dada una posicion, hay algun barco ocupandola
	 * @param pos Posicion que se quiere inspeccionar
	 * @return Barco que esta ocupando la posicion pos, en caso de ocuparla. Si la posicion esta desocupada se devuelve null
	 */
	public Barco hayBarco(Posicion pos) {

		Iterator<Barco> iterador = _barcos.iterator();
		while (iterador.hasNext()) {
			Barco b = iterador.next();
			if (b.estaEnLaPosicion(pos))
				return b;
		}
		return null;
	}

	/**
	 * Funcion que se encarga de hundir un barco, esto es, actualizar el tablero
	 * para que las casillas ocupadas por el barco pasen al estado HUNDIDO
	 * @param b Barco que se quiere hundir
	 */
	private void hunde(Barco b) {

		for (int i = 0; i < Barco.getLongitud(b.getTipoBarco()); ++i) {
			Posicion pos = b.getPosicion(i);
			_tablero[pos.getX()][pos.getY()] = Casilla.HUNDIDO;
		}
	}

	/**
	 * Funcion que devuelve el numero de barcos tocados en el tablero
	 * @return numero de barcos tocados que hay en el tablero
	 */
	public int getNumTocados() {

		return _numTocados;
	}

	/**
	 * Funcion que devuelve el numero de barcos hundidos en el tablero
	 * @return numero de barcos hundidos que hay en el tablero
	 */
	public int getNumHundidos() {
		return _numHundidos;
	}

	/**
	 * Funcion que cuenta el número de casillas ocupadas por barcos. Lo normal
	 * es que sea un número fijo de casillas (1*4 +2*3 + 3*2 + 4*1 = 20), pero
	 * esta funcion es adicional, pos si quisieramos cambiar las reglas en
	 * cuanto a los barcos.
	 * @return número de casillas ocupadas por barcos
	 */
	public int casillasOcupadasPorBarcos() {

		int contadorCasillas = 0;
		Iterator<Barco> iterador = _barcos.iterator();
		while (iterador.hasNext()) {
			Barco b = iterador.next();
			contadorCasillas += Barco.getLongitud(b.getTipoBarco());
		}
		return contadorCasillas;
	}

	/**
	 * Funcion que comprueba si todos los barcos del tablero ya han sido hundidos
	 * @return valor booleano que indica si los barcos estan hundidos (true) o no (false).
	 */
	public boolean todosBarcosHundidos() {

		Iterator<Barco> iterador = _barcos.iterator();
		while (iterador.hasNext()) {
			Barco b = iterador.next();
			if (!b.hundido())
				return false;
		}
		return true;
	}
}
