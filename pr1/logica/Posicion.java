//Posicion.java

package lige.grupo03.pr1.logica;


/**
* Clase que modela una posicion del tablero en el juego Hundir la Flota
* @author Javier Martin & Jessica Martin
* @version 1.0
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
	 * @param minguno
	 * @return nada
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
	 * @param ninguno
	 * @return valor de la coordenada X
	 */
	public int getX () {
		return _x;
	}
	
	/**
	 * Accedente para la coordenada Y de la posicion
	 * @param ninguno
	 * @return valor de la coordenada Y
	 */
	public int getY () {
		return  _y;
	}
	
	/**
	 * Mutador para la coordenada X de la posicion
	 * @param otroX Valor que se quiere asignar a la coordenada X
	 * @return nada
	 */
	public void setX (int otroX) {
		_x = otroX;
	}
	
	/**
	 * Mutador para la coordenada Y de la posicion
	 * @param otroY Valor que se quiere asignar a la coordenada Y
	 * @return nada
	 */
	public void setY (int otroY) {
		_y = otroY;
	}
	
	/**
	 * Redefinicion del método toString() para mostrar una posicion
	 * @param ninguno
	 * @return el string que se quiere mostrar
	 */
	public String toString() {
		
		String s = "La x es: " + getX() + ", y la y es: " + getY();
		return s;
	}
	
	/**
	 * Metodo privado para unificar todas las comparaciones de posiciones,
	 * mostrando mensajes por pantalla
	 * @param p1 una posicion de un tablero
	 * @param p2 otra posicion del tablero
	 * @return nada
	 */	
	private static void comparaPosiciones (Posicion p1, Posicion p2) {
		
		if (p1.equals(p2))
			System.out.println ("Son iguales");
		else
			System.out.println ("Son distintos");
	}
	
	/**
	 * Metodo principal de la aplicacion, 
	 * que supone el punto de entrada para Hundir la Flota
	 * @param args argumentos usados como parámetros de la aplicacion
	 * @return nada
	 */	
	public static void main(String[] args) {
		
		//---------------------
		//PRUEBAS DE POSICION
		//---------------------
		System.out.println("-----------PRUEBAS DE POSICION----------------");
		
		Posicion p1 = new Posicion(2, 3);
		System.out.println("La posicion 1 es:");
		System.out.println(p1);
		System.out.println();
		
		Posicion p2 = new Posicion(1, 3);
		System.out.println("La posicion 2 es:");
		System.out.println(p2);
		System.out.println();
		
		Posicion p3 = new Posicion(2, 4);
		System.out.println("La posicion 3 es:");
		System.out.println(p3);
		System.out.println();
		
		Posicion p4 = new Posicion();
		System.out.println("La posicion 4 es:");
		System.out.println(p4);
		System.out.println();
		
		Posicion p5 = new Posicion(2,4);
		System.out.println("La posicion 5 es:");
		System.out.println(p5);
		System.out.println();
		

		System.out.println("Hacemos unas cuantas comparaciones");
		comparaPosiciones (p1, p2);
		comparaPosiciones (p1, p3);
		comparaPosiciones (p2, p3);
		comparaPosiciones (p2, p2);
		comparaPosiciones (p2, p4);
		comparaPosiciones (p3, p5);
		System.out.println("-----------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
}
