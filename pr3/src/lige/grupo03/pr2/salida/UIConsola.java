//UIConsola.java

package lige.grupo03.pr2.salida;

import java.util.Scanner;

import lige.grupo03.pr2.logica.Posicion;
import lige.grupo03.pr2.logica.Tablero;

/**
 * Clase para solicitar por teclado ciertos datos y presentar un tablero.
 * @author Javier Martin & Jessica Martin
 * @version 3.0
 */
public class UIConsola extends UI {

	//----------------------
	// ATRIBUTO
	//----------------------
	private Scanner _scaner;
	//----------------------

	/**
	 * Constructor predeterminado
	 * @param modoDebug 
	 */
	public UIConsola(boolean modoDebug){
		_scaner = new Scanner(System.in);
		_debug = modoDebug;
	}

	/**
	 * Funci�n general para solicitar un dato del tablero. Esta funcion espera que el 
	 * dato a leer sea entero, y se asegura de no fallar en la captura del dato
	 * @return el dato entero que se ha leido
	 */
	private int pideDatoTablero() {

		int datoTmp = 0;
		boolean esEntero = false;
		while (!esEntero) {
			if (_scaner.hasNextInt()) {
				datoTmp = _scaner.nextInt();
				esEntero = true;
			} else {
				_scaner.next();
				System.out.println();
				mostrarError("ERROR: Introduce un valor entero");
			}
		}

		return datoTmp;
	}

	/**
	 * Haciendo uso de la funcion pideDatoTablero se solicita el alto del tablero
	 * @return el alto para el tablero
	 */
	public int pideAltoTablero() {

		System.out.println("Introduce el alto que tendr� el tablero");
		return pideDatoTablero();
	}

	/**
	 * Haciendo uso de la funcion pideDatoTablero se solicita el ancho del tablero
	 * @return el ancho para el tablero
	 */
	public int pideAnchoTablero() {

		System.out.println("Introduce el ancho que tendr� el tablero");
		return pideDatoTablero();
	}

	/**
	 * Haciendo uso de la funcion pideDatoTablero se solicitan las coordenadas
	 * de una posicion del tablero
	 * @return la posicion ya creada
	 */
	public Posicion pidePosicion() {

		Posicion pos = new Posicion();
		System.out.println("Introduce la coordenada x ");
		int xNueva = pideDatoTablero();
		pos.setX(xNueva);
		System.out.println("Introduce la coordenada y ");
		int yNueva = pideDatoTablero();
		pos.setY(yNueva);

		return pos;
	}

	/**
	 * Funcion que muestra un mensaje de error por pantalla
	 * @param error Mensaje que de error que se va a mostrar
	 */
	public void mostrarError(String error) {

		System.out.println(error);
	}

	/**
	 * Funci�n general para preguntar algo cuya respuesta
	 * (que se va a leer de teclado) es de si o no
	 * @param pregunta Cadena con la pregunta que vamos a formular
	 * @return valor booleano con la respuesta a la pregunta: si (true) o no (false)
	 */
	private boolean pedirAlgoSiONo(String pregunta) {

		String respuesta = "";

		do {
			System.out.println(pregunta);
			respuesta = _scaner.next();
			respuesta = respuesta.toLowerCase(); // convertimos lo que leamos a
													// minusculas
		} while ((!respuesta.equals("si")) && (!respuesta.equals("no")));

		if (respuesta.equals("si"))
			return true;
		else
			return false;
	}

	/**
	 * Haciendo uso de la funcion pedirAlgoSiONo, preguntamos al jugador 
	 * si quiere jugar otra partida
	 * @return respuesta del jugador
	 */
	public boolean pedirJugarOtra() {

		return pedirAlgoSiONo("�Quieres jugar otra partida? (si/no)");
	}

