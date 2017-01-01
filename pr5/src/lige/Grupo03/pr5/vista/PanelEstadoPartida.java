//PanelEstadoPartida.java

package lige.Grupo03.pr5.vista;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lige.Grupo03.pr5.modelo.Constantes;

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
	private JLabel _hundidos, _tocados, _intactos, _numDisparos, _dispersiones, _sonicos;
	//------------------------
	
	/**
	 * Constructor predeterminado
	 */
	public PanelEstadoPartida () {
		super();
		this.setLayout(new GridLayout (4,2,6,6));
		_hundidos = new JLabel ("   Barcos Hundidos: 0");
		_tocados = new JLabel ("   Barcos Tocados: 0");
		_intactos = new JLabel ("   Barcos Intactos: " + Constantes.NUM_BARCOS);
		_numDisparos = new JLabel ("   Disparos Totales realizados: 0");
		_dispersiones = new JLabel ("   Disparos de dispersión restantes: 2");
		_sonicos = new JLabel ("   Disparos sónicos restantes: 4");
		_hundidos.setToolTipText("Número de barcos hundidos hasta el momento");
		_tocados.setToolTipText("Número de barcos tocados hasta el momento");
		_intactos.setToolTipText("Número de barcos intactos en este momento");
		_numDisparos.setToolTipText("Número de disparos realizados hasta el momento");
		_dispersiones.setToolTipText("Número de disparos de dispersión que puedes usar");
		_sonicos.setToolTipText("Número de disparos sónicos que puedes usar");
		this.add(_tocados);
		this.add(_dispersiones);
		this.add(_hundidos);
		this.add(_sonicos);
		this.add(_intactos);
		this.add(_numDisparos);
		this.add(new JLabel());
		this.add(new JLabel());
		this.setVisible(true);
	}

	/**
	 * Método que cambia el texto de la etiqueta de disparos 
	 * actualizandola con la nueva cantidad.
	 * 
	 * @param numDisparos Numero de disparos que se llevan realizados 
	 */
	public void actualiza(int numDisparos) {
		_numDisparos.setText("   Disparos Totales realizados: " + numDisparos);	
	}
	
	/**
	 * Método que cambia el texto de la etiqueta de disparos de dispersion restantes 
	 * actualizandola con la nueva cantidad.
	 * 
	 * @param dispersiones Numero de disparos de dispersion restantes
	 */
	public void actualizaDispersiones(int dispersiones) {
		 _dispersiones.setText("   Disparos de dispersión restantes: " + dispersiones);
	}
	
	/**
	 * Método que cambia el texto de la etiqueta de disparos sonicos restantes 
	 * actualizandola con la nueva cantidad.
	 * 
	 * @param sonicos Numero de disparos sonicos restantes
	 */
	public void actualizaSonicos(int sonicos) {
		_sonicos.setText("   Disparos sónicos restantes: " + sonicos);
	}

	/**
	 * Método que cambia el texto de las etiquetas de tocados y hundidos 
	 * actualizandolas con la nueva cantidad.
	 * 
	 * @param numIntactos Numero de barcos intactos
	 * @param numTocados Numero de barcos tocados
	 */
	public void toca(int numIntactos, int numTocados) {
		_intactos.setText("   Barcos Intactos: " + numIntactos);
		_tocados.setText("   Barcos Tocados: " + numTocados);
	}
	
	/**
	 * Método que cambia el texto de las etiquetas de intactos, tocados y hundidos 
	 * actualizandolas con la nueva cantidad.
	 * 
	 * @param numIntactos Numero de barcos intactos
	 * @param numTocados Numero de barcos tocados
	 * @param numHundidos Numero de barcos hundidos
	 */
	public void hunde(int numIntactos, int numTocados, int numHundidos) {
		_intactos.setText("   Barcos Intactos: " + numIntactos);
		_tocados.setText("   Barcos Tocados: " + numTocados);
		_hundidos.setText("   Barcos Hundidos: " + numHundidos);
	}
}
