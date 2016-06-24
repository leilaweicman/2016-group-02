package grupo2.tpAnual.Observers;

import grupo2.tpAnual.DatosDeBusqueda;
import grupo2.tpAnual.RegistrarDatosBusqueda;

public class NotificarDatosBusqueda implements ObserverBusqueda {
	private RegistrarDatosBusqueda register;

	public NotificarDatosBusqueda() {
		this.register = new RegistrarDatosBusqueda();
	}

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		this.register.agregarDatosBusqueda(datosParaObserver);
	}
	
	public RegistrarDatosBusqueda getRegister(){
		return this.register;
	}
}
