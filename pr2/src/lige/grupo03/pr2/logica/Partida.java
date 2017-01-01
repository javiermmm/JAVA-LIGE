//Partida.java

package lige.grupo03.pr2.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import lige.grupo03.pr2.jugador.JugadorHumano;
import lige.grupo03.pr2.logica.Constantes.Direccion;
import lige.grupo03.pr2.logica.Constantes.TipoBarco;
import lige.grupo03.pr2.salida.UIConsola;

/**
 * Clase que modela lo que seria una partida del juego de los barquitos
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Tablero
 * @see UIConsola
 */
public class Partida {

	//---------------------------
	//ATRIBUTOS
	//---------------------------
	private UIConsola _consola;
	private Tablero _tablero;
	private int _numDisparos;
	private Random _random;
	//---------------------------

	/**
	 * Constructor de una partida, a partir de la salida por consola, 
	 * el ancho y el alto del tablero
	 * @param consola salida por medio de la cual se mostrarán por pantalla los detalles de la partida
	 * @param anchoTablero ancho del tablero de la partida
	 * @param altoTablero alto del tablero de la partida            
	 */
	public Partida(UIConsola consola, int anchoTablero, int altoTablero) {

		_random = new Random();
		_random.setSeed(new Date().getTime());
		_consola = consola;
		_tablero = new Tablero(anchoTablero, altoTablero);
		_numDisparos = 0;
	}

	/**
	 * Funcion que cuenta el número de casillas ocupadas por barcos.
	 * Lo normal es que sea un número fijo de casillas (1*4 +2*3 + 3*2 + 4*1 = 20), pero
	 * esta funcion es adicional, pos si quisieramos cambiar las reglas en cuanto a los barcos.
	 * @return número de casillas ocupadas por barcos
	 */
	private int casillasOcupadasPorBarcos() {

		int contadorCasillas = 0;
		Iterator<Barco> iterador = _tablero.getBarcos().iterator();
		while (iterador.hasNext()) {
			Barco b = iterador.next();
			contadorCasillas += Barco.getLongitud(b.getTipoBarco());
		}
		return contadorCasillas;
	}

	/**
	 * Funcion auxiliar cuyo único cometido es encapsular el cálculo del incremento usado
	 * para calcular la puntuacion
	 * @return numero utilizado como incremento en el calculo de la puntuacion
	 */
	private float incremento() {

		return (1 / (float)(_tablero.getAlto() * _tablero.getAncho())); 
		//En esta línea es imprescindible el casting, para que se realice la division real.
	}

	/**
	 * Funcion que encapsula el cálculo de la puntuacion
	 * @return puntuacion obtenida en la partida
	 */
	public float calculaPuntuacion() {

		return 1 - ((_numDisparos - casillasOcupadasPorBarcos()) * incremento());
	}

	/**
	 * Preparacion de la partida consistente fundamentalmente en 
	 * la colocacion de los barcos en el tablero
	 * @param ancho ancho del tablero de juego
	 * @param alto alto del tablero de juego
	 */
	public void prepararPartida(int ancho, int alto) {

		Barco barco = null;
		//Colocamos los submarinos
		colocaBarcosDelTipo(Constantes.NUM_SUBMARINOS, TipoBarco.SUBMARINO, barco, ancho, alto);
		
		//colocamos las fragatas
		colocaBarcosDelTipo(Constantes.NUM_FRAGATAS, TipoBarco.FRAGATA, barco, ancho, alto);
		
		//colocamos los acorazados
		colocaBarcosDelTipo(Constantes.NUM_ACORAZADOS, TipoBarco.ACORAZADO, barco, ancho, alto);
		
		//colocamos los portaaviones
		colocaBarcosDelTipo(Constantes.NUM_PORTAAVIONES, TipoBarco.PORTAAVIONES, barco, ancho, alto);
		
		_consola.creaYRellenaTableroDebug(_tablero, ancho, alto);
	}
	
