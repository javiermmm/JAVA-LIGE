//PanelOpcional.java

package lige.Grupo03.pr5.vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lige.Grupo03.pr5.controlador.Controlador;

/**
 * Clase que define el panel con los botones para hacer disparos especiales
 * (Opcional en la practica 5)
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class PanelOpcional extends JPanel{

	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	private Controlador _controlador;
	private JButton _dispersion, _sonico;
	//------------------------
	
	/**
	 * Constructor predeterminado
	 * 
	 * @param controlador Controlador de la aplicacion [Patron MVC]
	 */
	public PanelOpcional (Controlador controlador) { 
		super();
		_controlador = controlador;
		this.setLayout(new GridLayout (7, 1, 10, 10));
		this.add(new JLabel());
		this.add(new JLabel());
		_dispersion = new JButton(new ImageIcon("explosion.jpg"));
		_dispersion.setBackground(Color.BLACK);
		_dispersion.addActionListener(new OyenteDisparoDispersion());
		this.add(_dispersion);
		this.add(new JLabel());
		_sonico = new JButton(new ImageIcon("radar.jpg"));
		_sonico.setBackground(Color.WHITE);
		_sonico.addActionListener(new OyenteDisparoSonico());
		_sonico.setSize(40, 30);
		this.add(_sonico);
		this.add(new JLabel());
		this.add(new JLabel());
	}
	
	/**
	 * Método que deshabilita el boton de disparo de dispersion
	 * una vez se han gastado los dos disparos de este tipo 
	 */
	public void deshabilitaDispersiones() {
		_dispersion.setEnabled(false);
	}

	/**
	 * Método que deshabilita el boton de disparo sónico
	 * una vez se han gastado los cuatro disparos de este tipo 
	 */
	public void deshabilitaSonicos() {
		_sonico.setEnabled(false);
	}
	
	/**
	 * Clase que define el metodo actionPerformed en respuesta al evento que desencadena
	 * el click del boton de disparo de dispersion.
	 * 
	 * @author Jessica Martin & Javier Martin
	 * @version 1.0
	 * @see PanelOpcional
	 */
	private class OyenteDisparoDispersion implements ActionListener {
		
		/**
		 * Método que recoge la funcionalidad que hay que llevar a cabo como resultado 
		 * de un evento, (en este caso de los JMenuItems)
		 * 
		 * @param e Evento desencadenado al que se da respuesta
		 */
		public void actionPerformed(ActionEvent e) {
			_controlador.activaDispersion();
		}
	}
	
	/**
	 * Clase que define el metodo actionPerformed en respuesta al evento que desencadena
	 * el click del boton de disparo sónico.
	 * 
	 * @author Jessica Martin & Javier Martin
	 * @version 1.0
	 * @see PanelOpcional
	 */
	private class OyenteDisparoSonico implements ActionListener {

		/**
		 * Método que recoge la funcionalidad que hay que llevar a cabo como resultado 
		 * de un evento, (en este caso de los JMenuItems)
		 * 
		 * @param e Evento desencadenado al que se da respuesta
		 */
		public void actionPerformed(ActionEvent e) {
			_controlador.activaSonico();
		}
	}
}
