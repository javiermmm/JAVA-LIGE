//PanelFondo.java

package lige.grupo03.pr4.GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * Clase que define el panel con una imagen de fondo que 
 * se muestra cuando no se esta jugando
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class PanelFondo extends JPanel{

	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	//------------------------
	
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
