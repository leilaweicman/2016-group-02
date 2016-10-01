package grupo2.tpAnual.Observers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import grupo2.tpAnual.Repositorios.DatosBusquedaRepository;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;

@Entity
public class NotificarDatosBusqueda extends ObserverBusqueda {

	@Transient
	private DatosBusquedaRepository register;

	public NotificarDatosBusqueda() {
		this.register = new DatosBusquedaRepository();
		this.id=1;
	}

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		this.register.agregarDatosBusqueda(datosParaObserver);
	}

	public DatosBusquedaRepository getRegister() {
		return this.register;
	}
}
