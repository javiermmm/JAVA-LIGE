//BotonTablero.java

package lige.grupo03.pr6.editor.vista;

import java.awt.Color;

import javax.swing.JButton;

import lige.grupo03.pr6.editor.modelo.Posicion;

/**
 * Clase que define los botones del tablero del juego de los barcos
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Posicion
 */
public class BotonTablero extends JButton{

	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	private int _x;
	private int _y;
	//------------------------
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param s Texto del boton
	 * @param x Cordenada X que ocupa el boton en el tablero
	 * @param y Cordenada Y que ocupa el boton en el tablero
	 */
	public BotonTablero (String s, int x, int y) {
		super(s);
		_x = x;
		_y = y;
	}
	
	/**
	 * Accedente para la coordenada x del boton
	 * @return valor de la coordenada x del botón
	 */
	public int getCoordenadaX() {
		return _x;
	}
	
	/**
	 * Accedente para la coordenada y del boton
	 * @return valor de la coordenada y del botón
	 */
	public int getCoordenadaY() {
		return _y;
	}

	/**
	 * Funcion que devuelve la posicion del array a la que se refiere un boton del tablero de botones
	 * @return Posicion del array representada por el boton
	 */
	public Posicion getPosicion() {
		return new Posicion(_x-1, _y-1);
	}

	/**
	 * Funcion que comprueba si un boton se ha coloreado
	 * @return Valor booleano que indica si un boton tiene alguno de los colores 
	 * empleados para representar un barco (true) o no (false)
	 */
	public boolean tieneColor() {
		if ((this.getBackground() == Color.BLACK) || (this.getBackground() == Color.BLUE)
		   ||  (this.getBackground() == Color.YELLOW) || (this.getBackground() == Color.GREEN))
		   return true;
		else
			return false;
	}

}

