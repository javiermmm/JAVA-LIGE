//PanelDepuracion.java

package lige.grupo03.pr4.GUI;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


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
	private JTextArea _pruebas;
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
	public PanelDepuracion (int ancho, int alto, VentanaPrincipal ventana, int modoJuego) {
		super();
		if (modoJuego==2)
			this.setLayout(new GridLayout (2,1,10,10));
		else
			this.setLayout(new GridLayout (1,1,10,10));
		_ventanaPadre = ventana;
		//PANEL DEBUG
		if (modoJuego ==2)
			_panelDebug = new PanelDebug(ancho, alto);
		//COMPONER PANEL DEPURACION
		if (modoJuego==2)
			this.add(_panelDebug);
		this.add(creaTextAreaDebug());
		_pruebas.setCaretPosition(_pruebas.getDocument().getLength());
		Dimension medidas = _ventanaPadre.getSize();
		this.setPreferredSize(new Dimension ((int)(medidas.getWidth()/2.5),(int)(medidas.getHeight()/2.5)));
	}

	/**
	 * Metodo para escribir una linea en el textArea de depuracion y terminar con salto de linea
	 * 
	 * @param mensaje Texto que se quiere escribir
	 */
	public void escribeLineaPruebas(String mensaje) {
		_pruebas.append(mensaje + '\n');
	}
	
	/**
	 * Metodo para escribir una linea en el textArea de depuracion sin cambiar el puntero de escritura
	 * 
	 * @param mensaje Texto que se quiere escribir
	 */
	public void escribePruebas(String mensaje) {
		_pruebas.append(mensaje);
	}
	
	/**
	 * Metodo privado que encapsula el código para crear un TextArea personalizado
	 * con scrollBar vertical automática.
	 * 
	 * @return El objeto JScrollPane con sus propiedades ya establercidas
	 */
	private JScrollPane creaTextAreaDebug(){
		//PANEL PRUEBAS
		_pruebas = new JTextArea("Aquí aparecerán las pruebas de los botones (eventos).\n\n", 10, 30);
		_pruebas.setEditable(false);
		
		//AÑADIR SCROLL AL TEXTAREA
		JScrollPane scrollPruebas = new JScrollPane();
		scrollPruebas.setViewportView(_pruebas);
		scrollPruebas.setToolTipText("Cuadro de seguimiento de los eventos");
		return scrollPruebas;
	}
}
