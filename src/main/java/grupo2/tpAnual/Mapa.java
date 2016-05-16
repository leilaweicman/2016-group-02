package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;


public class Mapa{

	private List<POI> poiList;
	private List<BusquedaAvanzada> integracionesDeBusqueda = new ArrayList<BusquedaAvanzada>();
	public Mapa() {
		poiList = new ArrayList<POI>();
		CentroDTO centroDto = new CentroDTO();
		//integracionesDeBusqueda.add(centroDto);
	}

	public List<POI> getPOIs() {
		return poiList;
	}

	public void agregarPOI(POI poi) {
		poiList.add(poi);
	}

	/*public List<POI> busquedaIntegradora(String txtABuscar){
		integracionesDeBusqueda.forEach(integracion -> integracion.busqueda(txtABuscar));
	}*/

}
