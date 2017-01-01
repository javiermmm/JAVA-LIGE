//PanelFondo.java

package lige.grupo03.pr6.editor.vista;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que define el panel que muestra la imagen de bienvenida
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class PanelFondo extends JPanel{

	//----------------------------------------------
	//ATRIBUTO
	//----------------------------------------------
	private static final long serialVersionUID = 1L;
	//----------------------------------------------

	/**
	 * Constructor parametrizado
	 * 
	 * @param imagen Nombre de la imagen que se va a colocar en el fondo
	 */
	public PanelFondo (String imagen) {
		super();
		this.setLayout(new GridLayout(1,1,0,0));
		this.setBackground(Color.WHITE);
		add(new JLabel(new ImageIcon(imagen)));
	}
}
