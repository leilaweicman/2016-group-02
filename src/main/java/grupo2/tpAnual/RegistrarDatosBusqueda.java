package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

public class RegistrarDatosBusqueda {
	private List<DatosDeBusqueda> listaDatosDeBusqueda= new ArrayList<DatosDeBusqueda>();
	
	public void agregarDatosBusqueda(List<DatosDeBusqueda> registroBusqueda) {
		
		this.listaDatosDeBusqueda.addAll(registroBusqueda);
	}

	public List<DatosDeBusqueda> consultarDatos(){
		return this.listaDatosDeBusqueda;
}
}