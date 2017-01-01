//Principal.java

package lige.grupo03.pr6.editor;

import lige.grupo03.pr6.editor.controlador.Controlador;
import lige.grupo03.pr6.editor.modelo.Editor;
import lige.grupo03.pr6.editor.vista.VentanaPrincipal;

/**
 * Punto de entrada al editor de escenarios
 * 
 * @author Jessica 	Martin & Javier Martin
 * @version 1.0
 * @see Editor
 * @see Controlador
 * @see VentanaPrincipal
 */
public class Principal {

	/**
	 * @param args Argumentos de la aplicacion
	 */
	public static void main(String[] args) {

		Editor modelo = new Editor();
		Controlador controlador = new Controlador(modelo);
		VentanaPrincipal vista = new VentanaPrincipal(controlador);
		modelo.addObserver (vista);
	}

}
