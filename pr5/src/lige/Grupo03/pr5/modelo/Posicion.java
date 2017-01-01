//Posicion.java

package lige.Grupo03.pr5.modelo;

import lige.Grupo03.pr5.modelo.Constantes.Direccion;

/**
* Clase que modela una posicion del tablero en el juego Hundir la Flota
* @author Javier Martin & Jessica Martin
* @version 2.0
*/
public class Posicion {

	//----------
	//ATRIBUTOS
	//----------
	private int _x; //Coordenada Horizontal
	private int _y; //Coordenada Vertical
	//----------
	
	
	/**
	 * Constructor predeterminado. 
	 * Por defecto pondrá las cordenadas (0,0)
	 */
	public Posicion () {
		
		_x = 0;
		_y = 0;
	}
	
	/**
	 * Constructor parametrizado con las coordenadas de la posicion
	 * @param unX valor de la coordenada X
	 * @param unY valor de la coordenada Y
	 */
	public Posicion (int unX, int unY) {
		_x = unX;
		_y = unY;
	}
	
	/**
	 * Funcion para comprobar la igualdad entre dos posiciones
	 * @param posicion Posicion con la que se quiere comparar.
	 * @return Verdadero si las posiciones son iguales, falso e.o.c.
	 */
	public boolean equals (Posicion posicion) {
		
		return ((_x == posicion._x) && (_y == posicion._y));
	}
	
	/**
	 * Accedente para la coordenada X de la posicion
	 * @return valor de la coordenada X
	 */
	public int getX () {
		return _x;
	}
	
	/**
	 * Accedente para la coordenada Y de la posicion
	 * @return valor de la coordenada Y
	 */
	public int getY () {
		return  _y;
	}
	
	/**
	 * Mutador para la coordenada X de la posicion
	 * @param otroX Valor que se quiere asignar a la coordenada X
	 */
	public void setX (int otroX) {
		_x = otroX;
	}
	
	/**
	 * Mutador para la coordenada Y de la posicion
	 * @param otroY Valor que se quiere asignar a la coordenada Y
	 */
	public void setY (int otroY) {
		_y = otroY;
	}

	/**
	 * Funcion con la que conseguimos una copia del objeto Posicion
	 * @return Posicion que copiamos          
	 */
	public Posicion copia() {
		
		Posicion p = new Posicion ();
		p._x = _x;
		p._y = _y;
		
		return p;
	}
	
	/**
	 * Funcion que dada una posicion inicial y la direccion de orientacion
	 * devuelve la siguiente posicion en esa direccion
	 * @param direccion Direccion que indica la orientacion hacia la que seguir
	 * @return La posicion siguiente a la casilla inicial en la direccion dada.
	 */
	public Posicion calculaPosicionSiguiente(Direccion direccion) {

		Posicion posSiguiente = new Posicion(_x, _y);

		if (direccion == Direccion.NORTE)
			posSiguiente.setX(_x - 1);
		if (direccion == Direccion.SUR)
			posSiguiente.setX(_x + 1);
		if (direccion == Direccion.ESTE)
			posSiguiente.setY(_y + 1);
		if (direccion == Direccion.OESTE)
			posSiguiente.setY(_y - 1);

		return posSiguiente;
	}
}