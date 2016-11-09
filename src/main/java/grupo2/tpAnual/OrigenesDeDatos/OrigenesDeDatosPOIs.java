package grupo2.tpAnual.OrigenesDeDatos;

import java.util.List;

import javax.persistence.EntityManager;

import grupo2.tpAnual.Pois.POI;

public abstract class OrigenesDeDatosPOIs extends OrigenesDeDatos{
	
	public abstract List<POI> getPOIs();
	
	public abstract void agregarPOI(POI poi);
	
	public abstract List<POI> busqueda(String txtABuscar);
	
	public abstract void darDeBajaPOI(Integer id);

}