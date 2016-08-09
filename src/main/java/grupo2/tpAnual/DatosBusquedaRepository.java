package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

public class DatosBusquedaRepository {
	private List<DatosDeBusqueda> listaDatosDeBusqueda;

	public DatosBusquedaRepository() {
		this.listaDatosDeBusqueda = new ArrayList<DatosDeBusqueda>();
	}

	public void agregarDatosBusqueda(DatosDeBusqueda registroBusqueda) {
		this.listaDatosDeBusqueda.add(registroBusqueda);
	}

	public List<DatosDeBusqueda> consultarDatos() {
		return this.listaDatosDeBusqueda;
	}
}