package grupo2.tpAnual;

import java.util.List;

public class RegistrarDatosBusqueda {
	private List<DatosDeBusqueda> listaDatosDeBusqueda;
	
	public void agregarDatosBusqueda(List<DatosDeBusqueda> registroBusqueda) {
		
		this.listaDatosDeBusqueda.addAll(registroBusqueda);
	}

	public List<DatosDeBusqueda> consultarDatos(){
		return this.listaDatosDeBusqueda;
}
}