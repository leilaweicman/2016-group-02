package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa implements Administrador {

	private List<POI> poiList;
	private List<Integracion> integracionesBusquedasExternas;
	Map<String, POI> conversorAPoi = new HashMap<>();

	public Mapa() {
		poiList = new ArrayList<POI>();
		integracionesBusquedasExternas = new ArrayList<Integracion>();
		List<Rango> listaRango = new ArrayList<>();
		conversorAPoi.put("Banco", new Banco());
		conversorAPoi.put("CGP", new CGP());
		conversorAPoi.put("Comercio", new Comercio(listaRango));
		conversorAPoi.put("Parada",new Parada());
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
	if(this.conversorAPoi.containsKey(nombre)) this.poiList.add(this.conversorAPoi.get(nombre));	
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
