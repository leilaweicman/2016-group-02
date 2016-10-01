package grupo2.tpAnual.OrigenesDeDatos;

import java.util.List;

import grupo2.tpAnual.Pois.POI;

public interface OrigenesDeDatosPOIs extends OrigenesDeDatos{
	
	public List<POI> getPOIs();
	
	public void agregarPOI(POI poi);
	
	public abstract List<POI> busqueda(String txtABuscar);
	
	public void darDeBajaPOI(Integer id);

}
