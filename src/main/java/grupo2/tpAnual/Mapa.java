package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import grupo2.tpAnual.Integraciones.Integracion;
import grupo2.tpAnual.Integraciones.IntegracionBancoExterno;
import grupo2.tpAnual.Integraciones.IntegracionCentroDTO;

public class Mapa {

	private List<POI> pois;
	private List<Integracion> origenesDeDatos;
	Map<String, Object> devolverAtributo = new HashMap<>();

	public Mapa() {
		pois = new ArrayList<POI>();
		origenesDeDatos = new ArrayList<Integracion>();
		List<Rango> listaRango = new ArrayList<>();
		IntegracionBancoExterno datosBancosExterno = new IntegracionBancoExterno();
		IntegracionCentroDTO datosCentroDto = new IntegracionCentroDTO();
		origenesDeDatos.add(datosBancosExterno);
		origenesDeDatos.add(datosCentroDto);
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
		switch(atributo) {
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
		List<POI> result = new ArrayList<>();
		for (POI poi : pois) {
			if (poi.verificarPorTexto(txtABuscar))
				result.add(poi);
		}
		this.origenesDeDatos.forEach(integracion-> result.addAll(integracion.busqueda(txtABuscar)) );
		//falta aplanar la lista
		return result;
	}
	/*
	 * private void concatenarListas(Integracion integr, String txtABuscar,
	 * String servicio) { List<POI> lista = new ArrayList<POI>(); lista =
	 * integr.busqueda(txtABuscar, servicio); listaPOIS.addAll(lista); }
	 */
}
