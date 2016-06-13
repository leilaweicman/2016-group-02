package grupo2.tpAnual.Observers;

import java.util.ArrayList;
import java.util.List;

import grupo2.tpAnual.DatosDeBusqueda;
import grupo2.tpAnual.RegistrarDatosBusqueda;

public class RegistrarBusqueda implements ObserverBusqueda {
	private List<DatosDeBusqueda> registroBusqueda = new ArrayList<DatosDeBusqueda>();
	private RegistrarDatosBusqueda register = new RegistrarDatosBusqueda();
	
	
	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		this.registroBusqueda.add(datosParaObserver);
		this.register.agregarDatosBusqueda(registroBusqueda);
	}
	
	public List<DatosDeBusqueda> getRegistroBusqueda(){
		return this.registroBusqueda;
	}
}
