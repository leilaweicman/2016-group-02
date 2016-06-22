package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo2.tpAnual.POI;

public class OrigenesDeDatosPOIs implements OrigenesDeDatos {
	private List<POI> pois;

	public OrigenesDeDatosPOIs() {
		this.pois = new ArrayList<POI>();
	}

	public List<POI> getPOIs() {
		return pois;
	}

	public void agregarPOI(POI poi) {
		pois.add(poi);
	}

	public void darDeBajaPOI(POI nombre) {
		pois.remove(nombre);
	}

	public void modificarUnPOI(POI poi, String atributo, String valorAtributo) {
		// No hacemos nada hastano tener la UI

	}

	@Override
	public List<POI> busqueda(String txtABuscar) {
		List<POI> resultados = new ArrayList<POI>();
		resultados.addAll(this.pois.stream().filter(poi -> poi.verificarPorTexto(txtABuscar)).collect(Collectors.toList()));
		return resultados;
	}

}
