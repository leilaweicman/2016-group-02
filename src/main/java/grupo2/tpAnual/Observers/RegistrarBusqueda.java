package grupo2.tpAnual.Observers;

import java.util.ArrayList;
import java.util.List;

public class RegistrarBusqueda implements ObserverBusqueda {
	private List<DatosDeBusqueda> registroBusqueda = new ArrayList<DatosDeBusqueda>();

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		this.registroBusqueda.add(datosParaObserver);
		// No sé si crear una nueva clase que tenga los atributos que necesita
		// registrar, o que almacene todos, o hacer una tupla con los datos q
		// necesito almacenar
	}
	
	public List<DatosDeBusqueda> getRegistroBusqueda(){
		return this.registroBusqueda;
	}
}
