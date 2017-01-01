//Transformador.java

package lige.grupo03.pr6.editor.modelo;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * Clase que modela el transformador para convertir la estrucctura con la informacion a un fichero XML
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class Transformador {

	/**
	 * Metodo para realizar la transformacion final a un fichero XML
	 * @param doc Estructura con la informacion del escenario
	 * @param archivoDestino Fichero en el que vamos a guardar
	 */
	public void transforma(Document doc, File archivoDestino) {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = factoria.newTransformer();
			//transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "barcos.dtd");
		} catch (TransformerConfigurationException e1) {}
		//Creación de un Source a partir del arbol DOM
		DOMSource origen = new DOMSource(doc);
		//Creación de un Result a partir del fichero de destino
		StreamResult destino = new StreamResult(archivoDestino);
		try {
			transformer.transform(origen, destino);
		} catch (TransformerException e1) {}
	}
}
