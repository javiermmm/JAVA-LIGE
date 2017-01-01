//UIConsola.java

package lige.grupo03.pr1.salida;

import java.util.Scanner;

/**
* Clase para solicitar por teclado ciertos datos y presentar un tablero. 
* @author Javier Martin & Jessica Martin
* @version 1.0
*/
public class UIConsola {

	//---------
	//ATRIBUTO
	//---------
	private Scanner _scaner;
	
	/**
	 * Constructor por defecto
	 * @param ninguno
	 * @return nada
	 */
	public UIConsola () {
		_scaner = new Scanner(System.in);
	}
	
	/**
	 * Método para leer de teclado cualquier dato entero
	 * @param dato Dato que se va a pedir en formato string para mostrarlo por pantalla 
	 * @return el numero que represente el dato pedido
	 */
	private int pideDatoTablero (String dato) {
		
		int datoTmp = 0;
		boolean esEntero = false;
		System.out.println("Introduce el " + dato + " que tendrá el tablero");
		while (!esEntero) {
			if (_scaner.hasNextInt()){
				datoTmp = _scaner.nextInt();
				esEntero = true;
			}
			else {
				_scaner.next();
				System.out.println();
				System.out.println("ERROR: Introduce un valor entero para el " + dato);
			}
		}

		return datoTmp;
	}
	
	/**
	 * @param ninguno
	 * @return el alto para el tablero
	 */
	public int pideAltoTablero () {
		
		return pideDatoTablero ("alto");
	}
	
	/**
	 * @param ninguno
	 * @return el ancho para el tablero
	 */
	public int pideAnchoTablero () {
		
		return pideDatoTablero ("ancho");
	}	

	/**
	 * @param ninguno
	 * @return respuesta introducida por el usuario
	 */
	public boolean pedirDibujarOtroTablero () {
		
		String respuesta = "";
		
		do {
			System.out.println("¿Quieres dibujar otro tablero? (si/no)");
			respuesta = _scaner.next();
			respuesta = respuesta.toLowerCase(); //convertimos lo que leamos a minusculas
		} while ((!respuesta.equals("si")) && (!respuesta.equals("no")));
		
		if (respuesta.equals("si"))
			return true;
		else
			return false;
	}
	
	/**
	 * @param ninguno
	 * @return nada
	 */
	public void dibujarTablero () {
		
		boolean pintarOtro = false;
		do {
			//Tomamos alto y ancho
			int alto = pideAltoTablero();
			int ancho = pideAnchoTablero();
			
			//DIBUJAR
			System.out.print(" ");
			System.out.print(" ");
			System.out.print(" ");
			System.out.print(" ");
			for (int i=0; i<ancho; ++i) {
				System.out.print(i+1);
				System.out.print(" ");
				System.out.print(" ");
				System.out.print(" ");
				if ((i+1) < 10)
					System.out.print(" ");
			}
			System.out.println();
			//En este trozo pintamos la fila inicial de números con cuidado, para que queden colocados 
			
			
			for (int j=0; j<alto; ++j) {
				System.out.print(" ");
				System.out.print(" ");
				pintaFila(ancho, '-');
				if ((j+1) < 10)
					System.out.print(" ");
				System.out.print(j+1);
				pintaFila(ancho, ' ');
			}
			System.out.print(" ");
			System.out.print(" ");
			pintaFila(ancho, '-');
			//En este trozo pintamos el tablero en sí
			
			
			//Preguntamos si repetimos
			pintarOtro = pedirDibujarOtroTablero();
		} while (pintarOtro);
	}
	
	/**
	 * @param ancho ancho del tablero
	 * @param delimitador caracter que identifica el limite entre filas (blanco para representar una celda)
	 * @return nada
	 */
	private void pintaFila (int ancho, char delimitador) {
		
		for (int i=0; i<ancho; ++i) {
			System.out.print("|");
			System.out.print(delimitador);
			System.out.print(delimitador);
			System.out.print(delimitador);
			System.out.print(delimitador);
			//Escribimos el delimitador varias veces, para que quede un poco mas cuadrado
		}
		System.out.print("|");//añadimos el limite final del tablero
		
		System.out.println();//Para cambiar de linea en pantalla
	}
	
	public static void main(String[] args) {
		
		//------------------------
		//PRUEBAS DE UICONSOLA
		//------------------------
		UIConsola consola = new UIConsola(); 
		
		System.out.println("---------------PRUEBAS DE UICONSOLA------------------");
		System.out.println("Pedimos el alto individualmente");
		int alto = consola.pideAltoTablero();
		System.out.println(alto);
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("Pedimos el ancho individualmente");
		int ancho = consola.pideAnchoTablero();
		System.out.println(ancho);
		System.out.println();
		System.out.println();
		System.out.println();
		
		//PRUEBA GLOBAL
		System.out.println("Llamamos a dibujarTablero:");
		System.out.println();
		consola.dibujarTablero();
		System.out.println();
		System.out.println("-----------------------FIN------------------------");
	}
	
}
