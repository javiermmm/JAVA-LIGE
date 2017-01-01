//Barco.java

package lige.Grupo03.pr5.modelo;

import lige.Grupo03.pr5.modelo.Constantes.Direccion;
import lige.Grupo03.pr5.modelo.Constantes.EstadoBarco;
import lige.Grupo03.pr5.modelo.Constantes.TipoBarco;

/**
 * Clase que modela los barcos del Hundir la Flota
 * @author Javier Martin & Jessica Martin
 * @version 2.5
 * @see Posicion
 * @see Constantes
 */
public class Barco {

	// --------------------------------------
	// ATRIBUTOS
	// --------------------------------------
	private PosEstado[] _posicionesOcupadas;
	private TipoBarco _tipo;
	private EstadoBarco _estado;
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
		
		_estado = EstadoBarco.INTACTO;
		_tipo = tipo;
		_posicionesOcupadas = new PosEstado [getLongitud(tipo)];
		_posicionesOcupadas[0] = new PosEstado (posicionInicial, false);
		for (int i=1; i<getLongitud(tipo); ++i){
			posicionInicial = posicionInicial.calculaPosicionSiguiente (direccion);
			_posicionesOcupadas[i] = new PosEstado (posicionInicial, false);
		}
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

		return _posicionesOcupadas[i].getPosicion();
	}

	/**
	 * Mutador para las posiciones que ocupa el barco
	 * @param i posicion que ocupa en el array la posicion(posEstado) que queremos cambiar
	 * @param pos posicion que queremos colocar
	 * @param estado Estado de la casilla con posicion pos (True=atacada, False=NO atacada)
	 */
	public void setPosicion(int i, Posicion pos, boolean estado) {

		_posicionesOcupadas[i] = new PosEstado (pos, estado);
		
		if (estado) {
			if (revisaSiDebeHundirse()){
				_estado = EstadoBarco.HUNDIDO;
			}
			else
				_estado = EstadoBarco.TOCADO;
		}
	
	}
	
	/**
	 * Funcion que recorre las posiciones ocupadas del barco y deduce si el barco esta hundido
	 * buscando una posicion que no haya sido atacada.
	 * @return valor booleano que indica si el barco esta hundido (true) o no(false)
	 */
	private boolean revisaSiDebeHundirse() {
		
		int i = 0;
		boolean hundido = true;
		while (i < getLongitud(_tipo) && hundido) {
			if (_posicionesOcupadas[i].getEstado() == false)
				hundido = false; // En cuanto una posicion del barco no ha sido
									// atacada, el barco no puede estar hundido
			else
				++i;
		}
		return hundido;
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
			if (_posicionesOcupadas[i].getPosicion().equals(pos))
				esta = true;
			else
				++i;
		}

		return esta;
	}

	/**
	 * Funcion que comprueba si un barco esta tocado
	 * @return Verdadero si el barco esta tocado, falso e.o.c.
	 */
	public boolean tocado() {

		return (_estado == EstadoBarco.TOCADO); 
	}

	/**
	 * Funcion que comprueba si un barco esta hundido
	 * @return Verdadero si el barco está hundido, falso e.o.c.
	 */
	public boolean hundido() {

		return (_estado == EstadoBarco.HUNDIDO);
	}
	
	/**
	 * Accedente para el estado del Barco
	 * @return Estado del barco
	 */
	public EstadoBarco getEstado() {

		return _estado;
	}
	
	/**
	 * Accedente para conocer las posiciones ocupadas por un barco
	 * 
	 * @return array con las posiciones ocupadas por un barco
	 */
	public Posicion[] getPosicionesOcupadas() {
		
		Posicion[] posiciones = new Posicion[getLongitud(_tipo)];
		for (int i=0; i<getLongitud(_tipo); ++i)
			posiciones[i] = _posicionesOcupadas[i].getPosicion().copia();
		return posiciones;
	}
	
	//--------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------
	//DEFINIMOS UNA CLASE INTERNA QUE GUARDE LA POSICION Y SU ESTADO
	//--------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------
	/**
	 * Con esta clase pretendemos agrupar una Posicion con su estado, siendo este un simple boleano
	 * que indica si la posicion ha sido atacada (true) o no (false)
	 * @author Jessica Martin & Javier Martin
	 * @version 1.0
	 * @see Posicion
	 */
	 private class PosEstado {

		//--------------------------
		//ATRIBUTOS
		//--------------------------
		private Posicion _posicion;
		private boolean _atacada;//una casilla puede estar atacada (true) o no (false)
		//--------------------------
		
		/**
		 * Constructor de una posicion con su respectivo estado, esta vez, parametrizado.
		 * @param pos Posicion con la que se construirá el 'PosEstado'
		 * @param b valor booleano que indica si la posicion (pos) ha sido atacada o no  
		 */
		public PosEstado(Posicion pos, boolean b) {
			_posicion = pos.copia();
			_atacada = b;
		}

		/**
		 * Accedente para el atributo Posicion
		 * @return Posicion que se solicita.          
		 */
		public Posicion getPosicion() {
			return _posicion;
		}
		
		/**
		 * Accedente para el atributo estado
		 * @return valor booleano que indica si la posicion ha sido atacada o no          
		 */
		public boolean getEstado() {
			return _atacada;
		}
	}
}
