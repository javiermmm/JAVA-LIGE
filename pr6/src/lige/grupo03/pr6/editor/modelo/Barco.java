//Barco.java

package lige.grupo03.pr6.editor.modelo;

import lige.grupo03.pr6.editor.modelo.Constantes.Direccion;
import lige.grupo03.pr6.editor.modelo.Constantes.TipoBarco;

/**
 * Clase que modela un barco de los que se colocanen el escenario
 * 
 * @author Jessica 	Martin & Javier Martin
 * @version 1.0
 * @see Constantes
 */
public class Barco {

	// --------------------------------------
	// ATRIBUTOS
	// --------------------------------------
	private Posicion[] _posicionesOcupadas;
	private TipoBarco _tipo;
	// --------------------------------------

	/**
	 * Constructor de un barco, al que se asigna unas posiciones ocupadas
	 * en funcion de su casilla inicial y su direccion. NO se comprueba que las 
	 * casillas sean correctas
	 * @param tipo Tipo del Barco.
	 * @param posicionInicial primera posicion que ocupa el barco.
	 * @param direccion Direccion que identifica la orientacion de barco
	 */
	public Barco (TipoBarco tipo, Posicion posicionInicial, Direccion direccion) {
		
		_tipo = tipo;
		_posicionesOcupadas = new Posicion [getLongitud(tipo)];
		_posicionesOcupadas[0] = posicionInicial.copia();
		for (int i=1; i<getLongitud(tipo); ++i){
			posicionInicial = posicionInicial.calculaPosicionSiguiente (direccion);
			_posicionesOcupadas[i] = posicionInicial.copia();
		}
	}
	
	/**
	 * Constructor parametrizado para construir un barco
	 * @param tipo
	 */
	public Barco (TipoBarco tipo) {
		_tipo = tipo;
		_posicionesOcupadas = new Posicion [getLongitud(tipo)];
	}

	/**
	 * Funcion que dado un tipo de barco, devuelve la longitud del mismo
	 * @param tipo Tipo del barco (Portaaviones, fragata, acorazado, submarino)  
	 * @return número de casillas que ocupa el barco
	 */
	static public int getLongitud(TipoBarco tipo) {

		if (tipo == TipoBarco.PORTAAVIONES)
			return Constantes.LONG_PORTAAVIONES;
		if (tipo == TipoBarco.ACORAZADO)
			return Constantes.LONG_ACORAZADO;
		if (tipo == TipoBarco.FRAGATA)
			return Constantes.LONG_FRAGATA;
		if (tipo == TipoBarco.SUBMARINO)
			return Constantes.LONG_SUBMARINO;

		return 0;
	}
	
	/**
	 * Accedente para el tipo del barco
	 * @return Tipo del barco
	 */
	public TipoBarco getTipoBarco() {
		return _tipo;
	}
		
	/**
	 * Accedente para las posiciones que ocupa el barco
	 * @param i posicion que ocupa en el array la posicion que queremos conseguir
	 * @return Posicion i-ésima que ocupa el barco
	 */
	public Posicion getPosicion(int i) {

		return _posicionesOcupadas[i];
	}
	
	/**
	 * Accedente para las posiciones que ocupa el barco
	 * @param i posicion que ocupa en el array la posicion que queremos conseguir
	 * @return Posicion i-ésima que ocupa el barco
	 */
	public void setPosicion(Posicion pos, int i) {

		_posicionesOcupadas[i] = pos.copia();
	}

	/**
	 * Funcion que dada una posicion comprueba si el barco esta ocupando dicha posicion
	 * @param pos Posicion que se quiere comprobar
	 * @return Verdadero si el barco esta en la posicion, falso e.o.c.
	 */
	public boolean estaEnLaPosicion(Posicion pos) {

		boolean esta = false;
		int i = 0;
		int maxPosiciones = _posicionesOcupadas.length;
		while ((i < maxPosiciones) && (!esta)) {
			if (_posicionesOcupadas[i].equals(pos))
				esta = true;
			else
				++i;
		}

		return esta;
	}
	
	/**
	 * Accedente para conocer las posiciones ocupadas por un barco
	 * 
	 * @return array con las posiciones ocupadas por un barco
	 */
	public Posicion[] getPosicionesOcupadas() {
		
		Posicion[] posiciones = new Posicion[getLongitud(_tipo)];
		for (int i=0; i<getLongitud(_tipo); ++i)
			posiciones[i] = _posicionesOcupadas[i].copia();
		return posiciones;
	}
}
