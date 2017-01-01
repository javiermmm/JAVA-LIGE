//Constantes.java

package lige.grupo03.pr6.editor.modelo;

/**
 * Clase para recoger todos los enumerados y constantes necesarios
 * @author Javier Martin & Jessica Martin
 * @version 1.0
 */
public class Constantes {

	/**
	 * Enumerado para el tipo de Barco del Hundir la Flota
	 */
	public enum TipoBarco {PORTAAVIONES, ACORAZADO, FRAGATA, SUBMARINO}
	
	/**
	 * Enumerado para los posibles estados de una casilla
	 */
	public enum Casilla {TAPADO, TOCADO, HUNDIDO, AGUA}
	
	/**
	 * Enumerado para las cuatro direcciones posibles
	 */
	public enum Direccion {NORTE, SUR, ESTE, OESTE}
	
	/**
	 * Enumerado para los posibles estados de un barco
	 */
	public enum TipoEvento {NUEVO, ABRIR, COLOCAR, QUITAR, ERRORBARCO, ERRORPOSICION, ERRORTABLERO, ERRORSINTAXIS}
	
	//Constantes para dimensiones del tablero
	public final static int MINIMO = 10;
	public final static int MAXIMO = 16;
	
	//Constantes de número de barcos
	public final static int NUM_BARCOS = 10;
	public final static int NUM_SUBMARINOS = 4;
	public final static int NUM_FRAGATAS = 3;
	public final static int NUM_ACORAZADOS = 2;
	public final static int NUM_PORTAAVIONES = 1;
	
	//Constantes de Longitudes
	public final static int LONG_SUBMARINO = 1;
	public final static int LONG_FRAGATA = 2;
	public final static int LONG_ACORAZADO = 3;
	public final static int LONG_PORTAAVIONES = 4;
}
