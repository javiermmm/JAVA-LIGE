//VentanaPrincipal.java

package lige.grupo03.pr6.editor.vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lige.grupo03.pr6.editor.controlador.Controlador;
import lige.grupo03.pr6.editor.modelo.Constantes.TipoEvento;
import lige.grupo03.pr6.editor.vista.Eventos.Evento;
import lige.grupo03.pr6.editor.vista.Eventos.EventoAbrir;
import lige.grupo03.pr6.editor.vista.Eventos.EventoColocarBarco;
import lige.grupo03.pr6.editor.vista.Eventos.EventoNuevoEscenario;
import lige.grupo03.pr6.editor.vista.Eventos.EventoQuitaBarco;
import lige.grupo03.pr6.editor.vista.Eventos.EventoSobreAbrirYNuevo;

/**
 * Clase que define la Ventana Principal con la que interactua el usuario [Vista en el patron MVC]
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Controlador
 * @see MenuEditor
 * @see PanelFondo
 * @see PanelTablero
 * @see PanelBarcos
 */
public class VentanaPrincipal extends JFrame implements Observer {

	//-----------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------
	private static final long serialVersionUID = 1L;
	private Controlador _controlador;
	private Container _panel;
	private MenuEditor _menu;
	private PanelFondo _fondoInicial;
	private PanelTablero _tablero;
	private PanelBarcos _barcos;
	//-----------------------------------------------
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param controlador Controlador de la aplicacion [Patron MVC]
	 */
	public VentanaPrincipal(Controlador controlador) {
		super("Editor de escenarios");
		_controlador = controlador;
		_panel = this.getContentPane();
		_panel.setLayout(new BorderLayout(8,8));
		
		//Menu
		_menu = new MenuEditor(_controlador);
		setJMenuBar(_menu);
		
		//IMAGEN
		_fondoInicial = new PanelFondo("bienvenido.jpg");
		setContentPane(_fondoInicial);
		
		//"Configuramos" la ventana
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //Obtiene el tamaño de la pantalla.
		this.setSize((int) d.getWidth()-300 , (int) d.getHeight()-300); //Establece el tanaño de la ventana (frame) a un tamaño basado en el tamaño de la ventana.
		this.setMinimumSize(getSize());
		this.setMaximumSize(getSize());
		this.setLocation(0,0); //Establece la ventana en la esquina superior ixquierda.
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Terminar la aplicación al cerrar la ventana.
	}
	
	/**
	 * Metodo que compone el aspecto normal de la aplicacion colocando los paneles con el tablero y la informacion de los barcos
	 * @param evento Evento con los datos que necesitamos
	 */
	private void componVentana(EventoSobreAbrirYNuevo evento){
		limpiaVentana();
		_tablero = new PanelTablero (evento.getAncho(), evento.getAlto(), _controlador);
		_panel.add(_tablero, BorderLayout.CENTER);
		_barcos = new PanelBarcos ();
		_panel.add(_barcos, BorderLayout.SOUTH);
	}
	
