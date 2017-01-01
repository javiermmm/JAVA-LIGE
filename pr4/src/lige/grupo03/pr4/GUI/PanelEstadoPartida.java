//PanelEstadoPartida.java

package lige.grupo03.pr4.GUI;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que define el panel que informa del estado de la partida en curso.
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class PanelEstadoPartida extends JPanel {
	
	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	private JLabel _hundidos, _tocados, _intactos, _numDisparos;
	//------------------------
	
	/**
	 * Constructor predeterminado
	 */
	public PanelEstadoPartida () {
		super();
		this.setLayout(new GridLayout (4,1,6,6));
		_hundidos = new JLabel ("   Barcos Hundidos: 0");
		_tocados = new JLabel ("   Barcos Tocados: 0");
		_intactos = new JLabel ("   Barcos Intactos: 0");
		_numDisparos = new JLabel ("   Disparos realizados: 0");
		_hundidos.setToolTipText("Número de barcos hundidos hasta el momento");
		_tocados.setToolTipText("Número de barcos tocados hasta el momento");
		_intactos.setToolTipText("Número de barcos intactos en este momento");
		_numDisparos.setToolTipText("Número de disparos realizados hasta el momento");
		this.add(_tocados);
		this.add(_hundidos);
		this.add(_intactos);
		this.add(_numDisparos);
		this.setVisible(true);
	}

	/**
	 * Método que cambia el texto de la etiqueta de disparos 
	 * actualizandola con la nueva cantidad.
	 * 
	 * @param numDisparos Numero de disparos que se llevan realizados
	 */
	public void actualiza(int numDisparos) {
		_numDisparos.setText("   Disparos realizados: " + numDisparos);
	}
}
