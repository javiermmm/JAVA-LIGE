//Partida.java

package lige.Grupo03.pr5.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

import lige.Grupo03.pr5.modelo.Constantes.Casilla;
import lige.Grupo03.pr5.modelo.Constantes.Direccion;
import lige.Grupo03.pr5.modelo.Constantes.TipoBarco;
import lige.Grupo03.pr5.vista.UIConsola;
import lige.Grupo03.pr5.vista.Eventos.EventoDisparo;
import lige.Grupo03.pr5.vista.Eventos.EventoNuevaPartida;
import lige.Grupo03.pr5.vista.Eventos.EventoTerminarPartida;
import lige.Grupo03.pr5.vista.Eventos.EventoTodosBarcosHundidos;

/**
 * Clase que modela lo que seria una partida del juego de los barquitos
 * @author Jessica 	Martin & Javier Martin
 * @version 4.0
 * @see Tablero
 * @see UIConsola
 */
public class Partida extends Observable {

	//---------------------------
	//ATRIBUTOS
	//---------------------------
	private UIConsola _salida;
	private Tablero _tablero;
	private int _numDisparos;
	private boolean _dispersion;
	private boolean _sonico;
	private int _numDispersiones;
	private int _numSonicos;
	//---------------------------

	/**
	 * Constructor de una partida, a partir de la salida por consola
	 * @param salida salida por medio de la cual se mostrarán por pantalla los detalles de la partida
	 */
	public Partida(UIConsola salida) {

		_salida = salida;
		_numDisparos = 0;
		_dispersion = false;
		_sonico = false;
		_numDispersiones = 2;
		_numSonicos = 4;
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
	private float calculaPuntuacion() {

		return 1 - ((_numDisparos - _tablero.casillasOcupadasPorBarcos()) * incremento());
	}

	/**
	 * Preparacion de la partida consistente fundamentalmente en 
	 * la colocacion de los barcos en el tablero
	 * @param ancho ancho del tablero de juego
	 * @param alto alto del tablero de juego
	 */
	public void prepararPartida(int ancho, int alto, boolean modoDebug, int opJugador) {

		_tablero = new Tablero(ancho, alto);
		_numDisparos = 0;
		_numDispersiones = 2;
		_numSonicos = 4;
	
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
		_salida.dibujarTableroDebug(_tablero.getCuadricula(), _tablero.getAncho(), _tablero.getAlto());
		
		this.setChanged();
		this.notifyObservers(new EventoNuevaPartida(ancho, alto, _salida.getTableroInicial(), modoDebug));
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
	public boolean finJuego() {
		return _tablero.todosBarcosHundidos();
	}

	/**
	 * Metodo que avisa a la vista de que el jugador ha decidido terminar la partida 
	 * sin haber hundido todos los barcos
	 */
	public void terminaPartida() {
		this.setChanged();
		this.notifyObservers(new EventoTerminarPartida());
	}

	/**
	 * Metodo que realiza el disparo sobre el tablero y aumenta el numero de disparos 
	 * sin haber hundido todos los barcos
	 * @param disparo Posicion sobre la que se dispara
	 */
	public void disparaNormal(Posicion disparo) {
		_numDisparos++;	
		_tablero.dispara(disparo);	
	}

	/**
	 * Metodo que activa/desactiva el disparo de dispersion (segun clicks del usuario) 
	 */
	public void activaDispersion() {
		_sonico = false;
		_dispersion = !_dispersion;
	}

	/**
	 * Metodo que activa/desactiva el disparo sónico (segun clicks del usuario) 
	 */
	public void activaSonico() {
		_dispersion = false;
		_sonico = !_sonico;
	}
	
	/**
	 * Metodo privado que encapsula el aviso que se le da a las vistas cuando todos los
	 * barcos han sido hundidos 
	 */
	private void avisaFinJuego (){
		if (finJuego()){
			this.setChanged();
			this.notifyObservers(new EventoTodosBarcosHundidos(_tablero.getCuadricula(), calculaPuntuacion(), _numDisparos, _tablero.getAncho(), _tablero.getAlto()));
		}
	}
	
	/**
	 * Metodo privado que encapsula el aviso que se le da a las vistas cuando se realiza
	 * un disparo (sea del tipo que sea).
	 * @param posicionesAfectadas Array de posiciones afectadas por el disparo hecho.
	 * @param disparo Posicion objetivo en la que se dispara.
	 */
	private void avisaDisparo(Posicion[] posicionesAfectadas, Posicion disparo) {
		this.setChanged();
		this.notifyObservers(new EventoDisparo(posicionesAfectadas, disparo, _tablero.getCuadricula(), _tablero.getAncho(), 
				_tablero.getAlto(), barcosIntactos(), _tablero.getNumTocados(), _tablero.getNumHundidos(), _sonico, _dispersion, 
				_numDisparos, _numDispersiones, _numSonicos));
	}
	
	/**
	 * Metodo privado que encapsula los avisos que se le hacen a la vista cuando se efectua
	 * un disparo del tipo que sea, teniendo en cuenta que puede ser el ultimo y por ello
	 * se debe acabar la partida, o no.
	 * @param posicionesAfectadas Array de posiciones afectadas por el disparo hecho.
	 * @param disparo Posicion objetivo en la que se dispara.
	 */
	private void avisaVista(Posicion[] posicionesAfectadas, Posicion disparo) {
		avisaDisparo(posicionesAfectadas, disparo);
		avisaFinJuego();
	}
	/**
	 * Funcion privada que calcula los barcos intactos
	 * @return Entero que identifica el numero de barcos intactos 
	 */
	private int barcosIntactos(){
		return (_tablero.getBarcos().size() - _tablero.getNumHundidos() - _tablero.getNumTocados());
	}
	
	/**
	 * Función que efectúa el disparo según sea de dispersion, sónico o normal.
	 * @param disparo Posicion sobre la que se realiza el disparo; 
	 */
	public void dispara (Posicion disparo) {
		
		if (_sonico){
			if (_numSonicos >0)
				avisaVista(disparaSonico(disparo), disparo);
			_sonico = false;
		}
		else
			if (_dispersion){
				if (_numDispersiones > 0)
					avisaVista(disparaDispersion(disparo), disparo);
				_dispersion = false;
			}
			else
				if (! _dispersion && !_sonico){
				 	disparaNormal(disparo);
				 	Barco b = _tablero.hayBarco(disparo);
					Posicion[] posicionesBarco = null;
					if (b != null)
						posicionesBarco = b.getPosicionesOcupadas();
					avisaVista(posicionesBarco, disparo);
				}
	}

	/**
	 * Funcion privada que realiza el disparo sónico en sí mismo. Un disparo sónico
	 * utiliza el disparo normal cuando ha detectado submarino
	 * @param disparo Posicion objetivo en la que se dispara.
	 * @return Array de posiciones que contiene las posiciones afectadas en el disparo
	 */
	private Posicion[] disparaSonico(Posicion disparo) {
		Posicion disparoAux = new Posicion();
		ArrayList<Posicion> posAfectadas = new ArrayList<Posicion>();
		int i = -1;
		while (i < 2) {
			int j = -1;
			while (j < 2) {
				disparoAux.setX(disparo.getX() + i);
				disparoAux.setY(disparo.getY() + j);
				if (! _tablero.fuera(disparoAux)) {
					Barco b = _tablero.hayBarco(disparoAux);
					if (b != null)
						if (b.getTipoBarco() == TipoBarco.SUBMARINO) {
							disparaNormal(disparoAux);
							posAfectadas.add(disparoAux.copia());
						}
				}
				j++;
			}
			i++;
		}
		_numSonicos--;
		if (posAfectadas.size() > 1)
			_numDisparos -= posAfectadas.size()-1;
		return arrayListToArray(posAfectadas);
	}
	
	/**
	 * Funcion privada que realiza el disparo de dispersión en sí mismo. Un disparo de 
	 * dispersion utiliza el disparo el la casilla elegida y las que la rodean
	 * @param disparo Posicion objetivo en la que se dispara.
	 * @return Array de posiciones que contiene las posiciones afectadas en el disparo
	 */
	private Posicion[] disparaDispersion(Posicion disparo) {		
		Posicion disparoAux = new Posicion();
		ArrayList<Posicion> posAfectadas = new ArrayList<Posicion>();
		int i = -1;
		while (i < 2) {
			int j = -1;
			while (j < 2) {
				disparoAux.setX(disparo.getX() + i);
				disparoAux.setY(disparo.getY() + j);
				if (! _tablero.fuera(disparoAux)){
					disparaNormal(disparoAux);
					if (_tablero.getEstadoCasilla(disparoAux.getX(), disparoAux.getY()) == Casilla.HUNDIDO){
						Barco b = _tablero.hayBarco(disparoAux);
						for (int k=0; k<b.getPosicionesOcupadas().length; k++) {
							if (! contiene(posAfectadas, b.getPosicion(k)))
								posAfectadas.add(b.getPosicion(k));
						}
					}
					else 
						posAfectadas.add(disparoAux.copia());
				}
				j++;
			}
			i++;
		}
		_numDispersiones--;
		_numDisparos-=8;
		return arrayListToArray(posAfectadas);
	}

	/**
	 * Funcion privada que comprueba si una posicion se encuentra en un arrayList o no
	 * @param pos Posicion que vamos a buscar.
	 * @param posiciones ArrayList de posiciones en el que vamos a buscar
	 * @return Booleano que indica si la posicion esta (true) o no (false)
	 */
	private boolean contiene(ArrayList<Posicion> posiciones, Posicion pos) {
		Iterator<Posicion> iteradorPos = posiciones.iterator();
		Posicion p = null;
		while (iteradorPos.hasNext()) {
			p = iteradorPos.next();
			if (p.equals(pos))
				return true;
		}
		return false;
	}
	
	/**
	 * Funcion privada que vuelca en un array el contenido de un arrayList
	 * @param posAfectadas ArrayList de posiciones que vamos a "convertir"
	 * @return Array de posiciones resultante.
	 */
	private Posicion[] arrayListToArray(ArrayList<Posicion> posAfectadas) {
		Iterator<Posicion> iteradorPos = posAfectadas.iterator();
		Posicion[] posicionesModificadas= new Posicion[posAfectadas.size()];
		int i = 0;
		while (iteradorPos.hasNext()){
			posicionesModificadas[i] = iteradorPos.next();
			i++;
		}
		return posicionesModificadas;
	}

	/**
	 * Accedente para el numero de disparos
	 * @return Entero con el numero de disparos realizados
	 */
	public int getNumDisparos() {
		return _numDisparos;
	}

	/**
	 * Accedente para el numero de disparos de dispersion restantes
	 * @return Entero con el numero de disparos de dispersion restantes
	 */
	public int getDispersiones() {
		return _numDispersiones;
	}
	
	/**
	 * Accedente para el numero de disparos sónicos restantes
	 * @return Entero con el numero de disparos sónicos restantes
	 */
	public int getSonicos() {
		return _numSonicos;
	}
	
	/**
	 * Accedente para el tablero de juego. Necesario para el control de la IA.
	 * @return Tablero de la partida
	 */
	public Tablero getTablero() {
		return _tablero;
	}
}
