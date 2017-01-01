//Controlador.java

package lige.grupo03.pr6.editor.controlador;

import java.io.File;

import lige.grupo03.pr6.editor.modelo.Constantes.Direccion;
import lige.grupo03.pr6.editor.modelo.Constantes.TipoBarco;
import lige.grupo03.pr6.editor.modelo.Editor;
import lige.grupo03.pr6.editor.modelo.Posicion;

/**
 * Punto de entrada a la aplicacion de los barquitos
 * 
 * @author Jessica 	Martin & Javier Martin
 * @version 1.0
 * @see Editor
 */
public class Controlador {
	
	//---------------------
	//ATRIBUTOS
	//---------------------
	private Editor _modelo;
	//---------------------
	
	/**
	 * Constructor del controlador de la aplicacion
	 * @param editor Objeto Editor que representa el modelo de la aplicacion
	 */
	public Controlador(Editor editor) {
		_modelo = editor;
	}
	
	/**
	 * Método que le pasa al modelo la orden de crear un nuevo escenario
	 * @param ancho Ancho del tablero
	 * @param alto Alto del tablero
	 */
	public void creaEscenario(int ancho, int alto) {
		_modelo.creaEscenario(ancho, alto);
		
	}

	/**
	 * Método que le pasa al modelo la orden de colocar un barco en el escenario.
	 * Si el resultado es verdadero significa que se puede colocar y de hecho, SE HA COLOCADO.
	 * @param barco Tipo de barco que se quiere colocar (Portaaviones, Acorazado, Fragata o Submarino)
	 * @param pos Posicion inicial en la que empezará el barco.
	 * @param direccion Direccion hacia la que se quiere colocar el barco (Norte, Sur, Este y Oeste)
	 * @return Valor booleano que indica si se puede colocar el barco (true) o no (false)
	 */
	public boolean sePuedeColocar(TipoBarco barco, Posicion pos, Direccion direccion) {
		return _modelo.sePuedeColocar(barco, pos, direccion);
	}

	/**
	 * Metodo que indica si hay suficientes barcos como para colocar otro
	 * @param barco Tipo de barco que se quiere colocar (Portaaviones, Acorazado, Fragata o Submarino)
	 * @return Valor booleano que indica si hay suficientes barcos como para colocar otro (true) o no (false)
	 */
	public boolean haySuficientes(TipoBarco barco) {
		return _modelo.haySuficientes(barco);
	}

	/**
	 * Metodo que que le pasa la orden al modelo de quitar un barco del escenario
	 * @param pos Posicion que está ocupada por el barco que se quiere quitar (cualquiera de las que ocupa)
	 */
	public void quitaBarco(Posicion pos) {
		_modelo.quitaBarco(pos);
		
	}

	/**
	 * Metodo que indica si se han colocado todos los barcos
	 * @return Valor booleano que indica si se han colocado todos los barcos (true) o no (false)
	 */
	public boolean barcosGastados() {
		return _modelo.barcosGastados();
	}

	/**
	 * Método que le pasa al modelo la orden de abrir un fichero XML
	 * @param archivo Archivo con los datos del escenario en XML
	 */
	public void abrirArchivo(File archivo) {
		_modelo.abrirArchivo(archivo);
	}

	/**
	 * Método que le pasa al modelo la orden de crear un nuevo escenario
	 */
	public void guardar() {
		_modelo.guardar();
	}

	/**
	 * Método que le pasa al modelo la orden de guardar el escenario actual en un fichero XML
	 * @param archivo Archivo destino para los datos del escenario en XML
	 */
	public void guardarComo(File archivo) {
		_modelo.guardarComo(archivo);
	}
}
