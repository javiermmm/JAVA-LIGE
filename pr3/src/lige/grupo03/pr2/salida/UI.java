//UI.java

package lige.grupo03.pr2.salida;

import java.util.ArrayList;
import java.util.Iterator;

import lige.grupo03.pr2.logica.Barco;
import lige.grupo03.pr2.logica.Posicion;
import lige.grupo03.pr2.logica.Tablero;
import lige.grupo03.pr2.logica.Constantes.Casilla;
import lige.grupo03.pr2.logica.Constantes.TipoBarco;

/**
 * Clase abstracta que define el comportamiento general de una UI, e implementa algunos métodos comunes en varios tipos de UI.
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Posicion
 * @see Tablero
 *
 */
public abstract class UI {

		//-------------------------------
		//ATRIBUTOS
		//-------------------------------
		protected char[][] _tableroInicial;
		protected boolean _debug;
		//-------------------------------
		
		
		
		//-------------------------------
		//Metodos Abstractos
		//-------------------------------
		public abstract int pideAltoTablero();
		public abstract Posicion pidePosicion();
		public abstract int pideAnchoTablero();
		public abstract boolean pedirJugarOtra();
		public abstract void muestraNumBarcosDeCadaTipo(Tablero t);
		public abstract void mostrarPuntuaciones(float puntuacion);
		public abstract void mostrarEstadoFinal(Tablero t, int numDisparos);
		public abstract void muestraEstadoPartida(Tablero t);
		public abstract void mostrarError(String error);
		public abstract int pideTipoJugador();
		//-------------------------------
		
		
		
		//-------------------------------
		//Metodos con implementacion
		//-------------------------------
		/**
		 * Funcion auxiliar que dado un tipo de Casilla, devuelve el carácter que se debe escribir
		 * @param casilla Casilla cuya representacion en carácter queremos obtener
		 * @return caracter que representa el estado de la casilla
		 */
		protected char dameCaracterCasilla(Casilla casilla) {

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
		protected char caracterBarco(TipoBarco tipo) {

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
	
}
