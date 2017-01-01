//Editor.java

package lige.grupo03.pr6.editor.modelo;

import java.io.File;
import java.util.Observable;

import lige.grupo03.pr6.editor.modelo.Constantes.Direccion;
import lige.grupo03.pr6.editor.modelo.Constantes.TipoBarco;
import lige.grupo03.pr6.editor.vista.Eventos.EventoAbrir;
import lige.grupo03.pr6.editor.vista.Eventos.EventoColocarBarco;
import lige.grupo03.pr6.editor.vista.Eventos.EventoErrorBarco;
import lige.grupo03.pr6.editor.vista.Eventos.EventoErrorPosicion;
import lige.grupo03.pr6.editor.vista.Eventos.EventoErrorSintaxis;
import lige.grupo03.pr6.editor.vista.Eventos.EventoErrorTablero;
import lige.grupo03.pr6.editor.vista.Eventos.EventoNuevoEscenario;
import lige.grupo03.pr6.editor.vista.Eventos.EventoQuitaBarco;

/**
 * Clase que actua de modelo en la aplicacion [Patron MVC]
 * 
 * @author Jessica 	Martin & Javier Martin
 * @version 1.0
 */
public class Editor extends Observable {

	//----------------------------
	//ATRIBUTOS
	//----------------------------
	private int _numPortaaviones;
	private int _numAcorazados;
	private int _numFragatas;
	private int _numSubmarinos;
	private Tablero _tablero;
	private File _archivo;
	//----------------------------
	
	/**
	 * Constructor predeterminado
	 */
	public Editor(){
		_numPortaaviones = Constantes.NUM_PORTAAVIONES;
		_numAcorazados = Constantes.NUM_ACORAZADOS;
		_numFragatas = Constantes.NUM_FRAGATAS;
		_numSubmarinos = Constantes.NUM_SUBMARINOS;
		_archivo = null;
	}
	
	/**
	 * Método que crea el escenario (Tablero) en el que se colocan los barcos
	 * @param ancho Ancho que tendra el tablero
	 * @param alto Alto que tendra el tablero
	 */
	public void creaEscenario(int ancho, int alto) {
		_archivo = null;
		_tablero = new Tablero(ancho, alto);
		this.setChanged();
		this.notifyObservers(new EventoNuevoEscenario(ancho, alto));
		
	}

	/**
	 * Funcion que comprueba si el barco se puede colocar, y en caso afirmativo lo coloca
	 * @param barco Tipo de barco que estamos colocando.
	 * @param pos Posicion en la que empieza el barco
	 * @param direccion Direccion en la que vamos a colocar el barco
	 * @return Valor booleano que indica si se ha podido colocar el barco (true) o no (false).
	 */
	public boolean sePuedeColocar(TipoBarco barco, Posicion pos, Direccion direccion) {
		boolean sePuede = _tablero.seHaPodidoColocarBarco(barco, pos, direccion, Barco.getLongitud(barco)); 
		if (sePuede){
			decrementaBarcosDisponibles(barco);
			Barco b = _tablero.hayBarco(pos);
			this.setChanged();
			this.notifyObservers(new EventoColocarBarco(b.getPosicionesOcupadas(), _numPortaaviones, _numAcorazados, _numFragatas, _numSubmarinos));
		}
		return sePuede;
	}

	/**
	 * Metodo que decrementa el numero de barcos disponibles de un tipo al colocar alguno de ellos
	 * @param barco Tipo de barco que estamos colocando.
	 */
	private void decrementaBarcosDisponibles(TipoBarco barco) {
		if (barco == TipoBarco.PORTAAVIONES)
			_numPortaaviones--;
		if (barco == TipoBarco.ACORAZADO)
			_numAcorazados--;
		if (barco == TipoBarco.FRAGATA)
			_numFragatas--;
		if (barco == TipoBarco.SUBMARINO)
			_numSubmarinos--;
	}
	
	/**
	 * Metodo que inrementa el numero de barcos disponibles de un tipo al quitar alguno de ellos
	 * @param barco Tipo de barco que estamos quitando.
	 */
	private void incrementaBarcosDisponibles(TipoBarco barco) {
		if (barco == TipoBarco.PORTAAVIONES)
			_numPortaaviones++;
		if (barco == TipoBarco.ACORAZADO)
			_numAcorazados++;
		if (barco == TipoBarco.FRAGATA)
			_numFragatas++;
		if (barco == TipoBarco.SUBMARINO)
			_numSubmarinos++;
	}

	/**
	 * Funcion que comprueba si hay suficientes barcos disponibles de un tipo como para seguir intentando colocar alguno
	 * @param barco Tipo de barco que estamos colocando.
	 */
	public boolean haySuficientes(TipoBarco barco) {
		if ((barco == TipoBarco.PORTAAVIONES) && _numPortaaviones > 0)
			return true;
		if ((barco == TipoBarco.ACORAZADO) && _numAcorazados > 0)
			return true;
		if ((barco == TipoBarco.FRAGATA) && _numFragatas > 0)
			return true;
		if ((barco == TipoBarco.SUBMARINO) && _numSubmarinos > 0)
			return true;
		else
			return false;
	}

