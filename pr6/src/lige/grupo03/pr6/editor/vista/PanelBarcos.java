//PanelBarcos.java

package lige.grupo03.pr6.editor.vista;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Clase que define el panel que muestra la informaacion con el numero de barcos disponibles de cada tipo de barco
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class PanelBarcos extends JPanel{

	//-------------------------------------------------
	//ATRIBUTOS
	//-------------------------------------------------
	private static final long serialVersionUID = 1L;
	JLabel _portaaviones, _acorazados, _fragatas, _submarinos;
	//-------------------------------------------------

	/**
	 * Constructor predeterminado
	 */
	public PanelBarcos() {
		super();
		this.setLayout(new GridLayout(4,2,8,8));
		_portaaviones = new JLabel("Portaaviones: " + "1");
		_portaaviones.setHorizontalAlignment(SwingConstants.CENTER);
		_acorazados = new JLabel("Acorazados: " + "2");
		_acorazados.setHorizontalAlignment(SwingConstants.CENTER);
		_fragatas = new JLabel("Fragatas: " + "3");
		_fragatas.setHorizontalAlignment(SwingConstants.CENTER);
		_submarinos = new JLabel("Submarinos: " + "4");
		_submarinos.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(_portaaviones);
		this.add(_acorazados);
		this.add(_fragatas);
		this.add(_submarinos);
		this.add(new JLabel());
		this.add(new JLabel());
	}

	/**
	 * Metodo que actualiza las labels pertinentes para mostrar el numero correcto de barcos disponibles en cada momento
	 * @param portaaviones Numero de barcos disponibles del tipo portaaviones
	 * @param acorazados Numero de barcos disponibles del tipo acorazado
	 * @param fragatas Numero de barcos disponibles del tipo fragata
	 * @param submarinos Numero de barcos disponibles del tipo submarino
	 */
	public void actualizaBarcosDisponibles(int portaaviones, int acorazados, int fragatas, int submarinos) {
		_portaaviones.setText("Portaaviones: " + portaaviones);
		_acorazados.setText("Acorazados: " + acorazados);
		_fragatas.setText("Fragatas: " + fragatas);
		_submarinos.setText("Submarinos: " + submarinos);
	}
	
	
}
