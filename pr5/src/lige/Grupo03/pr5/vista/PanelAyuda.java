//PanelAyuda.java

package lige.Grupo03.pr5.vista;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Clase que define el panel en el que se va a mostrar la ayuda
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 */
public class PanelAyuda extends JPanel{

	//------------------------
	//ATRIBUTOS
	//------------------------
	private static final long serialVersionUID = 1L;
	private JTextArea _ayuda;
	//------------------------

	/**
	 * Constructor predeterminado
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public PanelAyuda () throws FileNotFoundException, IOException {
		super();
		setLayout(new FlowLayout());
		_ayuda = new JTextArea(20, 40);
		_ayuda.setEditable(false);
		JScrollPane scrollAyuda = new JScrollPane();
		scrollAyuda.setViewportView(_ayuda);
		scrollAyuda.setToolTipText("Ayuda de la aplicación");
		this.add(scrollAyuda);
		
		leerFichero();
	}
	
	/**
	 * Método privado para leer el fichero de ayuda.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void leerFichero() throws FileNotFoundException, IOException{
		String fichero = "ayuda.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fichero));
			String linea = br.readLine();
			while (linea != null) {
				_ayuda.append(linea + '\n');
				linea = br.readLine();
			}
			_ayuda.setCaretPosition(0);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			throw new IOException();
		}
	}
}
