//CreadorDom.java

package lige.grupo03.pr6.editor.modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Clase con la que guardaremos los datos del escenario en un fichero XML
 * 
 * @author Jessica 	Martin & Javier Martin
 * @version 1.0
 * @see Transformador
 */
public class CreadorDom {

	/**
	 * Funcion que dada una longitud devuelve el tipo de barco en forma de cadena de caracteres.
	 * @param n Longitud del barco
	 * @return Cadena con el tipo de barco
	 */
	private String longToTipoBarco(int n){
		switch(n){
			case 1: return "submarino";
			case 2: return "fragata";
			case 3: return "destructor";
			case 4: return "portaaviones";
			default: return null;
		}
	}

	/**
	 * Funcion que guarda en un fichero XML el estado del escenario
	 * @param archivoDestino Fichero destino en el que se va a guardar
	 * @param tablero cuyo estado se guarda
	 */
	public void guardarComo(File archivoDestino, Tablero tablero){
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setValidating(true);
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e2) {}
		Document doc = docBuilder.newDocument();
		
		//Elemento raiz (Tablero)
		Element tableroXml = guardaTablero(doc, tablero);
		
		//Elemento Barco
		ArrayList<Barco> barcos = tablero.getBarcos();
		Barco barco;
		Iterator<Barco> iteradorBarcos = barcos.iterator();
		while ( iteradorBarcos.hasNext() ) {
			barco = iteradorBarcos.next();
			Element barcoXml = doc.createElement("barco");
			Attr tipo = doc.createAttribute("tipo");
			tipo.setValue(longToTipoBarco(Barco.getLongitud(barco.getTipoBarco())));
			barcoXml.setAttributeNode(tipo);
			
			//Elemento Posicion
			guardaPosicion(doc, barcoXml, barco);
			tableroXml.appendChild(barcoXml);
		}
		
		//TRANSFORMADOR
		Transformador transformador = new Transformador();
		transformador.transforma(doc, archivoDestino);
	}

	/**
	 * Metodo que se encarga de guardar la informacion referente a las posiciones
	 * @param doc Objeto que ayuda a crear la estructura con la informacion a guardar
	 * @param barcoXml Elemento que se identifica con un barco del escenario
	 * @param barco Barco del escenario que se esta procesando.
	 */
	private void guardaPosicion(Document doc, Element barcoXml, Barco barco) {
		for (int i=0; i<Barco.getLongitud(barco.getTipoBarco()); i++){
			Element posicion = doc.createElement("posicion");
			Attr x = doc.createAttribute("x");
			x.setValue("" + barco.getPosicion(i).getX());
			posicion.setAttributeNode(x);
			Attr y = doc.createAttribute("y");
			y.setValue("" + barco.getPosicion(i).getY());
			posicion.setAttributeNode(y);
			barcoXml.appendChild(posicion);
		}
	}

	/**
	 * Funcion que se encarga de guardar la informacion referente al tablero
	 * @param doc Objeto que ayuda a crear la estructura con la informacion a guardar
	 * @param tablero Tablero propiamente dicho cuyo estado se esta procesando
	 * @return Elemento que se identifica con el tablero
	 */
	private Element guardaTablero(Document doc, Tablero tablero) {
		Element tableroXml = doc.createElement("tablero");
		Attr ancho = doc.createAttribute("ancho");
		ancho.setValue(("" + tablero.getAncho()));
		tableroXml.setAttributeNode(ancho);
		Attr alto = doc.createAttribute("alto");
		alto.setValue(("" + tablero.getAlto()));
		tableroXml.setAttributeNode(alto);
		doc.appendChild(tableroXml);
		
		return tableroXml;
	}
}
