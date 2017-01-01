//MenuBarcos.java

package lige.Grupo03.pr5.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import lige.Grupo03.pr5.controlador.Controlador;

/**
 * Clase que define el menu de opciones del juego de los barcos
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see VentanaPrincipal
 */
public class MenuBarcos extends JMenuBar {

	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	private Controlador _controlador;
	//------------------------

	/**
	 * Constructor parametrizado
	 * 
	 * @param _controlador Ventana (Frame) que se considera "padre" del menú (lo contiene)
	 */
	public MenuBarcos(Controlador controlador) {
		super();
		_controlador = controlador;
		//OPCIONES DEL MENU
		JMenu menuSalir = new JMenu("Salir");
		menuSalir.setMnemonic('S');
		JMenu menuAyuda = new JMenu("Ayuda");
		menuAyuda.setMnemonic('A');
		JMenu menuPartida = new JMenu("Partida");
		menuPartida.setMnemonic('P');
		menuSalir.setToolTipText("Menu para salir de la aplicación");
		menuAyuda.setToolTipText("Menu para ver la ayuda");
		menuPartida.setToolTipText("Menu para desplegar las opciones de partida");
		this.add(menuPartida);
		this.add(menuAyuda);
		this.add(menuSalir);
		//ITEMS
		JMenuItem nuevaPartida = new JMenuItem("Nueva Partida", 'N');
		nuevaPartida.setToolTipText("Pulsa aquí para iniciar una nueva partida");
		menuPartida.add(nuevaPartida);
		JMenuItem finPartida = new JMenuItem("Terminar Partida", 'T');
		finPartida.setToolTipText("Pulsa aquí para terminar una partida");
		menuPartida.add(finPartida);
		JMenuItem ayuda = new JMenuItem("Ver Ayuda", 'V');
		ayuda.setToolTipText("Pulsa aquí para ver la ayuda");
		menuAyuda.add(ayuda);
		JMenuItem salir = new JMenuItem("Salir", 'a');
		salir.setToolTipText("Pulsa aquí para salír de la aplicación");
		menuSalir.add(salir);
		//EVENTOS
		nuevaPartida.addActionListener(new OyenteItem());
		finPartida.addActionListener(new OyenteItem());
		salir.addActionListener(new OyenteItem());
		ayuda.addActionListener(new OyenteItem());
	}
	

	
	/**
	 * Clase que define el metodo actionPerformed en respuesta al evento que desencadenan los items del menu
	 * 
	 * @author Jessica Martin & Javier Martin
	 * @version 1.0
	 * @see MenuBarcos
	 */
	private class OyenteItem implements ActionListener {
		
		/**
		 * Método que recoge la funcionalidad que hay que llevar a cabo como resultado 
		 * de un evento, (en este caso de los JMenuItems)
		 * 
		 * @param e Evento desencadenado al que se da respuesta
		 */
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if(e.getSource() instanceof JMenuItem) {
				if("Salir".equals(actionCommand)){
					System.exit(0);
				}
				if("Ver Ayuda".equals(actionCommand)){
					respondeAyuda();
				}
				if("Terminar Partida".equals(actionCommand)){
					_controlador.terminaPartida();
				}
				if("Nueva Partida".equals(actionCommand)){
					respondeNuevaPartida();
				}
			}
		}
		
		/**
		 * Método privado que efectúa la respuesta necesaria al usar el item de Ayuda
		 */
		private void respondeAyuda() {
				try {
					PanelAyuda ayuda = new PanelAyuda();
					JOptionPane.showConfirmDialog(ayuda, ayuda, "Ayuda", JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE);
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "ERROR: No se encontró el archivo de ayuda", "AYUDA INEXISTENTE", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "ERROR: Ocurrió un promblema al leer el fichero", "ERROR I/O ", JOptionPane.ERROR_MESSAGE);
				}
		}
		
		/**
		 * Método privado que efectúa la respuesta necesaria al usar el item de Nueva Partida
		 */
		private void respondeNuevaPartida () {
			PanelSolicitaDatos panelDatos = new PanelSolicitaDatos();
			if(JOptionPane.showConfirmDialog(panelDatos, panelDatos, "Introduzca datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)
					== JOptionPane.OK_OPTION) {
				try {
					if ((panelDatos.getAncho() >16) || (panelDatos.getAncho() <10) || (panelDatos.getAlto() >16) || (panelDatos.getAlto() <10))
						JOptionPane.showMessageDialog(null, panelDatos.MensajeError(), "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
					else{
						_controlador.nuevaPartida(panelDatos.getAncho(), panelDatos.getAlto(), panelDatos.esModoDebug(), panelDatos.getJugador());
					}
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, panelDatos.MensajeError(), "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}	
}


