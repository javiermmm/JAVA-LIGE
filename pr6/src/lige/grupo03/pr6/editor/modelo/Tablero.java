//Tablero.java

package lige.grupo03.pr6.editor.modelo;

import java.util.ArrayList;
import java.util.Iterator;

import lige.grupo03.pr6.editor.modelo.Constantes.Direccion;
import lige.grupo03.pr6.editor.modelo.Constantes.TipoBarco;

/**
 * Clase que modela lo que seria un tablero para colocar los barcos del escenario
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
	private ArrayList<Barco> _barcos;
	private int _ancho;
	private int _alto;
	// ------------------------------

	/**
	 * Constructor del tablero, parametrizado con su alto y su ancho
	 * @param ancho Ancho que tendra el tablero
	 * @param alto Alto que tendra el tablero
	 */
	public Tablero(int ancho, int alto) {

		_alto = alto;
		_ancho = ancho;
		_barcos = new ArrayList<Barco>();
	}
	
	/**
	 * Constructor del tablero, parametrizado con su alto y su ancho
	 * @param ancho Ancho que tendra el tablero
	 * @param alto Alto que tendra el tablero
	 * @para barcos ArrayList con los barcos que hay en el tablero
	 */
	public Tablero(int ancho, int alto, ArrayList<Barco> barcos ) {

		_alto = alto;
		_ancho = ancho;
		_barcos = barcos;
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
	public boolean seHaPodidoColocarBarco(TipoBarco tipo, Posicion posIni, Direccion dir, int longitud) {

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
	public boolean fuera(Posicion pos) {
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
	 * Metodo que elimina un barco del Tablero, al eliminarlo del arrayList de Barcos
	 * 
	 * @param b Barco que queremos quitar del ArrayList
	 */
	public void quitaBarco(Barco b) {
		_barcos.remove(b);
	}

	/**
	 * Accedente para el ancho del tablero
	 * @return Entero con el ancho del tablero
	 */
	public int getAncho() {
		return _ancho;
	}

	/**
	 * Accedente para el alto del tablero
	 * @return Entero con el alto del tablero
	 */
	public int getAlto() {
		return _alto;
	}
}

