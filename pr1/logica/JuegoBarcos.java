//JuegoBarcos.java

package lige.grupo03.pr1.logica;

import lige.grupo03.pr1.salida.UIConsola;

/**
* Clase que controla la ejecucion del juego
* @author Javier Martin & Jessica Martin
* @version 1.0
* @see Barco
* @see UIConsola
* @see Constantes
*/
public class JuegoBarcos {
	
	//------------------
	//ATRIBUTOS
	//------------------
	private int _altoTablero;
	private int _anchoTablero;
	private UIConsola _consola;
	//------------------
	
	/**
	 * Constructor por defecto
	 * @param ninguno
	 * @return nada
	 */
	public JuegoBarcos () {
		_consola = new UIConsola();
	}

	/**
	 * Metodo que solicita el alto y el ancho al usuario, hasta que 
	 * introduzca datos menores que un máximo (por defecto, 10)
	 * @param ninguno
	 * @return nada
	 */
	public void pideAnchoYAlto(){
		
		do {
			_anchoTablero = _consola.pideAnchoTablero();
		} while ((_anchoTablero > Constantes.ANCHOTABLEROMAXIMO) || ((_anchoTablero < 1))); 
		do {
			_altoTablero = _consola.pideAltoTablero();
		} while ((_altoTablero > Constantes.ALTOTABLEROMAXIMO) || ((_altoTablero < 1))); 
	}
	
	public static void main(String[] args) {
		
		//------------------------
		//PRUEBAS DE JUEGOBARCOS
		//------------------------
		JuegoBarcos juego = new JuegoBarcos();
		
		System.out.println("-------------PRUEBAS DE JUEGOBARCOS---------------");
		System.out.println("Ahora llamamos a pideAnchoYAlto");
		System.out.println();
		juego.pideAnchoYAlto();
		System.out.println();
		System.out.println();
		System.out.println("-----------------------------------------------");
	}
}