	/**
	 * Procedimiento para colocar todos los barcos de un determinado tipo
	 * @param numBarcosTipo numero de barcos a colocar del tipo que sea
	 * @param tipo Tipo de barco que estamos colocando
	 * @param barco Objeto de clase barco que vamos a crear
	 * @param ancho ancho del tablero de juego
	 * @param alto alto del tablero de juego
	 * @param longitud Longitud del tipo de barco que estamos tratando de crear-colocar
	 */
	private void colocaBarcosDelTipo (int numBarcosTipo, TipoBarco tipo, Barco barco, int ancho, int alto) {
		
		int i=0;
		while (i<numBarcosTipo ){
			Posicion pos = generaPosAleatoria(ancho, alto);
			Direccion dir = generaDirAleatoria();
			if (_tablero.seHaPodidoColocarBarco (tipo, pos, dir, Barco.getLongitud(tipo)))
				i++;
		}
	}

	/**
	 * Funcion para la generacion aleatoria de las coordenadas de una posicion y construccion de la misma
	 * @param ancho ancho del tablero de juego
	 * @param alto alto del tablero de juego
	 * @return Posicion aleatoria generada
	 */
	private Posicion generaPosAleatoria (int ancho, int alto){
	
		int coordenadaX = _random.nextInt(ancho);
		int coordenadaY = _random.nextInt(alto);
		
		return new Posicion (coordenadaX, coordenadaY);
	}
	
	/**
	 * Funcion para la generacion aleatoria de una direccion
	 * @return Direccion aleatoria generada
	 */
	private Direccion generaDirAleatoria (){
		
		int direccion = _random.nextInt(4); // 0=Norte, 1=Este, 2=Sur, 3=Oeste
		return numeraDireccion(direccion);
	}
	
	/**
	 * Funcion auxiliar que decodifica las direcciones a partir de enteros, 
	 * para facilitar la generacion aleatoria. La codificacion es: 
	 * 0=Norte, 1=Este, 2=Sur, 3=Oeste; siguiendo el sentido de las agujas del reloj
	 * @param dir entero que representa la direccion
	 * @return Direccion resultante en formato Direccion (enumerado)
	 */
	private Direccion numeraDireccion(int dir) {

		if (dir == 0)
			return Direccion.NORTE;
		if (dir == 1)
			return Direccion.ESTE;
		if (dir == 2)
			return Direccion.SUR;
		if (dir == 3)
			return Direccion.OESTE;

		return null;
	}
	
	/**
	 * Funcion que comprueba si se ha llegado al final del juego (de la partidad, en realidad)
	 * @return valor booleano que indica si todos los barcos estan hundidos (true) o no (false)
	 */
	public boolean finJuego(ArrayList<Barco> barcos) {

		Iterator<Barco> iterador = barcos.iterator();
		while (iterador.hasNext()) {
			Barco b = iterador.next();
			if (!b.hundido())
				return false;
		}
		return true;
	}
	
	/**
	 * Con esta funcion se juega, es decir, se establece el esquema de juego 
	 * (1º-mostrar tablero/s, 2º-disparar N veces, 3º-mostrar resumen de partida)
	 * @param modoDebug Verdadero si se usó el parametro -d, Falso e.o.c.  
	 * @param ancho ancho del tablero de juego
	 * @param alto alto del tablero de juego
	 */
	public void jugar(boolean modoDebug, int ancho, int alto) {

		Posicion disparo;
		prepararPartida(ancho, alto);
		JugadorHumano jugador = new JugadorHumano();
	
		do{
			_consola.muestraEstadoPartida(_tablero,modoDebug);
			disparo = jugador.escogeMovimiento(_tablero, _consola);
			_tablero.dispara(disparo);
			_numDisparos++;	
		}while (!finJuego(_tablero.getBarcos()));
		
		_consola.mostrarEstadoFinal(_tablero, _numDisparos);
		_consola.mostrarPuntuaciones(calculaPuntuacion());
		
	}

}
