//PanelSolicitaDatos.java

package lige.grupo03.pr6.editor.vista;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase que define el panel que solicita el ancho y el alto al crear un nuevo escenario (tablero)
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class PanelSolicitaDatos extends JPanel {

	//---------------------------------------------
	//ATRIBUTOS
	//---------------------------------------------
	private static final long serialVersionUID = 1L;
	private JTextField _ancho, _alto;
	//---------------------------------------------
	
	/**
	 * Constructor predeterminado
	 */
	public PanelSolicitaDatos() {
		setLayout(new GridLayout(1, 3));
		JLabel etiquetaAncho = new JLabel("Ancho: ", JLabel.RIGHT);
		etiquetaAncho.setToolTipText("Ancho del tablero de juego");
		_ancho = new JTextField();
		_ancho.setToolTipText("Haz click para escribir el ancho");
		this.add(etiquetaAncho);
		this.add(_ancho);
		JLabel etiquetaAlto = new JLabel("Alto:", JLabel.RIGHT);
		etiquetaAlto.setToolTipText("Alto del tablero de juego");
		_alto = new JTextField();
		_alto.setToolTipText("Haz click para escribir el alto");
		this.add(etiquetaAlto);
		this.add(_alto);
	}
	
	/**
	 * Funcion que muestra un mensaje de error indicando las posibles causas del mismo
	 * 
	 * @return Mensaje que se le mostrara al usuario para informarle de los errores 
	 */
	public String MensajeError() {
		return "DATOS INCORRECTOS:Por favor,introduce un ancho y un alto entre 10 y 16 (Ambos Inclusive)\n" +
				"Posibles causas del error:\n- El dato introducido no es un entero\n" +
				"- El dato introducido se sale del rango permitido\n" +
				"- No has rellenado algun campo";
	}
	
	/**
	 * Funcion que devuelve el ancho del tablero que introdujo el usuario en el campo de texto correspondiente
	 * 
	 * @return Valor entero con el ancho introducido 
	 */
	public int getAncho () {
		
		return Integer.parseInt(_ancho.getText().toString());
	}
	
	/**
	 * Funcion que devuelve el alto del tablero que introdujo el usuario en el campo de texto correspondiente
	 * 
	 * @return Valor entero con el alto introducido 
	 */
	public int getAlto () {			
		return Integer.parseInt(_alto.getText().toString());
	}
}
