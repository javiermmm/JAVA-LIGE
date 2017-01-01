//VentanaPrincipal.java

package lige.Grupo03.pr5.vista;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lige.Grupo03.pr5.controlador.Controlador;
import lige.Grupo03.pr5.modelo.Constantes.Casilla;
import lige.Grupo03.pr5.modelo.Constantes.TipoEvento;
import lige.Grupo03.pr5.modelo.Posicion;
import lige.Grupo03.pr5.vista.Eventos.Evento;
import lige.Grupo03.pr5.vista.Eventos.EventoDisparo;
import lige.Grupo03.pr5.vista.Eventos.EventoNuevaPartida;
import lige.Grupo03.pr5.vista.Eventos.EventoTodosBarcosHundidos;

/**
 * Clase que define la Ventana Principal que interactuará con el usuario
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see MenuBarcos
 * @see PanelEstadoPartida
 * @see PanelTablero
 * @see PanelDepuracion
 * @see MenuBarcos
 * @see PanelFondo
 */
public class VentanaPrincipal extends JFrame implements Observer {

	//-------------------------------------------------------------
	//ATRIBUTOS
	//-------------------------------------------------------------
	private Controlador _controlador;
	private boolean _modoDebug;
	private int _numDisparos;
	private static final long serialVersionUID = 1L;
	private Container _panel;
	private MenuBarcos _menu;
	private PanelEstadoPartida _panelEstado;
	private PanelTablero _panelTablero;
	private PanelDepuracion _panelDepuracion;
	private PanelFondo _fondoInicial;
	private PanelOpcional _panelOpcional;
	//-------------------------------------------------------------
	
	/**
	 * Constructor predeterminado
	 * @param controlador Controlador de la aplicación de los barquitos
	 * @param modoDebug Booleano que indica si se ha elegido el modo de debug (true) o no (false)
	 * @param numDisparos Numero de disparos realizados en la partida 
	 * @param sonicos Numero de disparos sonicos restantes
	 * @param dispersiones Numero de disparos de dispersion restantes.
	 */
	public VentanaPrincipal (Controlador controlador, boolean modoDebug, int numDisparos, int dispersiones, int sonicos) {
		super ("Hundir la Flota");
		_controlador = controlador;
		_modoDebug = modoDebug;
		_numDisparos = numDisparos; 
		//Preparamos la ventana
		_panel = this.getContentPane();
		_panel.setLayout(new BorderLayout (20,20));
		//IMAGEN
		_fondoInicial = new PanelFondo("Barcos.jpeg");
		setContentPane(_fondoInicial);
		
		//MENU
		_menu = new MenuBarcos(_controlador);
		setJMenuBar (_menu);
		
		//"Configuramos" la ventana
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //Obtiene el tamaño de la pantalla.
		this.setSize((int) d.getWidth()-200 , (int) d.getHeight()-200); //Establece el tanaño de la ventana (frame) a un tamaño basado en el tamaño de la ventana.
		this.setMinimumSize(getSize());
		this.setMaximumSize(getSize());
		this.setLocation(0,0); //Establece la ventana en la esquina superior ixquierda.
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Terminar la aplicación al cerrar la ventana.
	}	
	
	/**
	 * Metodo que actualiza la cantidad de disparos efectuados
	 * 
	 * @param numDisparos Numero de disparos efectuados hasta el momento en la partida
	 */
	private void actualizaNumDisparos(int disparos) {
		_panelEstado.actualiza(disparos);
	}
	
	/**
	 * Metodo que actualiza la cantidad de disparos de dispersion restantes
	 * 
	 * @param dispersiones Numero de disparos de dispersion restantes
	 */
	private void actualizaDisparosDispersion(int dispersiones) {
		_panelEstado.actualizaDispersiones (dispersiones);	
	}
	
	/**
	 * Metodo que actualiza la cantidad de disparos sonicos restantes
	 * 
	 * @param sonicos Numero de disparos sonicos restantes
	 */
	private void actualizaDisparosSonicos(int sonicos) {
		_panelEstado.actualizaSonicos(sonicos);
	}
	
	/**
	 * Funcion que actualiza los valores de los distintos tipos de disparo y de los 
	 * disparos realizados en el panel que muestra estos datos
	 * 
	 * @param numDisparos Numero de disparos realizados hasta el momento
	 * @param numDispersiones Numero de disparos de dispersion restantes
	 * @param numSonicos Numero de disparos sonicos restantes
	 */
	private void actualizaEstadoPartida(int numDisparos, int numDispersiones, int numSonicos) {
		actualizaNumDisparos(numDisparos);
		actualizaDisparosDispersion(numDispersiones);
		actualizaDisparosSonicos(numSonicos);
	}
	
