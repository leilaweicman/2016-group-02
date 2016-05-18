package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa implements Administrador {

	private List<POI> poiList;
	private List<BusquedaAvanzada> integracionesDeBusqueda = new ArrayList<BusquedaAvanzada>();
	Map<String, Boolean> conversorAPoi = new HashMap<>();
	public Mapa() {
		poiList = new ArrayList<POI>();
		// integracionesDeBusqueda.add(centroDto);
	}

	public List<POI> getPOIs() {
		return poiList;
	}

	public void agregarPOI(POI poi) {
		poiList.add(poi);
	}

	/*
	 * public List<POI> busquedaIntegradora(String txtABuscar){
	 * integracionesDeBusqueda.forEach(integracion ->
	 * integracion.busqueda(txtABuscar)); }
	 */
	
	@Override
	public void crearPOI(String nombre){
		List<Rango> listaRango = new ArrayList<>();
		conversorAPoi.put("Banco", poiList.add(new Banco()));
		conversorAPoi.put("CGP", poiList.add(new CGP()));
		conversorAPoi.put("Comercio",poiList.add(new Comercio(listaRango)));
		conversorAPoi.put("Parada", poiList.add(new Parada()));	
	}
	
	public void darDeBajaPOI(POI nombre) {
		poiList.remove(nombre);
	}

	@Override
	public void modificarUnPOI(POI poi, String atributo, String valorAtributo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public POI consultarPoi(POI nombre) {
		return nombre;
	}



}