	/**
	 * Metodo que quita del tablero el barco que está ocupando la posicion pos
	 * @param pos Posicion ocupada por el barco que queremos quitar
	 */
	public void quitaBarco(Posicion pos) {

		Barco b = _tablero.hayBarco(pos);
		_tablero.quitaBarco(b);
		incrementaBarcosDisponibles(b.getTipoBarco());
		this.setChanged();
		this.notifyObservers(new EventoQuitaBarco(b.getPosicionesOcupadas(), _numPortaaviones, _numAcorazados, _numFragatas, _numSubmarinos));
	}

	/**
	 * Funcion que comprueba si se han colocado todos los barcos en el escenario
	 * @return Valor booleano que indica si todos los barcos se han colocado (true) o no (false)
	 */
	public boolean barcosGastados() {
		return (_numPortaaviones == 0) && (_numAcorazados == 0) && (_numFragatas == 0) && (_numSubmarinos == 0);
	}
	
	/**
	 * Metodo con el que el editor abrira un archivo XML con el estado de un escenario
	 * @param archivo Fichero que vamos a cargar.
	 */
	public void abrirArchivo (File archivo){
		ParseadorSax cosaQueAbre = new ParseadorSax();
		_archivo = archivo;
		if (cosaQueAbre.abrir(_archivo)){
			if (cosaQueAbre.getErrorTablero()){
				avisaErrorTablero(cosaQueAbre);
			}
			else if (cosaQueAbre.getErrorBarco()){
					avisaErrorBarco(cosaQueAbre);
				}
				else if (cosaQueAbre.getErrorPosicion()) {
						avisaErrorPosicion(cosaQueAbre);
					}
					else {
						avisaExitoAbrir(cosaQueAbre);
					}
		}
		else {
			this.setChanged();
			this.notifyObservers(new EventoErrorSintaxis());
		}
	}
	
	/**
	 * Metodo privado que avisa a la vista de un error en el tablero al abrir el fichero
	 * @param cosaQueAbre Parseador SAX usado para abrir el fichero.
	 */
	private void avisaErrorTablero(ParseadorSax cosaQueAbre) {
		cosaQueAbre.setErrorTablero(false);
		this.setChanged();
		this.notifyObservers(new EventoErrorTablero());
	}

	/**
	 * Metodo privado que avisa a la vista de un error en un barco al abrir el fichero
	 * @param cosaQueAbre Parseador SAX usado para abrir el fichero.
	 */
	private void avisaErrorBarco (ParseadorSax cosaQueAbre) {
		cosaQueAbre.setErrorBarco(false);
		this.setChanged();
		this.notifyObservers(new EventoErrorBarco());
	}
	
	/**
	 * Metodo privado que avisa a la vista de un error en una posicion al abrir el fichero
	 * @param cosaQueAbre Parseador SAX usado para abrir el fichero.
	 */
	private void avisaErrorPosicion (ParseadorSax cosaQueAbre){
		cosaQueAbre.setErrorPosicion(false);
		this.setChanged();
		this.notifyObservers(new EventoErrorPosicion());
	}
	
	/**
	 * Metodo privado que avisa a la vista de los cambios necesario al abrir el fichero con exito
	 * @param cosaQueAbre Parseador SAX usado para abrir el fichero.
	 */
	private void avisaExitoAbrir(ParseadorSax cosaQueAbre) {
		_tablero = cosaQueAbre.getTablero();
		_numPortaaviones = Constantes.NUM_PORTAAVIONES - cosaQueAbre.getNumPortaaviones();
		_numAcorazados = Constantes.NUM_ACORAZADOS - cosaQueAbre.getNumAcorazados();
		_numFragatas = Constantes.NUM_FRAGATAS - cosaQueAbre.getNumFragatas();
		_numSubmarinos = Constantes.NUM_SUBMARINOS - cosaQueAbre.getNumSubmarinos();
		this.setChanged();
		this.notifyObservers(new EventoAbrir(_tablero.getAncho(), _tablero.getAlto(), _tablero.getBarcos(), _numPortaaviones, _numAcorazados, _numFragatas, _numSubmarinos));
	}
	
	/**
	 * Metodo que guarda el estado del tablero en un ficheroXML (el que este abierto actualmente)
	 */
	public void guardar() {
		CreadorDom cosaQueGuarda= new CreadorDom ();
		cosaQueGuarda.guardarComo(_archivo, _tablero);
	}
	
	/**
	 * Metodo que guarda el estado del tablero en un ficheroXML, especificando el fichero donde se guardara
	 * @param archivo fichero en el que se va a guardar.
	 */
	public void guardarComo(File archivo) {
		_archivo = archivo;
		guardar();
	}
}
