package grupo2.tpAnual.Reportes;
import java.util.List;
import java.util.ArrayList;

import grupo2.tpAnual.DatosDeBusqueda;
import grupo2.tpAnual.RegistrarDatosBusqueda;


public interface Reporte {
	RegistrarDatosBusqueda register = new RegistrarDatosBusqueda();
	List<DatosDeBusqueda> registroBusqueda = new ArrayList<DatosDeBusqueda>();


	

	
}
