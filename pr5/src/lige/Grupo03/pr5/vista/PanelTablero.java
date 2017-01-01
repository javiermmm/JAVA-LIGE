//PanelTablero.java

package lige.Grupo03.pr5.vista;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lige.Grupo03.pr5.controlador.Controlador;
import lige.Grupo03.pr5.modelo.Posicion;

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
	private Controlador _controlador;
	private VentanaPrincipal _ventanaPadre; 
	private OyenteBoton _oyente;
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor parametrizado
	 * 
	 * @param ancho Ancho del Tablero.
	 * @param alto Alto del Tablero.
	 * @param padre Ventana (Frame) que se considera "padre" del menú (lo contiene)
	 * @param numDisparos 
	 * @param sonicos 
	 * @param dispersiones 
	 */
	public PanelTablero(int ancho, int alto, VentanaPrincipal padre, Controlador controlador, int numDisparos) {

		super(ancho, alto);
		_controlador = controlador;
		_ventanaPadre = padre;
		this.setLayout(new GridLayout (alto+1, ancho+1, 5, 5));
		Dimension medidas = _ventanaPadre.getSize();
		this.setPreferredSize(new Dimension ((int)(medidas.getWidth()/2),(int)(medidas.getHeight()/2)));
	}
	
	/**
	 * Metodo que crea el oyente para los botones del tablero 
	 */
	public void getOyente(){
		_oyente = new OyenteBoton();
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
	    boton.addActionListener(_oyente);
	    return boton;
	}
	
	/**
	 * Metodo para establecer las propiedades necesarias cuando el disparo es agua
	 * 
	 * @param disparo Posicion objetivo en la que se ha disparado
	 */
	public void ponAgua(Posicion disparo) {
		_botones[disparo.getX()][disparo.getY()].setText("a");
		_botones[disparo.getX()][disparo.getY()].setBackground(Color.CYAN);
		_botones[disparo.getX()][disparo.getY()].setEnabled(false);
	}

	/**
	 * Metodo para establecer las propiedades necesarias cuando el disparo es tocado
	 * 
	 * @param disparo Posicion objetivo en la que se ha disparado
	 */
	public void ponTocado(Posicion disparo) {
		_botones[disparo.getX()][disparo.getY()].setText("X");
		_botones[disparo.getX()][disparo.getY()].setBackground(Color.ORANGE);
		_botones[disparo.getX()][disparo.getY()].setEnabled(false);
	}

	/**
	 * Metodo para establecer las propiedades necesarias cuando el disparo es hundido
	 * 
	 * @param disparo Posicion objetivo en la que se ha disparado
	 */
	public void ponHundido(Posicion disparo) {
		_botones[disparo.getX()][disparo.getY()].setText("H");
		_botones[disparo.getX()][disparo.getY()].setBackground(Color.RED);
		_botones[disparo.getX()][disparo.getY()].setEnabled(false);
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
			Posicion disparo = new Posicion (boton.getCoordenadaX()-1, boton.getCoordenadaY()-1);
			_controlador.dispara(disparo);
		}
	}
}
