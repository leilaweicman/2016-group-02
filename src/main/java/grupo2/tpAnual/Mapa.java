package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo2.tpAnual.Integraciones.Integracion;
import grupo2.tpAnual.Integraciones.IntegracionBancoExterno;
import grupo2.tpAnual.Integraciones.IntegracionCentroDTO;

public class Mapa {

	private List<POI> pois;
	private List<Integracion> origenesDeDatos;
	private List<ObserverBusqueda> observersBusqueda;
	private long tiempoMaximoDeEjecucion;
	
	public Mapa() {
		pois = new ArrayList<POI>();
		observersBusqueda = new ArrayList<ObserverBusqueda>();
		origenesDeDatos = new ArrayList<Integracion>();
		origenesDeDatos.add(new IntegracionBancoExterno());
		origenesDeDatos.add(new IntegracionCentroDTO());
	}
	
	public List<POI> getPOIs() {
		return pois;
	}

	public void setOrigenesDeDatos(Integracion integracion) {
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
		long tiempoInicio = System.currentTimeMillis();//mido el tiempo de ejecución
		List<POI> result = new ArrayList<POI>();
		
		//busco en mis pois
		result.addAll(this.pois.stream().filter(poi -> poi.verificarPorTexto(txtABuscar)).collect(Collectors.toList()));
					
		//busco en los servicios externos
		this.origenesDeDatos.forEach(integracion -> result.addAll(integracion.busqueda(txtABuscar)));
	
		long tiempoFin = System.currentTimeMillis();//mido el tiempo de ejecución
		long segundosTardados=(tiempoFin- tiempoInicio)/1000; //lo paso a segundos
		
		//le aviso a los observers de que ocurrio el evento
		DatosDeBusqueda datosParaObserver = new DatosDeBusqueda(txtABuscar,segundosTardados,this.tiempoMaximoDeEjecucion,result.size());
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

}
