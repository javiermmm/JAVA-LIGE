//ParseadorSax.java

package lige.grupo03.pr6.editor.modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lige.grupo03.pr6.editor.modelo.Constantes.Direccion;
import lige.grupo03.pr6.editor.modelo.Constantes.TipoBarco;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Javier y Jéssica
 *
 */
public class ParseadorSax {

	//-----------------------------
	//ATRIBUTOS
	//-----------------------------
	private OyenteSax _oyenteSax;
	//-----------------------------
	
	/**
	 * Funcion que devuelve el tablero que hemos cargado al abrir el fichero XML
	 * @return Tablero que se ha cargado
	 */
	public Tablero getTablero(){
		return _oyenteSax.getTablero();
	}
	
	/**
	 * Accedente para el numero de portaaviones
	 * @return numero de portaaviones
	 */
	public int getNumPortaaviones() {
		return _oyenteSax.getNumPortaaviones();
	}
	
	/**
	 * Accedente para el numero de acorazados
	 * @return numero de acorazados
	 */
	public int getNumAcorazados() {
		return _oyenteSax.getNumAcorazados();
	}
	
	/**
	 * Accedente para el numero de fragatas
	 * @return numero de fragatas
	 */
	public int getNumFragatas() {
		return _oyenteSax.getNumFragatas();
	}
	
	/**
	 * Accedente para el numero de submarinos
	 * @return numero de submarinos
	 */
	public int getNumSubmarinos() {
		return _oyenteSax.getNumSubmarinos();
	}
	
	/**
	 * Accedente para el error en algun barco
	 * @return Valor del Booleano que indica si hay error en algun barco
	 */
	public boolean getErrorBarco() {
		return _oyenteSax.getErrorBarco();
	}
	
	/**
	 * Accedente para el error en alguna posicion
	 * @return Valor del Booleano que indica si hay error en alguna posicion
	 */
	public boolean getErrorPosicion() {
		return _oyenteSax.getErrorPosicion();
	}
	
	/**
	 * Accedente para el error en el tablero
	 * @return Valor del Booleano que indica si hay error en el tablero
	 */
	public boolean getErrorTablero() {
		return _oyenteSax.getErrorTablero();
	}
	
	/**
	 * Mutador para el booleano que identifica el error en algun barco
	 * @param valor Booleano que se le pone al error.
	 */
	public void setErrorBarco(boolean valor){
		_oyenteSax.setErrorBarco(valor);
	}
	
	/**
	 * Mutador para el booleano que identifica el error en alguna posicion
	 * @param valor Booleano que se le pone al error.
	 */
	public void setErrorPosicion(boolean valor){
		_oyenteSax.setErrorPosicion(valor);
	}
	
	/**
	 * Mutador para el booleano que identifica el error en el tablero
	 * @param valor Booleano que se le pone al error.
	 */
	public void setErrorTablero(boolean valor){
		_oyenteSax.setErrorTablero(valor);
	}
	
	public boolean abrir(File ficheroOrigen){
		boolean valido = true;
		//Creamos el parser empleando la factoría
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			valido = false;
		} catch (SAXException e) {
			valido = false;
		}
		
		//Oyente hereda de DefaultHandler
		_oyenteSax = new OyenteSax();
		
