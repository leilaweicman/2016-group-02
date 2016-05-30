package grupo2.tpAnual.Observers;

import java.util.ArrayList;
import java.util.List;

import grupo2.tpAnual.DatosDeBusqueda;

public class RegistrarBusqueda implements ObserverBusqueda {
	private List<DatosDeBusqueda> registroBusqueda = new ArrayList<DatosDeBusqueda>();

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		this.registroBusqueda.add(datosParaObserver);
	}
	
	public List<DatosDeBusqueda> getRegistroBusqueda(){
		return this.registroBusqueda;
	}
}
