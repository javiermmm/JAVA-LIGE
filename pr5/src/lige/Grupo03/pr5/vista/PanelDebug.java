//PanelDebug.java

package lige.Grupo03.pr5.vista;

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
	 * Método vacio, sin valor.
	 * Esta vacio, porque no hace nada, pero debe existir, como "daño colateral" en la 
	 * maniobra por evitar crear cientos de oyentes de botones. De esta manera, 
	 * por lo menos ahorramo en complejidad espacial.
	 */
	public void getOyente() {}


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

	/**
	 * Metodo para mostrar los barcos del tablero de debug con colorines
	 * 
	 * @param tableroInicial Array de caracteres con los barcos colocados
	 * @param ancho Entero con el ancho del tablero
	 * @param alto Entero con el alto del tablero
	 */
	public void pintaBarquitos(char [][] tableroInicial, int ancho, int alto) {
		for (int i=0;i<alto;++i)
			for (int j=0;j<ancho;++j){
				if (tableroInicial[i][j] == 'S')
					_botones[i][j].setBackground(Color.YELLOW);
				if (tableroInicial[i][j] == 'F')
					_botones[i][j].setBackground(Color.BLUE);
				if (tableroInicial[i][j] == 'A')
					_botones[i][j].setBackground(Color.GREEN);
				if (tableroInicial[i][j] == 'P')
					_botones[i][j].setBackground(Color.BLACK);
			}
	}
}