		//Lanzamos el proceso de parseo
		try {
			parser.parse(ficheroOrigen, _oyenteSax);
		} catch (SAXException e) {
			valido = false;
		} catch (IOException e) {
			valido = false;
		}
		return valido;
	};
	
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	private class OyenteSax extends DefaultHandler {

		//------------------------------
		//ATRIBUTOS
		//------------------------------
		private int _numPosBarco;
		private int _alto;
		private int _ancho;
		private Barco _barcoEnCurso;
		private ArrayList<Barco> _barcos;
		private int _numPortaaviones;
		private int _numAcorazados;
		private int _numFragatas;
		private int _numSubmarinos;
		private boolean _errorPosicion;
		private boolean _errorBarco;
		private boolean _errorTablero;
		//------------------------------
		
		/**
		 * Constructor predeterminado
		 */
		public OyenteSax () {
			_numPosBarco = 0;
			_alto = Constantes.MINIMO;
			_ancho = Constantes.MINIMO;
			_barcoEnCurso = null;
			_barcos = null;
			_numPortaaviones = 0;
			_numAcorazados = 0;
			_numFragatas = 0;
			_numSubmarinos = 0;
			_errorPosicion = false;
			_errorBarco = false;
			_errorTablero = false;
		}
		
		/**
		 * Funcion que devuelve el tablero cargado
		 * @return Tablero que se ha cargado
		 */
		public Tablero getTablero(){
			return new Tablero(_alto,_ancho,_barcos);
		}
		
		/**
		 * Accedente para el numero de portaaviones
		 * @return numero de portaaviones
		 */
		public int getNumPortaaviones() {
			return _numPortaaviones;
		}
		
		/**
		 * Accedente para el numero de acorazados
		 * @return numero de acorazados
		 */
		public int getNumAcorazados() {
			return _numAcorazados;
		}
		
		/**
		 * Accedente para el numero de fragatas
		 * @return numero de fragatas
		 */
		public int getNumFragatas() {
			return _numFragatas;
		}
		
		/**
		 * Accedente para el numero de submarinos
		 * @return numero de submarinos
		 */
		public int getNumSubmarinos() {
			return _numSubmarinos;
		}
		
		/**
		 * Accedente para el error en algun barco
		 * @return Valor del Booleano que indica si hay error en algun barco
		 */
		public boolean getErrorBarco() {
			return _errorBarco;
		}
		
		/**
		 * Accedente para el error en alguna posicion
		 * @return Valor del Booleano que indica si hay error en alguna posicion
		 */
		public boolean getErrorPosicion() {
			return _errorPosicion;
		}
		
		/**
		 * Accedente para el error en el tablero
		 * @return Valor del Booleano que indica si hay error en el tablero
		 */
		public boolean getErrorTablero() {
			return _errorTablero;
		}
		
		/**
		 * Mutador para el booleano que identifica el error en algun barco
		 * @param valor Booleano que se le pone al error.
		 */
		public void setErrorBarco(boolean valor){
			_errorBarco = valor;
		}
		
		/**
		 * Mutador para el booleano que identifica el error en alguna posicion
		 * @param valor Booleano que se le pone al error.
		 */
		public void setErrorPosicion(boolean valor){
			_errorPosicion = valor;
		}
		
		/**
		 * Mutador para el booleano que identifica el error en el tablero
		 * @param valor Booleano que se le pone al error.
		 */
		public void setErrorTablero(boolean valor){
			_errorTablero = valor;
		}
		
		/**
		 * Este método sobreescribe al de la clase DefaultHandler e identifica el comienzo de un elemento/etiqueta en el 
		 * fichero xml
		 */
		public void startElement(String namespace, String sName, String qName, Attributes atrs) throws SAXException {
			procesaTablero(qName, atrs);
			procesaBarco(qName, atrs);
			procesaPosicion(qName, atrs);
		}

		/**
		 * Este metodo encapsula el procesamiento cuando se identifica el comienzo de una etiqueta de tablero
		 * @param qName nombre del elemento que procesamos. En este caso tablero
		 * @param atrs Atributos del elemento que procesamos. En este caso ancho y alto
		 */
		private void procesaTablero(String qName, Attributes atrs) {
			if (qName == "tablero")
				if (atrs != null) {
					if (atrs.getLength() == 2){
						for(int i=0; i<atrs.getLength(); i++) {
							if (atrs.getQName(i) == "ancho")
								 _ancho = Integer.parseInt(atrs.getValue(i));
							if (atrs.getQName(i) == "alto")
								_alto = Integer.parseInt(atrs.getValue(i));				
							}
						_barcos = new ArrayList<Barco>();
					}
					else {
						_errorTablero = true;
					}
				}
		}
		
		/**
		 * Este metodo encapsula el procesamiento cuando se identifica el comienzo de una etiqueta de barco
		 * @param qName nombre del elemento que procesamos. En este caso barco
		 * @param atrs Atributos del elemento que procesamos. En este caso tipo
		 */
		private void procesaBarco (String qName, Attributes atrs) {
			if (qName == "barco") { 
				if (atrs != null) {
					if (atrs.getLength() == 1){
						if (atrs.getQName(0) == "tipo") { 
							String tipoBarco = atrs.getValue(0);
							if (sePuedeColocar(tipoBarco)){
								_barcoEnCurso = new Barco (this.stringToTipoBarco(atrs.getValue(0)));
								_numPosBarco = 0;
							}
							else
								_errorBarco = true;
						}
					}
					else
						_errorBarco = true;
				}
			}
		}
		
		/**
		 * Este metodo encapsula el procesamiento cuando se identifica el comienzo de una etiqueta de posicion
		 * @param qName nombre del elemento que procesamos. En este caso posicion
		 * @param atrs Atributos del elemento que procesamos. En este caso x e y.
		 */
		private void procesaPosicion (String qName, Attributes atrs) {
			if (qName == "posicion") {
				if (_numPosBarco < Barco.getLongitud(_barcoEnCurso.getTipoBarco())) {
					if (atrs != null) {
						if (atrs.getLength() == 2) {
							int x = 0;
							int y = 0;
							for(int i=0; i<atrs.getLength(); i++) {
								if (atrs.getQName(i)=="x")
									x = (Integer.parseInt(atrs.getValue(i)));
								if (atrs.getQName(i)=="y")
									y = Integer.parseInt(atrs.getValue(i));
							}
							if (posicionValida(x, y)) {
								_barcoEnCurso.setPosicion(new Posicion(x, y), _numPosBarco);
								if (Barco.getLongitud(_barcoEnCurso.getTipoBarco()) == _numPosBarco+1) {
									if (! esAdyacente(_barcoEnCurso, _barcos) && (esBarcoCoherente(_barcoEnCurso))) {
										_barcos.add(_barcoEnCurso);
										actualizaNumBarcos(_barcoEnCurso.getTipoBarco());
									}
									else
										_errorBarco = true;
								}
								_numPosBarco++;
							}
							else 
								_errorPosicion = true;
						}
						else
							_errorPosicion = true;
					}
				}
			}
		}
		
		/**
		 * Este método sobreescribe al de la clase DefaultHandler e identifica el fin de un elemento/etiqueta en el 
		 * fichero xml
		 */
		public void endElement(String nameSpace, String sName, String qName)  {
			if (qName == "barco"){
					if (_numPosBarco < Barco.getLongitud(_barcoEnCurso.getTipoBarco()))
						_errorBarco = true;
			}
		}
		
		/**
		 * Funcion que comprueba que un barco es coherente, es decir, que todas sus posiciones son adyacentes en linea (N,S,E,O)
		 * @param barco barco que comprobamos
		 * @return Valor booleano que indica si el barco es coherente (true) o no (false)
		 */
		private boolean esBarcoCoherente(Barco barco) {
			Posicion[] posiciones = barco.getPosicionesOcupadas();	
			Posicion posicion = posiciones[0];
			boolean coherente = true;
			if (posiciones.length >1) {
				if (posicion.esAdyacenteEnCruz(posiciones[1])) {
					Direccion dir = compruebaDireccion(posiciones[0], posiciones[1]);
					int i = 1;
					while (i < Barco.getLongitud(_barcoEnCurso.getTipoBarco())-1 && coherente){
						Posicion posAux = posiciones[i].calculaPosicionSiguiente(dir);
						if (! posAux.equals(posiciones[i+1]))
							coherente = false;
						else
							i++;
					}
				}
				else
					coherente =false;
			}			
			return coherente;
		}
		
		/**
		 * Funcion que dadas dos posiciones comprueba si son adyacentes y en tal caso, devuelve la orientacion
		 * @param posicion Posicion inicial que tomamos de referencia
		 * @param posicion2 Segunda posicion a partir de la cual sacaremos la direccion
		 * @return Direccion calculada que sigue el barco
		 */
		private Direccion compruebaDireccion(Posicion posicion, Posicion posicion2) {
			if (posicion.getX() == posicion2.getX()){
				if (posicion.getY() < posicion2.getY())
					return Direccion.ESTE;
				if (posicion.getY() > posicion2.getY())
					return Direccion.OESTE;
			}
			if (posicion.getY() == posicion2.getY()){
				if (posicion.getX() < posicion2.getX())
					return Direccion.SUR;
				if (posicion.getX() > posicion2.getX())
					return Direccion.NORTE;
			}
			return null;
		}

		/**
		 * Funcion que indica si un barco es adyacente a cualquiera de los barcos ya colocados
		 * @param barcoEnCurso Barco que se pretende colocar
		 * @param barcos ArrayList con los barcos ya colocados
		 * @return Valor booleano que indica si el barco en curso es adyacente a algun otro (true) o no (false)
		 */
		private boolean esAdyacente(Barco barcoEnCurso, ArrayList<Barco> barcos) {
			Iterator<Barco> iterador = barcos.iterator();
			boolean adyacentes = false;
			while (iterador.hasNext() && ! adyacentes){
				Barco b = iterador.next();
				int i = 0;
				while (! adyacentes && i<Barco.getLongitud(barcoEnCurso.getTipoBarco())){
					int j = 0;
					while (! adyacentes && j<Barco.getLongitud(b.getTipoBarco())){
						if (barcoEnCurso.getPosicion(i).esAdyacente(b.getPosicion(j)))
							adyacentes = true;
						else 
							j++;
					}
					i++;
				}
			}
			return adyacentes;
		}

		/**
		 * Funcion que indica si la posicion es valida en cuanto a las dimensiones del tablero
		 * @param x valor de la coordenada x de la posicion
		 * @param y valor de la coordenada y de la posicion
		 * @return Valor booleano que indica si la posicion es valida (true) o no (false)
		 */
		private boolean posicionValida(int x, int y) {
			return (x >= 0) && (x <= _alto) && (y >= 0) && (y <= _ancho);
		}

		/**
		 * Funcion que indica si se puede colocar un tipo de barco teniendo en cuenta los ya colocados
		 * @param s Cadena con el tipo de barco que queremos colocar
		 * @return Valor booleano que indica si se puede colocar el barco (ture) o no (false)
		 */
		private boolean sePuedeColocar(String s) {
			if (s.equals("portaaviones") && _numPortaaviones < Constantes.NUM_PORTAAVIONES)
				return true;
			else if (s.equals("destructor") && _numAcorazados < Constantes.NUM_ACORAZADOS)
				return true;
			else if (s.equals("fragata") && _numFragatas < Constantes.NUM_FRAGATAS)
				return true;
			else if (s.equals("submarino") && _numSubmarinos < Constantes.NUM_SUBMARINOS)
				return true;
			else return false;
		}

		/**
		 * Metodo que incrementa en uno el numero de barcos colocados de un tipo, cuando lo colocamos
		 * @param tipoBarco Tipo de barco que hemos colocado
		 */
		private void actualizaNumBarcos(TipoBarco tipoBarco) {
			if (tipoBarco == TipoBarco.PORTAAVIONES)
				_numPortaaviones++;
			else if (tipoBarco == TipoBarco.ACORAZADO)
				_numAcorazados++;
			else if (tipoBarco == TipoBarco.FRAGATA)
				_numFragatas++;
			else if (tipoBarco == TipoBarco.SUBMARINO)
				_numSubmarinos++;
		}

		/**
		 * Funcion que convierte un string con el tipo de barco al enumerado equivalente
		 * @param s Cadena con el tipo de barco
		 * @return Enumerado con el tipo de barco
		 */
		private TipoBarco stringToTipoBarco(String s){
			TipoBarco tipo = null;
			if (s.equals("portaaviones"))
				tipo = TipoBarco.PORTAAVIONES;
			else if (s.equals("destructor"))
				tipo = TipoBarco.ACORAZADO;
			else if (s.equals("fragata"))
				tipo = TipoBarco.FRAGATA;
			else if (s.equals("submarino"))
				tipo = TipoBarco.SUBMARINO;
			return tipo;	
		}	
	}
}
