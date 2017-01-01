//BotonTablero.java

package lige.Grupo03.pr5.vista;
import javax.swing.JButton;


/**
 * Clase que define los botones del tablero del juego de los barcos
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
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

}
