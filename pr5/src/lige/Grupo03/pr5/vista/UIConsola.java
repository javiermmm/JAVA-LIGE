//UIConsola.java

package lige.Grupo03.pr5.vista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import lige.Grupo03.pr5.modelo.Barco;
import lige.Grupo03.pr5.modelo.Constantes.Casilla;
import lige.Grupo03.pr5.modelo.Constantes.TipoBarco;
import lige.Grupo03.pr5.modelo.Constantes.TipoEvento;
import lige.Grupo03.pr5.modelo.Posicion;
import lige.Grupo03.pr5.modelo.Tablero;
import lige.Grupo03.pr5.vista.Eventos.Evento;
import lige.Grupo03.pr5.vista.Eventos.EventoDisparo;
import lige.Grupo03.pr5.vista.Eventos.EventoTodosBarcosHundidos;

/**
 * Clase para solicitar por teclado ciertos datos y presentar un tablero.
 * @author Javier Martin & Jessica Martin
 * @version 3.0
 */
public class UIConsola implements Observer {

	private char[][] _tableroInicial;
	private boolean _debug;
	private boolean _consolaActivada;
	//----------------------

	/**
	 * Constructor predeterminado
	 * @param modoDebug 
	 */
	public UIConsola(boolean modoDebug, boolean consolaActivada){
		_debug  = modoDebug;
		_consolaActivada = consolaActivada; 
		new Scanner(System.in);
	}

	/**
	 * Con este metodo se muestra la puntuacion total conseguida durante la partida
	 * Al mostrar el resultado se ha multiplicado la puntuacion por 100, por estética.
	 * Mostraremos la puntuacion en formato decimal, paramayor precision.
	 * @param puntuacion Numero real que representa la puntuacion en la partida
	 */
	private void mostrarPuntuaciones(float puntuacion) {

		System.out.println("");
		System.out.println("La puntuacion total es de :" + puntuacion*100
				+ " puntos.");
		System.out.println("");

	}

	/**
	 * Metodo para dibujar en pantalla el tablero principal, con los estados de las casillas
	 * @param t Tablero que se quiere dibujar
	 */
	private void dibujarTablero(Casilla[][] tablero, int ancho, int alto) {

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
		// En este trozo pintamos la fila inicial de números con cuidado, para
		// que queden colocados

		for (int j = 0; j < alto; ++j) {
			System.out.print(" ");
			System.out.print(" ");
			pintaFila(tablero, ancho, '-', 0, false, false);
			if ((j + 1) < 10)
				System.out.print(" ");
			System.out.print(j + 1);
			pintaFila(tablero, ancho, ' ', j, true, false);
		}
		System.out.print(" ");
		System.out.print(" ");
		pintaFila(tablero, ancho, '-', 0, false, false);
		// En este trozo pintamos el tablero en sí
	}

	/**
	 * Metodo para pintar la fila de un tablero. Se considera fila del tablero
	 * a dos filas de pantalla en las que una servira como delimitadora de filas
	 * y la otra será la fila de contenido con las celdas.
	 * @param t Tablero que se quiere dibujar
	 * @param ancho ancho del tablero a dibujar
	 * @param delimitador Caracter que queremos pintar. Sera un guion para delimitar las filas
	 * @param indiceX contador para saber qué fila estamos dibujando
	 * @param esCelda valor booleano que indica si la fila que pintamos es una fila de celdas(true) o delimitadora (false)
	 * @param debug valor booleano que indica si estamos pintando una fila del tableroDebug(true) o del normal(false)
	 */
	private void pintaFila(Casilla[][] tablero, int ancho, char delimitador, int indiceX,
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
					System.out.print(dameCaracterCasilla(tablero[indiceX][y]));
			else
				System.out.print(delimitador);
			System.out.print(delimitador);
			// Escribimos el delimitador varias veces, para que quede un poco
			// mas cuadrado
		}
		System.out.print("|");// añadimos el limite final del tablero

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
	public void dibujarTableroDebug(Casilla[][] tablero, int ancho, int alto) {

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
		// En este trozo pintamos la fila inicial de números con cuidado, para
		// que queden colocados

		for (int j = 0; j < alto; ++j) {
			System.out.print(" ");
			System.out.print(" ");
			pintaFila(tablero, ancho, '-', 0, false, true);
			if ((j + 1) < 10)
				System.out.print(" ");
			System.out.print(j + 1);
			pintaFila(tablero, ancho, ' ', j, true, true);
		}
		System.out.print(" ");
		System.out.print(" ");
		pintaFila(tablero, ancho, '-', 0, false, true);
		// En este trozo pintamos el tablero en sí

	}

	/**
	 * Metodo que presneta un pequeño resumen del numero de barcos tocados, hundidos e intactos
	 * que hay en el tablero al momento de su invocacion
	 * @param t Tablero que se quiere inspeccionar
	 */
	private void muestraNumBarcosDeCadaTipo(int intactos, int hundidos, int tocados) {
		
		System.out.println("");
		System.out.println("Barcos HUNDIDOS: " + hundidos);
		System.out.println("Barcos TOCADOS: " + tocados);
		System.out.println("Barcos INTACTOS: " + intactos);
	}
	
