//PanelSobreTaberos.java

package lige.grupo03.pr4.GUI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Clase que contiene los elementos comunes en los botones de ambos tableros (normal y debug)
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see PanelTablero
 * @see PanelDebug
 */
public abstract class PanelSobreTableros extends JPanel{

	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	//------------------------
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param ancho Ancho del Tablero.
	 * @param alto Alto del Tablero.
	 */
	public PanelSobreTableros (int ancho, int alto) {
		super();
		this.setLayout(new GridLayout (alto+1, ancho+1, 5, 5));
		this.add(new JLabel());
		for (int k=1; k<=ancho; ++k){
			JLabel numColumna = new JLabel ("" + k);
			numColumna.setToolTipText("Columna " + k);
			numColumna.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(numColumna);
		}
		for (int i=1; i<=alto; ++i){
			JLabel numFila = new JLabel("" + i);
			numFila.setToolTipText("Fila " + i);
			numFila.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(numFila);
			for (int j=1; j<=ancho; ++j) {
				this.add(getBoton(i,j));
			}
		}
	}

	//METODO ABSTRACTO
	protected abstract BotonTablero getBoton(int i, int j);
}
