package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;

public class Mapa {
	private List<OrigenesDeDatos> origenesDeDatos;
	private List<ObserverBusqueda> observersBusqueda;
	private String nombre;
	private Usuario usuario;
	
	public Mapa(List<OrigenesDeDatos> listaDeOrigenes) {
		observersBusqueda = new ArrayList<ObserverBusqueda>();
		origenesDeDatos = new ArrayList<OrigenesDeDatos>();
		listaDeOrigenes.forEach(origen -> this.agregarOrigenesDeDatos(origen));
		
	}
	// desacoplo la lista de pois de mapa. Ahora el conjunto de pois pasa a ser
	// otro origen de dato mas
	// y el mapa solo se encarga de buscar y los observers y esas cosas

	public void agregarOrigenesDeDatos(OrigenesDeDatos integracion) {
		this.origenesDeDatos.add(integracion);
	}

	public void sacarOrigenesDeDatos(OrigenesDeDatos integracion) {
		this.origenesDeDatos.remove(integracion);
	}

	public List<POI> busquedaRealizadaPorElUsuario(String txtABuscar) {
		long tiempoInicio = System.currentTimeMillis();
		List<POI> result = new ArrayList<POI>();
		
		this.observersBusqueda.addAll(usuario.enviarObservers());
		
		// busco en los servicios externos y mis pois (que forma parte de un
		// origen de dato)
		this.origenesDeDatos.forEach(integracion -> result.addAll(integracion.busqueda(txtABuscar)));
		long tiempoFin = System.currentTimeMillis();

		// mido el tiempo de ejecucion y lo paso a segundos
		long segundosTardados = (tiempoFin - tiempoInicio) / 1000;

		// le aviso a los observers de que ocurrio el evento
		DatosDeBusqueda datosParaObserver = new DatosDeBusqueda(this.nombre, txtABuscar, segundosTardados,
				result.size(), new LocalDate());
		this.observersBusqueda.forEach(observer -> observer.notificarBusqueda(datosParaObserver));

		return result;
	}

	
	public List<OrigenesDeDatos> getOrigenesDeDatos() {
		return this.origenesDeDatos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setUsuario(Usuario user){
		this.usuario=user;
	}
}