	/**
	 * Metodo que invoca a todos los metodos que muestran informacion de la partida
	 * para pintar en pantalla un resumen total del estado de la partida
	 * @param t Tablero que se esta utilizando
	 */
	private void muestraEstadoPartida (Casilla[][] tablero, int ancho, int alto, int intactos, int hundidos, int tocados) {
		
		System.out.println("");
		System.out.println("");
		dibujarTablero (tablero, ancho, alto);
		if (_debug)
			dibujarTableroDebug (tablero, ancho, alto);
		muestraNumBarcosDeCadaTipo (intactos, hundidos, tocados);
	}
	
	/**
	 * Procedimiento que se encarga de mostrar el fin de la partida y un mensaje con los disparos usados
	 * @param t Tablero que tiene la informacion que queremos consultar (la disposicion de los barcos)
	 * @param numDisparos Número de Disparos necesitados durante la partida
	 */
	private void mostrarEstadoFinal (Casilla[][] tablero, int ancho, int alto, int numDisparos) {
		
		System.out.println("");
		System.out.println("Ya has acabado la partida, y...");
		System.out.println("¡¡¡'Sólo has necesitado " + numDisparos + " disparos!!!");
		System.out.println("¡La próxima vez seguro que lo haces incluso mejor!");
		System.out.println("");
		System.out.println("La colocacion de los barcos era esta:");
		System.out.println("");
		dibujarTableroDebug (tablero, ancho, alto);
	}

	/**
	 * Funcion auxiliar que dado un tipo de Casilla, devuelve el carácter que se debe escribir
	 * @param casilla Casilla cuya representacion en carácter queremos obtener
	 * @return caracter que representa el estado de la casilla
	 */
	private char dameCaracterCasilla(Casilla casilla) {

		if (casilla == Casilla.AGUA)
			return 'a';
		if (casilla == Casilla.HUNDIDO)
			return 'H';
		if (casilla == Casilla.TOCADO)
			return 'X';

		return ' ';
	}
	
	/**
	 * Funcion que dado un tipo de barco devuelve el caracter que es necesario pintar en el tablero
	 * @param tipo Tipo de barco cuyo caracter queremos pintar.
	 * @return caracter a pintar
	 */
	private char caracterBarco(TipoBarco tipo) {

		char c = ' ';
		if (tipo == TipoBarco.ACORAZADO)
			c = 'A';
		if (tipo == TipoBarco.FRAGATA)
			c = 'F';
		if (tipo == TipoBarco.PORTAAVIONES)
			c = 'P';
		if (tipo == TipoBarco.SUBMARINO)
			c = 'S';

		return c;
	}
	
	/**
	 * Procedimiento que dado un tablero, su ancho y su alto, crea el tablero Debug
	 * y toma los datos necesarios para rellenarlo como se deba
	 * @param t Tablero que tiene la informacion que queremos consultar (la disposicion de los barcos)
	 * @param ancho Ancho del tablero de juego
	 * @param alto Alto del tablero de juego
	 */
	public void creaYRellenaTableroDebug (Tablero t, int ancho, int alto) {
		
		//creamos el tablero
		_tableroInicial = new char[alto][ancho];
		for (int i = 0; i < alto; ++i)
			for (int j = 0; j < ancho; ++j) {
				_tableroInicial[i][j] = ' ';
			}
		
		//Rellenamos el tablero Debug
		ArrayList<Barco> barcos = t.getBarcos();
		Iterator<Barco> iterador = barcos.iterator();
		while (iterador.hasNext()){
			Barco b = iterador.next();
			for (int i=0; i<Barco.getLongitud(b.getTipoBarco()); ++i){
				Posicion pos = b.getPosicion(i);
				_tableroInicial[pos.getX()][pos.getY()] = caracterBarco (b.getTipoBarco());
			}
		}
	}
	
	/**
	 * Accedente para el array que representa el tablero de debug
	 * 
	 * @return Tablero de debug
	 */
	public char[][] getTableroInicial() {
		return _tableroInicial;
	}
	
	/**
	 * Accedente para el estado de la consola (activada o no)
	 * 
	 * @return Booleano que indica si la consola está áctivada (true) o no (false)
	 */
	public boolean activada() {
		return _consolaActivada;
	}

	/**
	 * Metodo que recibe los avisos del modelo, después de haber cambiado
	 * 
	 * @param o Objeto que se observa, en nuestro caso lo que hace de modelo es la Partida
	 * @param arg Evento que se recibe (Nueva Partida, disparo, etc.) 
	 */
	public void update(Observable o, Object arg) {
		Evento e= (Evento)arg;	
		if (e.getTipo()==TipoEvento.DISPARO){
			EventoDisparo evento = (EventoDisparo)e;
			if (_consolaActivada)
				this.muestraEstadoPartida(evento.getTablero(), evento.getAncho(), evento.getAlto(), evento.getNumIntactos(), evento.getNumHundidos(), evento.getNumTocados());
		}
		else if (e.getTipo()==TipoEvento.BARCOSHUNDIDOS){
			EventoTodosBarcosHundidos evento = (EventoTodosBarcosHundidos)e;
			if (_consolaActivada){
				this.mostrarEstadoFinal(evento.getTablero(), evento.getAncho(), evento.getAlto(), evento.getNumDisparos());
				this.mostrarPuntuaciones(evento.getPuntuacion());
			}
		}		
	}
}
