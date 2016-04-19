package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class PoiHelper {
	private Point miCoordenada;
	private List<POI> _POIs;
	public PoiHelper(){
		_POIs = new ArrayList<POI>();
	}
	public List<POI> getPOIs(){
		return _POIs;
	}
		
	public void agregarPOI(POI p){
		_POIs.add(p);
	}
	
	public List<POI> Busqueda (String txtABuscar){
		List<POI> result = new ArrayList<POI>();
		for(POI poi : _POIs){
			if(poi.Busqueda(txtABuscar)){
				result.add(poi);
			}
		}
		return result;
	}
	public void setMiCoordenada(double latitud, double longitud){
		this.miCoordenada=Point.and(latitud, longitud);
		
	}
	// le pregunta al poi correspondiente que calcule la cercania
	public boolean estanCerca(POI p, Point coordenadaDeseada){
		return p.estaCerca(coordenadaDeseada);
	}
}

