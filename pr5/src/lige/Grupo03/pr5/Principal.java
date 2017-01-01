//Principal.java

package lige.Grupo03.pr5;

import lige.Grupo03.pr5.controlador.Controlador;
import lige.Grupo03.pr5.modelo.Partida;
import lige.Grupo03.pr5.vista.UIConsola;
import lige.Grupo03.pr5.vista.VentanaPrincipal;

/**
 * Punto de entrada a la aplicacion de los barquitos
 * 
 * @author Jessica 	Martin & Javier Martin
 * @version 3.0
 * @see Partida
 * @see UIConsola
 * @see Controlador
 * @see VentanaPrincipal
 */
public class Principal {

	/**
	 * @param args Argumentos de la aplicacion
	 */
	public static void main(String[] args) {

		boolean modoDebug=false;
		boolean porConsola=false;
		
		if (args.length>0)
			if (args[0].equals("-d"))
				modoDebug=true;
			else if (args[0].equals("-c"))
				porConsola=true;
		if (args.length==2)
			if (args[1].equals("-d"))
				modoDebug=true;
			else if (args[1].equals("-c"))
				porConsola=true;
		
		UIConsola consola = new UIConsola(modoDebug, porConsola);
		Partida modelo = new Partida(consola);
		Controlador controlador = new Controlador(modelo);
		VentanaPrincipal vista = new VentanaPrincipal(controlador, modoDebug, modelo.getNumDisparos(), modelo.getDispersiones(), modelo.getSonicos());
		modelo.addObserver (vista);
		modelo.addObserver (consola);
		controlador.setIntervaloIA(500);
		controlador.bucleIA();
	}

}
