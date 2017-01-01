//VentanaPrincipal.java

package lige.grupo03.pr4.GUI;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Clase que define el menu de opciones del juego de los barcos
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
public class VentanaPrincipal extends JFrame {

	//-------------------------------------------------------------
	//ATRIBUTOS
	//-------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private Container _panel;
	private MenuBarcos _menu;
	private PanelEstadoPartida _panelEstado;
	private PanelTablero _panelTablero;
	private PanelDepuracion _panelDepuracion;
	private PanelFondo _fondoInicial;
//	private PanelFondo _imagen;
//	Este atributo está por si en un momento dado, quisieramos añadir otra foto
//  en el centro de la ventana, ya que en esta practica hemos decidido no ponerla. 
//  De la misma manera se encuentran comentadas las lineas
//  que hacen referencia a este atributo a lo largo del código.	
	//-------------------------------------------------------------
	
	/**
	 * Constructor predeterminado
	 */
	public VentanaPrincipal () {
		
		//Preparamos la ventana
		super ("Hundir la Flota");
		_panel = this.getContentPane();
		_panel.setLayout(new BorderLayout (20,20));
		//IMAGEN
		_fondoInicial = new PanelFondo("Barcos.jpeg");
		setContentPane(_fondoInicial);
		//MENU
		_menu = new MenuBarcos(this);
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
	 * Funcion que pone los paneles en la ventana distribuyéndolos
	 * respetando ciertas indicaciones.
	 * 
	 * @param ancho Ancho del tablero establecido por el jugador
	 * @param alto Alto del tablero establecido por el jugador
	 * @param modoJuego Entero que representa el modo de juego establecido por el jugador 
	 */
	public void ponPaneles(int ancho, int alto, int modoJuego) {
		
		//TABLERO
		_panelTablero = new PanelTablero (ancho, alto, this);
		_panel.add(_panelTablero, BorderLayout.WEST);
		//PANEL DE ESTADO DE LA PARTIDA
		_panelEstado = new PanelEstadoPartida ();
		_panel.add(_panelEstado, BorderLayout.SOUTH);
		//PANEL DE DEPURACION
		_panelDepuracion = new PanelDepuracion(ancho, alto, this, modoJuego);
		_panel.add(_panelDepuracion, BorderLayout.EAST);
		//IMAGEN
//		_imagen = new PanelFondo("Barcos2.jpeg");
//		_panel.add(_imagen, BorderLayout.CENTER);
//		Mejoras Futuras [VER SECCION DE ATRIBUTOS]	
		setContentPane(_panel);
		this.repaint();
		this.revalidate();
	}	

	/**
	 * Metodo que escribeun mensaje determinado en el textArea de depuracion
	 * 
	 * @param mensaje Cadena de texto que se va a escribir 
	 */
	public void escribe(String mensaje) {
		if (_panelDepuracion != null)
			_panelDepuracion.escribeLineaPruebas(mensaje);
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * Metodo que "limpia" la ventana, eliminando todos los componentes que tenga
	 */
	public void destruyePaneles() {
		if (_panelDepuracion != null)
			_panel.remove(_panelDepuracion);
		if (_panelTablero != null)
			_panel.remove(_panelTablero);
		if (_panelEstado != null)
			_panel.remove(_panelEstado);
//		if (_imagen != null)
//			_panel.remove(_imagen);
//		Mejoras Futuras [VER SECCION DE ATRIBUTOS]
		setContentPane(_fondoInicial);
		this.repaint();
		this.revalidate();
	}

	/**
	 * Metodo que actualiza la cantidad de disparos efectuados
	 * 
	 * @param numDisparos Numero de disparos efectuadops hasta el momento en la partida
	 */
	public void actualizaNumDisparos(int numDisparos) {
		_panelEstado.actualiza(numDisparos);
	}
	
	//************
	//METODO MAIN
	//************
	public static void main(String args[]){
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.escribe("Esto no hace nada porque _panelDepuracion no existe, pero me ahorro el warning por no usar la variable ventana");
	}
}