	/**
	 * Con este metodo se muestra la puntuacion total conseguida durante la partida
	 * Al mostrar el resultado se ha multiplicado la puntuacion por 100, por est�tica.
	 * Mostraremos la puntuacion en formato decimal, paramayor precision.
	 * @param puntuacion Numero real que representa la puntuacion en la partida
	 */
	public void mostrarPuntuaciones(float puntuacion) {

		System.out.println("");
		System.out.println("La puntuacion total es de :" + puntuacion*100
				+ " puntos.");
		System.out.println("");

	}

	/**
	 * Metodo para dibujar en pantalla el tablero principal, con los estados de las casillas
	 * @param t Tablero que se quiere dibujar
	 */
	private void dibujarTablero(Tablero t) {

		// Tomamos alto y ancho
		int alto = t.getAlto();
		int ancho = t.getAncho();

		// DIBUJAR
		System.out.print(" ");
		System.out.print(" ");
		System.out.print(" ");
		System.out.print(" ");
		for (int i = 0; i < ancho; ++i) {
			System.out.print(i + 1);
			System.out.print(" ");
			System.out.print(" ");
			System.out.print(" ");
			if ((i + 1) < 10)
				System.out.print(" ");
		}
		System.out.println();
		// En este trozo pintamos la fila inicial de n�meros con cuidado, para
		// que queden colocados

		for (int j = 0; j < alto; ++j) {
			System.out.print(" ");
			System.out.print(" ");
			pintaFila(t, ancho, '-', 0, false, false);
			if ((j + 1) < 10)
				System.out.print(" ");
			System.out.print(j + 1);
			pintaFila(t, ancho, ' ', j, true, false);
		}
		System.out.print(" ");
		System.out.print(" ");
		pintaFila(t, ancho, '-', 0, false, false);
		// En este trozo pintamos el tablero en s�

	}

	/**
	 * Metodo para pintar la fila de un tablero. Se considera fila del tablero
	 * a dos filas de pantalla en las que una servira como delimitadora de filas
	 * y la otra ser� la fila de contenido con las celdas.
	 * @param t Tablero que se quiere dibujar
	 * @param ancho ancho del tablero a dibujar
	 * @param delimitador Caracter que queremos pintar. Sera un guion para delimitar las filas
	 * @param indiceX contador para saber qu� fila estamos dibujando
	 * @param esCelda valor booleano que indica si la fila que pintamos es una fila de celdas(true) o delimitadora (false)
	 * @param debug valor booleano que indica si estamos pintando una fila del tableroDebug(true) o del normal(false)
	 */
	private void pintaFila(Tablero t, int ancho, char delimitador, int indiceX,
			boolean esCelda, boolean debug) {

		for (int y = 0; y < ancho; ++y) {
			System.out.print("|");
			if (!debug){
				System.out.print(delimitador);
				System.out.print(delimitador);
			}
			if (esCelda)
				if (debug)
					System.out.print(_tableroInicial[indiceX][y]);
				else
					System.out.print(dameCaracterCasilla(t.getEstadoCasilla(
							indiceX, y)));
			else
				System.out.print(delimitador);
			System.out.print(delimitador);
			// Escribimos el delimitador varias veces, para que quede un poco
			// mas cuadrado
		}
		System.out.print("|");// a�adimos el limite final del tablero

		System.out.println();// Para cambiar de linea en pantalla
	}

	/**
	 * Metodo para dibujar en pantalla el tablero debug, con la situacion inicial
	 * de los barcos al colocarlos en el tablero de juego.
	 * NOTA: No se ha hecho un metodo comun, porque los aspectos de los dos tableros
	 * (normal y debug), no son iguales y ponerlo en comun hubiera restado legibilidad 
	 * al codigo. Sin embargo se ha agrupado cierta funcionalidad en la funcion pintaFila.
	 * @param t Tablero que se quiere dibujar
	 */
	private void dibujarTableroDebug(Tablero t) {

		// Tomamos alto y ancho
		int alto = t.getAlto();
		int ancho = t.getAncho();

		// DIBUJAR
		System.out.println("");
		System.out.println("");
		System.out.print(" ");
		System.out.print(" ");
		System.out.print(" ");
		for (int i = 0; i < ancho; ++i) {
			System.out.print(i + 1);
			System.out.print(" ");
			if ((i + 1) < 9)
				System.out.print(" ");
		}
		System.out.println();
		// En este trozo pintamos la fila inicial de n�meros con cuidado, para
		// que queden colocados

		for (int j = 0; j < alto; ++j) {
			System.out.print(" ");
			System.out.print(" ");
			pintaFila(t, ancho, '-', 0, false, true);
			if ((j + 1) < 10)
				System.out.print(" ");
			System.out.print(j + 1);
			pintaFila(t, ancho, ' ', j, true, true);
		}
		System.out.print(" ");
		System.out.print(" ");
		pintaFila(t, ancho, '-', 0, false, true);
		// En este trozo pintamos el tablero en s�

	}

