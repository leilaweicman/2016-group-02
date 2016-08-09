package grupo2.tpAnual.Observers;

import grupo2.tpAnual.DatosDeBusqueda;
import grupo2.tpAnual.DatosBusquedaRepository;

public class NotificarDatosBusqueda implements ObserverBusqueda {
	private DatosBusquedaRepository register;

	public NotificarDatosBusqueda() {
		this.register = new DatosBusquedaRepository();
	}

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		this.register.agregarDatosBusqueda(datosParaObserver);
	}

	public DatosBusquedaRepository getRegister() {
		return this.register;
	}
}
