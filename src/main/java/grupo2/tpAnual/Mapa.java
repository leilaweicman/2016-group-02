package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;

import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;


public class Mapa {

	private List<POI> pois;
	private List<OrigenesDeDatos> origenesDeDatos;
	private List<ObserverBusqueda> observersBusqueda;
	private long tiempoMaximoDeEjecucion;
	private String nombre;
	
	public Mapa() {
		pois = new ArrayList<POI>();
		observersBusqueda = new ArrayList<ObserverBusqueda>();
		origenesDeDatos = new ArrayList<OrigenesDeDatos>();
	}
	

	
	public List<POI> getPOIs() {
		return pois;
	}

	public void setOrigenesDeDatos(OrigenesDeDatos integracion) {
		this.origenesDeDatos.add(integracion);
	}

	public void agregarPOI(POI poi) {
		pois.add(poi);
	}

	public void crearPOI(POI poi) {
		this.pois.add(poi);
	}

	public void darDeBajaPOI(POI nombre) {
		pois.remove(nombre);
	}

	public void modificarUnPOI(POI poi, String atributo, String valorAtributo) {
		// No hacemos nada hastano tener la UI

	}

	public String consultarPoi(POI poi, String atributo) {
		switch (atributo) {
		case "Direccion":
			return poi.getDireccion().getCalle();
		case "Ubicacion":
			return poi.getUbicacion().toString();
		case "Comuna":
			return String.valueOf(poi.getComuna().getNumeroComuna());
		default:
			return "No se encontró attributo";
		}
	}

	public List<POI> busquedaRealizadaPorElUsuario(String txtABuscar) {
		long tiempoInicio = System.currentTimeMillis();
		List<POI> result = new ArrayList<POI>();
		

		//busco en mis pois
		result.addAll(this.pois.stream().filter(poi -> poi.verificarPorTexto(txtABuscar)).collect(Collectors.toList()));
					
		//busco en los servicios externos
		this.origenesDeDatos.forEach(integracion -> result.addAll(integracion.busqueda(txtABuscar)));
		long tiempoFin = System.currentTimeMillis();
	
		//mido el tiempo de ejecucion y lo paso a segundos
		long segundosTardados=(tiempoFin- tiempoInicio)/1000;

		
		//le aviso a los observers de que ocurrio el evento
		DatosDeBusqueda datosParaObserver = new DatosDeBusqueda(this.nombre, txtABuscar,segundosTardados,this.tiempoMaximoDeEjecucion,result.size(), new LocalDate());
		this.observersBusqueda.forEach(observer-> observer.notificarBusqueda(datosParaObserver));
		
		return result;
	}
	
	public void agregarObserverBusqueda(ObserverBusqueda observer){
		this.observersBusqueda.add(observer);
	}
	
	public void quitarObserverBusqueda(ObserverBusqueda observer){
		this.observersBusqueda.remove(observer);
	}
	
	public void setTiempoMaximoDeEjecucion(int tiempoEnSegundos){
		this.tiempoMaximoDeEjecucion = tiempoEnSegundos;
	}
	public long getTiempoMaximoDeEjecucion(){
		return this.tiempoMaximoDeEjecucion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
