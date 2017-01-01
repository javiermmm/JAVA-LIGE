//Principal.java

package lige.grupo03.pr2;

import lige.grupo03.pr2.logica.JuegoBarcos;

/**
 * Clase principal con el punto de entrada al Hundir la Flota
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see JuegoBarcos
 */
public class Principal {

	/**
	 * @param args Argumentos de la aplicacion
	 */
	public static void main(String[] args) {

		boolean modoDebug = false;
		if (args.length > 0)
			if (args[0].equals("-d"))
				modoDebug = true;
		
		JuegoBarcos juegoBarcos = new JuegoBarcos();
		juegoBarcos.aJugar(modoDebug);
	}

}
