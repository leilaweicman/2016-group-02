package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa {

	private List<POI> poiList;
	private List<Integracion> integracionesBusquedasExternas;
	Map<String, POI> conversorAPoi = new HashMap<>();
	Map<String, Object> devolverAtributo= new HashMap<>();
	
	private List<POI> listaPOIS= new ArrayList<POI>();
	
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


	public void crearPOI(POI poi) {
	this.poiList.add(poi);	
	}

	public void darDeBajaPOI(POI nombre) {
		poiList.remove(nombre);
	}


	public void modificarUnPOI(POI poi, String atributo, String valorAtributo) {
		// No hacemos nada hastano tener la UI

	}


	public Object consultarPoi(POI nombre, String atributo) {
		devolverAtributo.put("Direccion",nombre.getDireccion()); 
		devolverAtributo.put("Ubicacion",nombre.getUbicacion());
		devolverAtributo.put("Comuna",nombre.getComuna());
		
		if(this.devolverAtributo.containsKey(atributo))	{return (this.devolverAtributo.get(atributo));}
		return atributo; //para que no tire error porque no se que devolver
		
		
	}
	
	/*public List<POI> busquedaRealizadaPorElUsuario(String txtABuscar) {
	  List<POI> result = new ArrayList<POI>();
	  for (POI poi : poiList) {
	   if (poi.verificarPorTexto(txtABuscar))
	    result.add(poi);
	  }
	  return result;
	 }*/
	
	
	public List<POI> busquedaRealizadaPorElUsuario(String txtABuscar, String servicio) {
		try{
			this.integracionesBusquedasExternas.stream().forEach(integr -> concatenarListas(integr, txtABuscar, servicio) );
			return listaPOIS;
		} catch (Exception e){
			throw new IntegracionException ("no se ha podido realizar la busqueda",e);
		}
	}
	
	private void concatenarListas(Integracion integr, String txtABuscar, String servicio){
		List<POI> lista= new ArrayList<POI>();
		lista= integr.busqueda(txtABuscar, servicio);
		listaPOIS.addAll(lista) ;
	}
}
