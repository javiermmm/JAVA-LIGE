//PanelTablero.java

package lige.grupo03.pr4.GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que define el panel que contiene el tablero sobre el que se jugará
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.1
 * @see VentanaPrincipal
 */
public class PanelTablero extends PanelSobreTableros{
	
	//------------------------
	//ATRIBUTOS
	//------------------------
	private VentanaPrincipal _ventanaPadre; 
	private static final long serialVersionUID = 1L;
	private int _numDisparos;
	//------------------------

	/**
	 * Constructor parametrizado
	 * 
	 * @param ancho Ancho del Tablero.
	 * @param alto Alto del Tablero.
	 * @param padre Ventana (Frame) que se considera "padre" del menú (lo contiene)
	 */
	public PanelTablero(int ancho, int alto, VentanaPrincipal padre) {
		super(ancho, alto);
		_ventanaPadre = padre;
		this.setLayout(new GridLayout (alto+1, ancho+1, 5, 5));
		_numDisparos = 0;
		Dimension medidas = _ventanaPadre.getSize();
		this.setPreferredSize(new Dimension ((int)(medidas.getWidth()/2.5),(int)(medidas.getHeight()/2.5)));
	}
	
	/**
	 * Metodo para devolver un boton con unas caracteristicas propias
	 * 
	 * @param i coordenada para la fila
	 * @param j coordenada para la columna
	 */
	protected BotonTablero getBoton(int i, int j){
		BotonTablero boton = new BotonTablero("", i, j);
		boton.setEnabled(true);
		boton.setToolTipText("Estado de la coordenada (" + i + "," + j + ")" );
	    boton.addActionListener(new OyenteBoton());
	    return boton;
	}

	/**
	 * Clase que implementa el metodo ActionPerformed que da respuesta a los eventos
	 * 
	 * @author Jessica Martin & Javier Martin
	 * @version 1.0
	 * @see PanelTablero
	 */
	private class OyenteBoton implements ActionListener {
		
		/**
		 * Método que recoge la funcionalidad que hay que llevar a cabo como resultado 
		 * de un evento, (en este caso de los BotonTablero)
		 * 
		 * @param e Evento desencadenado al que se da respuesta
		 */
		public void actionPerformed(ActionEvent e) {
			BotonTablero boton = (BotonTablero) e.getSource();
			_ventanaPadre.escribe("Pulsaste el boton: (" + boton.getCoordenadaX() + "," + boton.getCoordenadaY() + ")");
			boton.setBackground(Color.ORANGE);
			boton.setEnabled(false);
			_numDisparos++;
			_ventanaPadre.actualizaNumDisparos(_numDisparos);
		}
	}
}