	/**
	 * Método que crea un escenario nuevo con la representacion mediante botones del tablero
	 * 
	 * @param e Evento que le llega a la ventana al que se debe responder creando 
	 * el escenario con ayuda de los datos que guarda
	 */
	private void crearEscenario(Evento e) {
		componVentana((EventoNuevoEscenario) e);
		_menu.habilitaGuardado();
		this.setContentPane(_panel);
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * Metodo que refleja los cambios en el tablero despues de abrir un fichero XML existente
	 * @param e
	 */
	private void abrir(Evento e) {
		EventoAbrir evento = (EventoAbrir) e; 
		componVentana (evento);
		this.setContentPane(_panel);
		_tablero.ponBarcosFichero(evento.getBarcos());
		_barcos.actualizaBarcosDisponibles(evento.getNumPortaaviones(), evento.getNumAcorazados(), evento.getNumFragatas(), evento.getNumSubmarinos());
		_menu.habilitaGuardado();
		this.repaint();
		this.revalidate();		
	}
	
	/**
	 * Metodo que "limpia" la ventana, eliminando todos los componentes que tenga
	 */
	private void limpiaVentana() {
		if (_tablero != null)
			_panel.remove(_tablero);
		if (_barcos != null)
			_panel.remove(_barcos);
		setContentPane(_fondoInicial);
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * Método que coloca un barco en el escenario
	 * 
	 * @param e Evento que le llega a la ventana al que se debe responder colocando
	 * el barco con ayuda de los datos que guarda
	 */
	private void colocaBarco(Evento e) {
		EventoColocarBarco evento = (EventoColocarBarco) e;
		_tablero.colocaBarco(evento.getPosicionesBarco());
		_barcos.actualizaBarcosDisponibles(evento.getPortaaviones(), evento.getAcorazados(), evento.getFragatas(), evento.getSubmarinos());
	}
	
	/**
	 * Método que quita un barco del escenario
	 * 
	 * @param e Evento que le llega a la ventana al que se debe responder quitando
	 * un barco del escenario con ayuda de los datos que guarda
	 */
	private void quitaBarco(Evento e) {
		EventoQuitaBarco evento = (EventoQuitaBarco) e;
		_tablero.quitaBarco(evento.getPosicionesBarco());
		_barcos.actualizaBarcosDisponibles(evento.getPortaaviones(), evento.getAcorazados(), evento.getFragatas(), evento.getSubmarinos());
	}
	
	
	
	
	/**
	 * Metodo que recibe los avisos del modelo, después de haber cambiado
	 * 
	 * @param o Objeto que se observa, en nuestro caso lo que hace de modelo es la Partida
	 * @param arg Evento que se recibe (Nuevo Escenario, Colocar barco etc.) 
	 */
	public void update(Observable o, Object arg) {
		Evento e= (Evento)arg;
		recibeAcciones(e);
		recibeErrores(e);
	}
	
	/**
	 * Metodo que diferencia los eventos pertenecientes a acciones del usuario
	 * @param e Evento que se recibe
	 */
	private void recibeAcciones(Evento e) {
		if (e.getTipo()==TipoEvento.NUEVO){
			crearEscenario(e);
		}
		if (e.getTipo()==TipoEvento.COLOCAR){
			colocaBarco(e);
		}
		if (e.getTipo()==TipoEvento.QUITAR){
			quitaBarco(e);
		}
		if (e.getTipo()==TipoEvento.ABRIR){
			abrir(e);
		}
	}
	
	/**
	 * Metodo que diferencia los distintos tipos de erroes posibles
	 * @param e Evento que se recibe
	 */
	private void recibeErrores(Evento e) {
		if (e.getTipo()==TipoEvento.ERRORBARCO){
			JOptionPane.showMessageDialog(null, 
				"ERROR: El fichero XML de origen contiene errores en algun BARCO (colocacion, tipo, cantidad...)", 
				"Archivo XML origen Incorrecto", JOptionPane.ERROR_MESSAGE);
		}
		if (e.getTipo()==TipoEvento.ERRORPOSICION){
			JOptionPane.showMessageDialog(null, 
				"ERROR: El fichero XML de origen contiene errores en alguna POSICION", 
				"Archivo XML origen Incorrecto", JOptionPane.ERROR_MESSAGE);
		}
		if (e.getTipo()==TipoEvento.ERRORTABLERO){
			JOptionPane.showMessageDialog(null, 
				"ERROR: El fichero XML de origen contiene errores en la definicion del TABLERO", 
				"Archivo XML origen Incorrecto", JOptionPane.ERROR_MESSAGE);
		}
		if (e.getTipo()==TipoEvento.ERRORSINTAXIS){
			JOptionPane.showMessageDialog(null, 
				"ERROR: El fichero XML de origen contiene errores sintácticos", 
				"Archivo XML origen Incorrecto", JOptionPane.ERROR_MESSAGE);
		}
	}
}
