//PanelSolicitaDatos.java

package lige.grupo03.pr4.GUI;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Clase que define el panel que le pide al usuario 
 * los datos necesarios para iniciar una nueva partida
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class PanelSolicitaDatos extends JPanel {

	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	private String[] lista = {"1- Jugador Humano", "2- Jugador IA", "3- Jugador IA Mejorada"};
	private JTextField _ancho, _alto;
	private JComboBox<String> _jugador;
	private JRadioButton _normal, _debug;
	//------------------------
	
	/**
	 * Constructor predeterminado
	 */
	public PanelSolicitaDatos() {
		setLayout(new GridLayout(4, 2));
		JLabel etiquetaAncho = new JLabel("Ancho: ", JLabel.RIGHT);
		etiquetaAncho.setToolTipText("Ancho del tablero de juego");
		_ancho = new JTextField();
		_ancho.setToolTipText("Haz click para escribir el ancho");
		add(etiquetaAncho);
		add(_ancho);
		JLabel etiquetaAlto = new JLabel("Alto:", JLabel.RIGHT);
		etiquetaAlto.setToolTipText("Alto del tablero de juego");
		_alto = new JTextField();
		_alto.setToolTipText("Haz click para escribir el alto");
		add(etiquetaAlto);
		add(_alto);
		ButtonGroup grupoBotones = new ButtonGroup();
		_normal = new JRadioButton("Modo normal");
		_normal.setToolTipText("Modo de juego normal");
		_debug = new JRadioButton("Modo debug", true);
		_debug.setToolTipText("Modo de juego debug (depuración)");
		grupoBotones.add(_normal);
		grupoBotones.add(_debug);
		add(_normal);
		add(_debug);
		_jugador = new JComboBox<String> (lista);
		_jugador.setToolTipText("Tipo de jugador para la partida");
		add(_jugador);
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
	
	/**
	 * Funcion que devuelve el tipo de jugador que eligió 
	 * el usuario codificado como un entero en base a su indice respecto del ComboBox
	 * 
	 * @return Valor entero con el tipo de jugador introducido 
	 */
	public int getJugador() {
		return _jugador.getSelectedIndex()+1;
	}
	
	/**
	 * Funcion que devuelve el modo de juego que eligió 
	 * el usuario codificado como un entero (siguiendo la linea del 
	 * metodo empleado en la parte de consola de la practica anterior)
	 * 
	 * @return Valor entero con el modo de juego introducido 
	 */
	public int getModo() {
		if (_normal.isSelected())
			return 1;
		else
			return 2;
	}
	
}
