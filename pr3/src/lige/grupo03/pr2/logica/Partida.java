//Partida.java

package lige.grupo03.pr2.logica;

import java.util.Date;
import java.util.Random;

import lige.grupo03.pr2.jugador.Jugador;
import lige.grupo03.pr2.jugador.JugadorHumano;
import lige.grupo03.pr2.jugador.JugadorIA;
import lige.grupo03.pr2.jugador.JugadorIAMejor;
import lige.grupo03.pr2.logica.Constantes.Direccion;
import lige.grupo03.pr2.logica.Constantes.TipoBarco;
import lige.grupo03.pr2.salida.UI;
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
	private UI _salida;
	private Tablero _tablero;
	private int _numDisparos;
	private Jugador _jugador;
	//---------------------------

	/**
	 * Constructor de una partida, a partir de la salida por consola, 
	 * el ancho y el alto del tablero
	 * @param salida salida por medio de la cual se mostrarán por pantalla los detalles de la partida
	 * @param anchoTablero ancho del tablero de la partida
	 * @param altoTablero alto del tablero de la partida            
	 * @param opJugador 
	 */
	public Partida(UI salida, int anchoTablero, int altoTablero, int opJugador) {

		_salida = salida;
		_tablero = new Tablero(anchoTablero, altoTablero);
		_numDisparos = 0;
		if (opJugador == 1)
			_jugador = new JugadorHumano(_salida);
		if (opJugador == 2)
			_jugador = new JugadorIA();
		if (opJugador ==3)
			_jugador = new JugadorIAMejor();
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

		return 1 - ((_numDisparos - _tablero.casillasOcupadasPorBarcos()) * incremento());
	}

	/**
	 * Preparacion de la partida consistente fundamentalmente en 
	 * la colocacion de los barcos en el tablero
	 * @param ancho ancho del tablero de juego
	 * @param alto alto del tablero de juego
	 */
	public void prepararPartida(int ancho, int alto) {

		Random random = new Random();
		random.setSeed(new Date().getTime());
		Barco barco = null;
		//Colocamos los submarinos
		colocaBarcosDelTipo(Constantes.NUM_SUBMARINOS, TipoBarco.SUBMARINO, barco, ancho, alto, random);
		
		//colocamos las fragatas
		colocaBarcosDelTipo(Constantes.NUM_FRAGATAS, TipoBarco.FRAGATA, barco, ancho, alto, random);
		
		//colocamos los acorazados
		colocaBarcosDelTipo(Constantes.NUM_ACORAZADOS, TipoBarco.ACORAZADO, barco, ancho, alto, random);
		
		//colocamos los portaaviones
		colocaBarcosDelTipo(Constantes.NUM_PORTAAVIONES, TipoBarco.PORTAAVIONES, barco, ancho, alto, random);
		
		_salida.creaYRellenaTableroDebug(_tablero, ancho, alto);
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
	private void colocaBarcosDelTipo (int numBarcosTipo, TipoBarco tipo, Barco barco, int ancho, int alto, Random random) {
		
		int i=0;
		while (i<numBarcosTipo ){
			Posicion pos = generaPosAleatoria(ancho, alto, random);
			Direccion dir = generaDirAleatoria(random);
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
	private Posicion generaPosAleatoria (int ancho, int alto, Random random){
	
		int coordenadaX = random.nextInt(ancho);
		int coordenadaY = random.nextInt(alto);
		
		return new Posicion (coordenadaX, coordenadaY);
	}
	
	/**
	 * Funcion para la generacion aleatoria de una direccion
	 * @return Direccion aleatoria generada
	 */
	private Direccion generaDirAleatoria (Random random){
		
		int direccion = random.nextInt(4); // 0=Norte, 1=Este, 2=Sur, 3=Oeste
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
	public boolean finJuego(Tablero t) {

		return _tablero.todosBarcosHundidos();
	}
	
	/**
	 * Con esta funcion se juega, es decir, se establece el esquema de juego 
	 * (1º-mostrar tablero/s, 2º-disparar N veces, 3º-mostrar resumen de partida)
	 * @param ancho ancho del tablero de juego
	 * @param alto alto del tablero de juego
	 */
	public void jugar(int ancho, int alto) {

		Posicion disparo;
		prepararPartida(ancho, alto);
	
		do{
			_salida.muestraEstadoPartida(_tablero);
			disparo = _jugador.escogeMovimiento(_tablero);
			_tablero.dispara(disparo);
			_numDisparos++;	
		}while (!finJuego(_tablero));
		
		_salida.mostrarEstadoFinal(_tablero, _numDisparos);
		_salida.mostrarPuntuaciones(calculaPuntuacion());
		
	}

}
