//EventoSobreAbrirYNuevo.java

package lige.grupo03.pr6.editor.vista.Eventos;

/**
 * Clase intermedia que declara los metodos comunes a los eventos de Abrir y de Nuevo Escenario
 * 
 * @author Jessica Martin & Javier Martin
 * @version 1.0
 * @see Evento
 */
public abstract class EventoSobreAbrirYNuevo extends Evento{

	//MÉTODOS ABSTRACTOS
	public abstract int getAncho();
	public abstract int getAlto();
}
