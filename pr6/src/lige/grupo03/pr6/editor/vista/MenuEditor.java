//MenuEditor.java

package lige.grupo03.pr6.editor.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import lige.grupo03.pr6.editor.controlador.Controlador;

/**
 * Clase que define el menu del editor de escenarios
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Controlador
 * @see PanelSolicitaDatos
 */
public class MenuEditor extends JMenuBar {

	//-------------------------------------------------
	//ATRIBUTOS
	//-------------------------------------------------
	private static final long serialVersionUID = 1L;
	private Controlador _controlador;
	private boolean _guardadoAlgunaVez;
	private JMenuItem _guardar, _guardarComo;
	//-------------------------------------------------

	/**
	 * Constructor parametrizado
	 * 
	 * @param _controlador Ventana (Frame) que se considera "padre" del menú (lo contiene)
	 */
	public MenuEditor(Controlador controlador) {
		super();
		_controlador = controlador;
		_guardadoAlgunaVez = false;
		//OPCIONES DEL MENU
		JMenu menuSalir = new JMenu("Salir");
		menuSalir.setMnemonic('S');
		JMenu menuAyuda = new JMenu("Ayuda");
		menuAyuda.setMnemonic('y');
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic('A');
		menuSalir.setToolTipText("Menu para salir de la aplicación");
		menuAyuda.setToolTipText("Menu para ver la ayuda");
		menuArchivo.setToolTipText("Menu para desplegar las opciones de archivo");
		this.add(menuArchivo);
		this.add(menuAyuda);
		this.add(menuSalir);
		//ITEMS
		JMenuItem nuevo = new JMenuItem("Nuevo", 'N');
		nuevo.setToolTipText("Pulsa aquí para crear un nuevo escenario");
		menuArchivo.add(nuevo);
		JMenuItem abrir = new JMenuItem("Abrir", 'A');
		abrir.setToolTipText("Pulsa aquí para abrir un escenario existente");
		menuArchivo.add(abrir);
		_guardar = new JMenuItem("Guardar", 'G');
		_guardar.setToolTipText("Pulsa aquí para guaradar el escenario actual");
		_guardar.setEnabled(false);
		menuArchivo.add(_guardar);
		_guardarComo = new JMenuItem("Guardar como...", 'c');
		_guardarComo.setToolTipText("Pulsa aquí para guardar el escenario actual eligiendo nombre y ubicacion");
		_guardarComo.setEnabled(false);
		menuArchivo.add(_guardarComo);		
		JMenuItem ayuda = new JMenuItem("Ver Ayuda", 'V');
		ayuda.setToolTipText("Pulsa aquí para ver la ayuda");
		menuAyuda.add(ayuda);
		JMenuItem salir = new JMenuItem("Salir", 'a');
		salir.setToolTipText("Pulsa aquí para salír de la aplicación");
		menuSalir.add(salir);
		//EVENTOS
		nuevo.addActionListener(new OyenteItem());
		abrir.addActionListener(new OyenteItem());
		_guardar.addActionListener(new OyenteItem());
		_guardarComo.addActionListener(new OyenteItem());
		salir.addActionListener(new OyenteItem());
		ayuda.addActionListener(new OyenteItem());
	}
	
	/**
	 * Metodo que habilita los items para guardar y guardar como
	 */
	public void habilitaGuardado() {
		_guardar.setEnabled(true);
		_guardarComo.setEnabled(true);
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
				if("Nuevo".equals(actionCommand)){
					respondeNuevoEscenario ();
				}
				if("Abrir".equals(actionCommand)){
					JFileChooser fc = new JFileChooser();
					int respuesta = fc.showOpenDialog(null);
					if (respuesta == JFileChooser.APPROVE_OPTION) {
						File archivoOrigen = fc.getSelectedFile();
						_controlador.abrirArchivo(archivoOrigen);
					}
				}
				if("Guardar".equals(actionCommand)){
					if (_guardadoAlgunaVez)
						_controlador.guardar();
					else
						guardarComo();
				}
				if("Guardar como...".equals(actionCommand)){
					guardarComo();
				}
			}
		}
		
		/**
		 * Metodo que abre el cuadro de dialogo para guardar el fichero y propaga la orden para "guardar como"
		 */
		private void guardarComo() {
			JFileChooser fc = new JFileChooser();
			int respuesta = fc.showSaveDialog(null);
			if (respuesta == JFileChooser.APPROVE_OPTION) {
				File archivoDestino = fc.getSelectedFile();
				_controlador.guardarComo(archivoDestino);
				_guardadoAlgunaVez = true;
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
		private void respondeNuevoEscenario () {
			PanelSolicitaDatos panelDatos = new PanelSolicitaDatos();
			if(JOptionPane.showConfirmDialog(panelDatos, panelDatos, "Introduzca datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)
					== JOptionPane.OK_OPTION) {
				try {
					if ((panelDatos.getAncho() >16) || (panelDatos.getAncho() <10) || (panelDatos.getAlto() >16) || (panelDatos.getAlto() <10))
						JOptionPane.showMessageDialog(null, panelDatos.MensajeError(), "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
					else{
						_controlador.creaEscenario(panelDatos.getAncho(), panelDatos.getAlto());
					}
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, panelDatos.MensajeError(), "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
