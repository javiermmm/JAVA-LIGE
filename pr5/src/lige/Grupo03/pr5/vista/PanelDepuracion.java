//PanelDepuracion.java

package lige.Grupo03.pr5.vista;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;


/**
 * Clase que define el panel dedicado a depuracion 
 * (tableroDebug y textArea de eventos) de la parte derecha de la ventana
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see VentanaPrincipal
 * @see PanelDebug
 */
public class PanelDepuracion extends JPanel {

	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	private PanelDebug _panelDebug;
	private VentanaPrincipal _ventanaPadre;
	//------------------------
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param ancho Ancho del Tablero de Debug.
	 * @param alto Alto del Tablero de Debug.
	 * @param ventana Ventana (Frame) que se considera "padre" del menú (lo contiene)
	 * @param modoJuego Valor que identifica si se eligió jugar en modo debug (2) o normal (1)
	 */
	public PanelDepuracion (int ancho, int alto, VentanaPrincipal ventana, boolean modoDebug) {
		super();
		_ventanaPadre = ventana;
		if (modoDebug){
			this.setLayout(new GridLayout (2,1,10,10));
			_panelDebug = new PanelDebug(ancho, alto);
			this.add(_panelDebug);
			this.add(new PanelFondo("tramposo.jpg"));
		} else {
			this.setLayout(new GridLayout (1,1,10,10));
			this.add(new PanelFondo("tramposo2.jpg"));
		}
		
		Dimension medidas = _ventanaPadre.getSize();
		this.setPreferredSize(new Dimension ((int)(medidas.getWidth()/2.5),(int)(medidas.getHeight()/2.5)));
	}

	/**
	 * Metodo que muestra los barquitos de colores si se ha elegido el modo de Debug
	 * 
	 * @param tableroInicial Array de caracteres con los barcos colocados
	 * @param ancho Entero con el ancho del tablero
	 * @param alto Entero con el alto del tablero
	 */
	public void ponBarquitos(char[][] tableroInicial, int ancho, int alto) {
		if (_panelDebug != null)
			_panelDebug.pintaBarquitos(tableroInicial, ancho, alto);
	}
}
