//PanelTablero.java

package lige.grupo03.pr6.editor.vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import lige.grupo03.pr6.editor.controlador.Controlador;
import lige.grupo03.pr6.editor.modelo.Barco;
import lige.grupo03.pr6.editor.modelo.Posicion;

/**
 * Clase que define el panel con el tablero para colocar los barcos
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Botonablero
 * @see Controlador
 * @see OyenteBoton
 */
public class PanelTablero extends JPanel {

	//----------------------------------------------
	//ATRIBUTOS
	//----------------------------------------------
	private static final long serialVersionUID = 1L;
	private OyenteBoton _oyente;
	private Controlador _controlador;
	private BotonTablero[][] _botones;
	//----------------------------------------------

	/**
	 * Constructor parametrizado
	 * 
	 * @param ancho Ancho del tablero
	 * @paramo alto Alto del tablero
	 * @param controlador Controlador de la aplicacion [Patron MVC]
	 */
	public PanelTablero (int ancho, int alto, Controlador controlador) {
		super();
		_controlador = controlador;
		_oyente = new OyenteBoton();
		_botones = new BotonTablero[alto][ancho];
		this.setLayout(new GridLayout (alto+1, ancho+1, 5, 5));
		this.add(new JLabel());
		for (int k=1; k<=ancho; ++k){
			JLabel numColumna = new JLabel ("" + k);
			numColumna.setToolTipText("Columna " + k);
			numColumna.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(numColumna);
		}
		for (int i=1; i<=alto; ++i){
			JLabel numFila = new JLabel("" + i);
			numFila.setToolTipText("Fila " + i);
			numFila.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(numFila);
			for (int j=1; j<=ancho; ++j) {
				BotonTablero boton = new BotonTablero("", i, j);
				boton.setToolTipText("Boton que representa la coordenada (" + boton.getCoordenadaX() + ", " + boton.getCoordenadaY() + ")");
				boton.addActionListener(_oyente);
				_botones[i-1][j-1] = boton;
				this.add(boton);
			}
		}
	}
	
	/**
	 * Método que coloca un barco en el escenario, asignándole posiciones válidas
	 * 
	 * @param posicionesOcupadas Array de posiciones ocupadas por el barco que se esta colocando
	 */
	public void colocaBarco(Posicion[] posicionesOcupadas) {
	
		Color color = ponColorBarco(posicionesOcupadas.length);
		for (int i=0; i<posicionesOcupadas.length; i++){
			_botones[posicionesOcupadas[i].getX()][posicionesOcupadas[i].getY()].setBackground(color);
		}
	}
	
	/**
	 * Método que asigna un color a cada tipo de barco para representarlo en el escenario
	 * 
	 * @param length Longitud de un barco. La longitud identifica el tipo de barco que es.
	 * @return Color que se asigna al tipo de barco cuya longitud se recibe.
	 */
	private Color ponColorBarco(int length) {
		
		if (length == 4)
			return Color.BLACK;
		if (length == 3)
			return Color.GREEN;
		if (length == 2)
			return Color.BLUE;
		else
			return Color.YELLOW;
	}
	
	/**
	 * Método que quita un barco del escenario
	 * 
	 * @param posicionesBarco Array de posiciones ocupadas por el barco que se esta quitando
	 */
	public void quitaBarco(Posicion[] posicionesBarco) {
		for (int i=0; i<posicionesBarco.length; i++)
			_botones[posicionesBarco[i].getX()][posicionesBarco[i].getY()].setBackground(null);
	}
	
	/**
	 * Metodo que coloca los barcos en el escenario al abrir un fichero XML
	 * @param barcos Barcos que hay que colocar en el escenario
	 */
	public void ponBarcosFichero(ArrayList<Barco> barcos) {
		Iterator<Barco> iterador = barcos.iterator();
		while (iterador.hasNext()){
			Barco b = iterador.next();
			colocaBarco (b.getPosicionesOcupadas());
		}	
	}



	/**
	 * Clase que define el metodo actionPerformed en respuesta al evento que desencadenan los items del menu
	 * 
	 * @author Jessica Martin & Javier Martin
	 * @version 1.0
	 * @see MenuBarcos
	 */
	private class OyenteBoton implements ActionListener {
		
		/**
		 * Método que recoge la funcionalidad que hay que llevar a cabo como resultado 
		 * de un evento, (en este caso de los JMenuItems)
		 * 
		 * @param e Evento desencadenado al que se da respuesta
		 */
		public void actionPerformed(ActionEvent e) {
			BotonTablero boton = (BotonTablero) e.getSource();
			Posicion pos = boton.getPosicion();
			if (boton.tieneColor()) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que quieres quitar este barco del tablero?", "Quitar barco", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)
						== JOptionPane.OK_OPTION) {
					_controlador.quitaBarco(pos);
				}
			}
			else {
				PanelColocaBarco coloca = new PanelColocaBarco();
				if (_controlador.barcosGastados())
					JOptionPane.showMessageDialog(null, "ERROR: no hay más barcos disponibles. Quita un barco para poner otro", "BARCOS AGOTADOS", JOptionPane.ERROR_MESSAGE);
				else {
					if(JOptionPane.showConfirmDialog(coloca, coloca, "Coloca el Barco", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)
							== JOptionPane.OK_OPTION) {
						try {
							if (_controlador.haySuficientes(coloca.getBarco())){
								if (!_controlador.sePuedeColocar(coloca.getBarco(), pos, coloca.getDireccion()))
									JOptionPane.showMessageDialog(null, coloca.MensajeError(), "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
							}
							else
								JOptionPane.showMessageDialog(null, "ERROR: no hay más barcos disponibles de ese tipo", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
						}catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, coloca.MensajeError(), "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
	}
}
