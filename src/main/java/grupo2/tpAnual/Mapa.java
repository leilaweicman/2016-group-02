package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa implements Administrador {

	private List<POI> poiList;
	private List<Integracion> integracionesBusquedasExternas;
	Map<Boolean, Boolean> conversorAPoi = new HashMap<>();

	public Mapa() {
		poiList = new ArrayList<POI>();
		integracionesBusquedasExternas = new ArrayList<Integracion>();
	}

	public List<POI> getPOIs() {
		return poiList;
	}

	public void setIntegracionesBusquedaExterna(Integracion integracion) {
		this.integracionesBusquedasExternas.add(integracion);
	}

	public void agregarPOI(POI poi) {
		poiList.add(poi);
	}

	@Override
	public void crearPOI(String nombre) {
		List<Rango> listaRango = new ArrayList<>();
		conversorAPoi.put(nombre == "Banco", poiList.add(new Banco()));
		conversorAPoi.put(nombre == "CGP", poiList.add(new CGP()));
		conversorAPoi.put(nombre == "Comercio", poiList.add(new Comercio(listaRango)));
		conversorAPoi.put(nombre == "Parada", poiList.add(new Parada()));
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

	/*public List<POI> busquedaRealizadaPorElUsuario(String txtABuscar) {
		List<POI> listaPOIS= new ArrayList<POI>();
		this.integracionesBusquedasExternas.forEach(integr -> integr.busqueda(txtABuscar));
		return integracionesBusquedasExternas;
	}*/

}
