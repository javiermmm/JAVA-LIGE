//PanelColocaBarco.java

package lige.grupo03.pr6.editor.vista;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lige.grupo03.pr6.editor.modelo.Constantes.Direccion;
import lige.grupo03.pr6.editor.modelo.Constantes.TipoBarco;

/**
 * Clase que define el panel que se muestra para solicitar los datos necesarios al colocar un barco (tipo y direccion)
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class PanelColocaBarco extends JPanel{

	//----------------------------------
	//ATRIBUTOS
	//----------------------------------
	private static final long serialVersionUID = 1L;
	private String[] listaBarcos = {"Portaaviones", "Acorazado", "Fragata", "Submarino"};
	private String[] listaDir = {"Norte", "Sur", "Este", "Oeste"};
	private JComboBox<String> _barco;
	private JComboBox<String> _direccion;
	//----------------------------------
	
	/**
	 * Constructor predeterminado
	 */
	public PanelColocaBarco() {
		super();
		setLayout(new GridLayout(3, 2, 10, 10));
		JLabel etiquetaBarco = new JLabel("Tipo de Barco:", JLabel.CENTER);
		etiquetaBarco.setToolTipText("Tipo de barco que quieres colocar");
		this.add(etiquetaBarco);
		JLabel etiquetaOrientacion = new JLabel("Direccion:", JLabel.CENTER);
		etiquetaBarco.setToolTipText("Orientación en la que quieres colocar el barco");
		this.add(etiquetaOrientacion);
		_barco = new JComboBox<String> (listaBarcos);
		_barco.setToolTipText("Elige el tipo de barco pulsando en el desplegable");
		_direccion = new JComboBox<String> (listaDir);
		_direccion.setToolTipText("Elige la direccion que tendra el barco pulsando en el desplegable");
		this.add(_barco);
		this.add(_direccion);
	}

	/**
	 * Accedente para la direccion elegida.
	 * @return Direccion seleccionada en el cuadro de dialogo.
	 */
	public Direccion getDireccion() {
		if (_direccion.getSelectedIndex() == 0)
			return Direccion.NORTE;
		if (_direccion.getSelectedIndex() == 1)
			return Direccion.SUR;
		if (_direccion.getSelectedIndex() == 2)
			return Direccion.ESTE;
		else
			return Direccion.OESTE;
	}

	/**
	 * Accedente para el tipo de barco elegido.
	 * @return Tipo de Barco que se ha elegido colocar
	 */
	public TipoBarco getBarco() {
		if (_barco.getSelectedIndex() == 0)
			return TipoBarco.PORTAAVIONES;
		if (_barco.getSelectedIndex() == 1)
			return TipoBarco.ACORAZADO;
		if (_barco.getSelectedIndex() == 2)
			return TipoBarco.FRAGATA;
		else
			return TipoBarco.SUBMARINO;
	}

	/**
	 * Funcion que devuelve un mensaje de error al intentar colocar un barco indebidamente
	 * @return String con el mensaje de error
	 */
	public String MensajeError() {
		return "ERROR: No se puede colocar el barco. Por favor, revisa el número de barcos " +
				"disponibles del tipo seleccionado y la disposicion del tablero, y vuelve " +
				"a intentarlo después";
	}
}
