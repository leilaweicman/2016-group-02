package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

public class PoiHelper {
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
	
	//Falta definir que pasa en comercio y en el banco
	//y entender que significa que esten etiquetados mediante una palabra clave
	public List<POI> Busqueda (String txtABuscar){
		List<POI> result = new ArrayList<POI>();
		for(POI poi : _POIs){
			if(poi.Busqueda(txtABuscar)){
				result.add(poi);
			}
		}
		return result;
	}
}