	/**
	 * Funcion que pone los paneles en la ventana distribuyéndolos
	 * respetando ciertas indicaciones.
	 * 
	 * @param e Evento que provoca la ejecucion de este metodo (EventoNuevaPartida)
	 */
	private void nuevaPartida(Evento e) {
		
		terminaPartida();
		EventoNuevaPartida evento=(EventoNuevaPartida)e;
		_modoDebug = evento.getTipoJuego();
		//TABLERO
		_panelTablero = new PanelTablero (evento.getAncho(), evento.getAlto(), this, _controlador, _numDisparos);
		_panel.add(_panelTablero, BorderLayout.WEST);
		//PANEL DE ESTADO DE LA PARTIDA
		_panelEstado = new PanelEstadoPartida ();
		_panel.add(_panelEstado, BorderLayout.SOUTH);
		//PANEL DE DEPURACION
		_panelDepuracion = new PanelDepuracion(evento.getAncho(), evento.getAlto(), this, _modoDebug);
		_panel.add(_panelDepuracion, BorderLayout.EAST);
		_panelDepuracion.ponBarquitos(evento.getTableroInicial(), evento.getAncho(), evento.getAlto());
		//Parte opcional 
		_panelOpcional = new PanelOpcional(_controlador);
		_panelOpcional.setSize(200, 100);
		_panel.add(_panelOpcional,BorderLayout.CENTER);
		setContentPane(_panel);
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * Funcion que cambia los botones del tablero que deben cambiar cuando se realiza un
	 * disparo (ya sea normal, sonico o de dispersion) 
	 * 
	 * @param e Evento que provoca la ejecucion de este metodo (EventoNuevaPartida)
	 */
	private void disparo(Evento e) {
		EventoDisparo evento = (EventoDisparo)e;
		Posicion pos = evento.getDisparo();
		if (evento.isDispersion()) {
			for (int i=0; i<evento.getPosicionesAfectadas().length; i++){
				reflejaAguaYTocado(evento.getNumTocados(), evento.getNumIntactos(), evento.getPosTablero(i), evento.getPosicionesAfectadas()[i]);
				if (evento.getPosTablero(i) == Casilla.HUNDIDO){
					reflejaHundido(evento, i);
				}
			}
			if (evento.getNumDispersiones() == 0)
				_panelOpcional.deshabilitaDispersiones();
		}
		else if (evento.isSonico()){
				for (int j=0; j<evento.getPosicionesAfectadas().length; j++){
					reflejaHundido(evento, j);
				}
			if (evento.getNumSonicos() == 0)
				_panelOpcional.deshabilitaSonicos();
		}
		else {
			reflejaAguaYTocado(evento.getNumTocados(), evento.getNumIntactos(), evento.getTablero()[pos.getX()][pos.getY()], pos);
			if (evento.getTablero()[pos.getX()][pos.getY()] == Casilla.HUNDIDO){
				for (int i=0; i<evento.getPosicionesAfectadas().length; i++){
					reflejaHundido(evento, i);
				}
			}
		}
		actualizaEstadoPartida(evento.getNumDisparos(), evento.getNumDispersiones(), evento.getNumSonicos());
	}

	/**
	 *  Metodo que se encarga de realizar los cambios en la vista, cuando se hace agua o tocado
	 *  en un disparo
	 *  
	 *  @param tocados Numero de barcos tocados
	 *  @param intactos Numero de barcos intactos
	 *  @param casilla Estado de una posicion del tablero de tipo Casilla
	 *  @param pos Posicion que se actualiza
	 */
	private void reflejaAguaYTocado(int tocados, int intactos, Casilla casilla, Posicion pos){
		if (casilla == Casilla.AGUA){
			_panelTablero.ponAgua(pos);
		}
		if (casilla == Casilla.TOCADO){
			_panelTablero.ponTocado(pos);
			_panelEstado.toca(intactos, tocados);
		}
	}
	
	/**
	 *  Metodo que se encarga de realizar los cambios en la vista, cuando se hunde un barco
	 *  
	 *  @param evento Evento de disparo
	 *  @param indice Entero para acceder al array que guarda las posiciones afectadas.
	 */
	private void reflejaHundido(EventoDisparo evento, int indice){
		_panelTablero.ponHundido(evento.getPosicionesAfectadas()[indice]);
		_panelEstado.hunde(evento.getNumIntactos(), evento.getNumTocados(), evento.getNumHundidos());
	}
	
	/**
	 *  Metodo que se encarga de notificar el fin de la partida, mostrando la puntuacion,
	 *  borrando todos los elementos del panel de juego y reestableciendo el panel con el 
	 *  fondo inicial con imagen
	 *  
	 *  @param e Evento que provoca la ejecucion de este metodo (EventoNuevaPartida)
	 */
	private void finPartida(Evento e) {
		EventoTodosBarcosHundidos evento = (EventoTodosBarcosHundidos)e;
		JOptionPane.showMessageDialog(null, evento.getMensajeFin(), "PARTIDA TERMINADA", JOptionPane.INFORMATION_MESSAGE);
		terminaPartida();
	}

	/**
	 * Metodo que "limpia" la ventana, eliminando todos los componentes que tenga
	 */
	private void terminaPartida() {
		if (_panelDepuracion != null)
			_panel.remove(_panelDepuracion);
		if (_panelTablero != null)
			_panel.remove(_panelTablero);
		if (_panelEstado != null)
			_panel.remove(_panelEstado);
		if (_panelOpcional != null)
			_panel.remove(_panelOpcional);
		setContentPane(_fondoInicial);
		this.repaint();
		this.revalidate();
	}

	/**
	 * Metodo que recibe los avisos del modelo, después de haber cambiado
	 * 
	 * @param o Objeto que se observa, en nuestro caso lo que hace de modelo es la Partida
	 * @param arg Evento que se recibe (Nueva Partida, disparo, etc.) 
	 */
	public void update(Observable o, Object arg) {
		Evento e= (Evento)arg;
		if (e.getTipo()==TipoEvento.NUEVAPARTIDA){
			nuevaPartida(e);
		}
		else if (e.getTipo()==TipoEvento.TERMINAPARTIDA){
			terminaPartida();
		}
		else if (e.getTipo()==TipoEvento.DISPARO){
			disparo(e);
		}
		else if (e.getTipo()==TipoEvento.BARCOSHUNDIDOS){
			finPartida(e);
		}	
	}
}
