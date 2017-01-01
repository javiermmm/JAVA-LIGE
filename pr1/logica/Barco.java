//Barco.java

package lige.grupo03.pr1.logica;

import lige.grupo03.pr1.logica.Constantes.*;

/**
* Clase que modela los barcos del Hundir la Flota
* @author Javier Martin & Jessica Martin
* @version 1.0
* @see Posicion
* @see Constantes
*/
public class Barco {

	//----------
	//ATRIBUTOS
	//----------
	private Posicion[] _posicionesOcupadas;
	private TipoBarco _tipo;
	private int _long;
	//----------
	
	
	/**
	 * Funcion que dado un tipo de barco, devuelve la longitud del mismo
	 * @param tipo Tipo del barco (Portaaviones, fragata, acorazado, submarino)
	 * @return número de casillas que ocupa el barco
	 */
	private int dameNumCasillas (TipoBarco tipo) {
		
		if (tipo == TipoBarco.PORTAAVIONES)
			return 4;
		if (tipo == TipoBarco.ACORAZADO)
			return 3;
		if (tipo == TipoBarco.FRAGATA)
			return 2;
		if (tipo == TipoBarco.SUBMARINO)
			return 1;
		
		return 0;
	}
	
	/**
	 * Funcion que dada una posicion inicial y la direccion de orientacion 
	 * devuelve la siguiente posicion en esa direccion
	 * @param inicial Posicion inicial del barco
	 * @param direccion Direccion que indica la orientacion del barco
	 * @return La posicion siguiente a la casilla inicial en la direccion dada.
	 */
	private Posicion calculaPosicionSiguiente (Posicion inicial, Direccion direccion) {
		
		int Xinicial = inicial.getX();
		int Yinicial = inicial.getY();
		Posicion posSiguiente = new Posicion (Xinicial, Yinicial);
		
		if (direccion == Direccion.NORTE)
			posSiguiente.setX(Xinicial-1);
		if (direccion == Direccion.SUR)
			posSiguiente.setX(Xinicial+1);
		if (direccion == Direccion.ESTE)
			posSiguiente.setY(Yinicial+1);
		if (direccion == Direccion.OESTE)
			posSiguiente.setY(Yinicial-1);
			
		return posSiguiente; 
	}
	
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
		_long = dameNumCasillas (tipo);
		_posicionesOcupadas = new Posicion [_long];
		_posicionesOcupadas[0] = posicionInicial;
		for (int i=1; i<_long; ++i)
			_posicionesOcupadas[i] = calculaPosicionSiguiente (_posicionesOcupadas[i-1], direccion);
		//En este caso las posiciones no tienen por qué ser válidas. se controlará donde se deba.
	}

	/**
	 * Accedente para la longitud del barco
	 * @param ninguno
	 * @return longitud del barco
	 */
	public int getLongitud() {
		return _long;
	}
	
	/**
	 * Accedente para el tipo del barco
	 * @param ninguno
	 * @return Tipo del barco
	 */
	public TipoBarco getTipoBarco () {
		return _tipo;
	}
	
	/**
	 * Accedente para las posiciones que ocupa el barco
	 * @param ninguno
	 * @return Posiciones que ocupa el barco
	 */
	public Posicion[] getPosiciones(){
		
		return _posicionesOcupadas;
	}
	
	/**
	 * Funcion que dada una posicion comprueba si el barco esta ocupando dicha posicion
	 * @param pos Posicion que se quiere comprobar
	 * @return Verdadero si el barco esta en la posicion, falso e.o.c.
	 */
	public boolean estaEnLaPosicion (Posicion pos){
		
		boolean esta = false;
		int i=0;
		int maxPosiciones = _posicionesOcupadas.length;
		while ((i<maxPosiciones) && (!esta)) {
			if (_posicionesOcupadas[i].equals(pos))
				esta = true;
			else 
				++i;
		}
		
		return esta;
	}
	
	public static void main(String[] args) {
	
		//---------------------
		//PRUEBAS DE BARCO
		//---------------------
		System.out.println("-----------PRUEBAS DE BARCO----------------");
		System.out.println();
		System.out.println("Ponemos como posicion inicial una cualquiera y miramos si esta");
		System.out.println();
		
		Posicion p1 = new Posicion (1,5);
		Barco b1 = new Barco(TipoBarco.FRAGATA, p1, Direccion.OESTE);
		if (b1.estaEnLaPosicion(p1))
				System.out.println("El barco ocupa esta posicion");
		else
			System.out.println("El barco NOOO ocupa esta posicion");
		
		System.out.println("Ponemos como posicion inicial (0,0) y miramos si esta la que creamos antes");
		System.out.println();
		Posicion p2 = new Posicion();
		Barco b2 = new Barco(TipoBarco.PORTAAVIONES, p2, Direccion.ESTE);
		if (b2.estaEnLaPosicion(p1))
				System.out.println("El barco ocupa esta posicion");
		else
			System.out.println("El barco NOOO ocupa esta posicion");
		
	}
}

