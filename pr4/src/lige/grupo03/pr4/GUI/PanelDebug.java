//PanelDebug.java

package lige.grupo03.pr4.GUI;

import java.awt.Color;
import java.awt.GridLayout;

/**
 * Clase que define el panel que contiene el tablero de Debug
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.1
 */
public class PanelDebug extends PanelSobreTableros{

	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	//------------------------
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param ancho ancho del tablero elegido
	 * @param alto alto del tablero elegido
	 */
	public PanelDebug (int ancho, int alto) {
		super(ancho, alto);
		this.setLayout(new GridLayout (alto+1, ancho+1, 1, 1)); 
	}

	/**
	 * Metodo para devolver un boton con unas caracteristicas propias
	 * 
	 * @param i coordenada para la fila
	 * @param j coordenada para la columna
	 */
	protected BotonTablero getBoton(int i, int j){
		BotonTablero boton = new BotonTablero("", i, j);
		boton.setEnabled(false);
		boton.setBackground(Color.WHITE);
		boton.setToolTipText("Estado de la coordenada (" + i + "," + j + ")" );
		return boton;
	}
}
