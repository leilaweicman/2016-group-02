package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

public class RegistrarDatosBusqueda {
	private List<DatosDeBusqueda> listaDatosDeBusqueda = new ArrayList<DatosDeBusqueda>();

	public void agregarDatosBusqueda(DatosDeBusqueda registroBusqueda) {

		this.listaDatosDeBusqueda.add(registroBusqueda);
	}

	public List<DatosDeBusqueda> consultarDatos() {
		return this.listaDatosDeBusqueda;
	}
}