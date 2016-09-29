package grupo2.tpAnual.OrigenesDeDatos;

import java.util.List;

import grupo2.tpAnual.POI;

public interface OrigenesDeDatos {
	
	public abstract List<POI> busqueda(String txtABuscar);
}
