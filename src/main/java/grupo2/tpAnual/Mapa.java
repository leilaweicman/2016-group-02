package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class Mapa {

	private List<POI> poiList;

	public Mapa() {
		poiList = new ArrayList<POI>();
	}

	public List<POI> getPOIs() {
		return poiList;
	}

	public void agregarPOI(POI poi) {
		poiList.add(poi);
	}

	public List<POI> Busqueda(String txtABuscar) {
		List<POI> result = new ArrayList<POI>();
		for (POI poi : poiList) {
			if (poi.verificarPorTexto(txtABuscar)) {
				result.add(poi);
			}
		}
		return result;
	}

}