	/**
	 * Metodo que presneta un peque�o resumen del numero de barcos tocados, hundidos e intactos
	 * que hay en el tablero al momento de su invocacion
	 * @param t Tablero que se quiere inspeccionar
	 */
	public void muestraNumBarcosDeCadaTipo(Tablero t) {
		
		System.out.println("");
		System.out.println("Barcos HUNDIDOS: " + t.getNumHundidos());
		System.out.println("Barcos TOCADOS: " + t.getNumTocados());
		System.out.println("Barcos INTACTOS: " + (t.getBarcos().size() - t.getNumHundidos() - t.getNumTocados()));
	}
	
	/**
	 * Metodo que invoca a todos los metodos que muestran informacion de la partida
	 * para pintar en pantalla un resumen total del estado de la partida
	 * @param t Tablero que se esta utilizando
	 */
	public void muestraEstadoPartida (Tablero t) {
		
		dibujarTablero (t);
		if (_debug)
			dibujarTableroDebug (t);
		muestraNumBarcosDeCadaTipo (t);
	}
	
	/**
	 * Procedimiento que se encarga de mostrar el fin de la partida y un mensaje con los disparos usados
	 * @param t Tablero que tiene la informacion que queremos consultar (la disposicion de los barcos)
	 * @param numDisparos N�mero de Disparos necesitados durante la partida
	 */
	public void mostrarEstadoFinal (Tablero t, int numDisparos) {
		
		System.out.println("");
		System.out.println("Ya has acabado la partida, y...");
		System.out.println("���'S�lo has necesitado " + numDisparos + " disparos!!!");
		System.out.println("�La pr�xima vez seguro que lo haces incluso mejor!");
		System.out.println("");
		System.out.println("La colocacion de los barcos era esta:");
		System.out.println("");
		dibujarTableroDebug (t);
	}

	/**
	 * Procedimiento que se encarga de mostrar el peque�o menu 
	 * que indica los distintos tipos de jugador disponible
	 */
	private void menuEligeJugador() {
		
		System.out.println();
		System.out.println("Introduce el tipo de jugador que quieres usar (1-3):");
		System.out.println("1.- Jugador Humano (Tu mismo disparas)");
		System.out.println("2.- Jugador IA (Se realizaran disparos aleatorios))");
		System.out.println("3.- Jugador IA Mejorada (Se realizaran disparos aleatorios, hasta acertar a un barco, en ese momento se disparar� con intencion))");
		System.out.println();
	}
	
	@Override
	/**
	 * funcion que solicita al usuario la opcion que define el tipo de jugador a usar
	 * @return el entero con la opcion que representa el tipo de jugador elegido
	 */
	public int pideTipoJugador() {
		
		menuEligeJugador();
		
		int opcion =0;
		boolean esEntero = false;
		boolean esValido = false;
		while (!esEntero && !esValido) {
			if (_scaner.hasNextInt()) {
				opcion = _scaner.nextInt();
				esEntero = true;
				if ((opcion >= 1) || (opcion <= 3))
					esValido = true;
			} else {
				_scaner.next();
				System.out.println();
				mostrarError("ERROR: Introduce un valor entero entre 1 y 3");
			}
		}
		return opcion;
	}
}
