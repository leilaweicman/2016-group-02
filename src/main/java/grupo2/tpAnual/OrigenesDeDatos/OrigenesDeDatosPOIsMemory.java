package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo2.tpAnual.Pois.POI;

public class OrigenesDeDatosPOIsMemory implements OrigenesDeDatosPOIs {

	private List<POI> pois;

	public OrigenesDeDatosPOIsMemory() {
		this.pois = new ArrayList<POI>();
	}

	public List<POI> getPOIs() {
		return pois;
	}

	public void agregarPOI(POI poi) {
		pois.add(poi);
	}

	public void darDeBajaPOI(Integer id) {
		this.pois.removeIf(poi -> poi.getId().equals(id));
	}

	@Override
	public List<POI> busqueda(String txtABuscar) {
		List<POI> resultados = new ArrayList<POI>();
		resultados.addAll(
				this.pois.stream().filter(poi -> poi.verificaPorTexto(txtABuscar)).collect(Collectors.toList()));
		return resultados;
	}

}
