package grupo2.tpAnual.Observers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMemory;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;

@Entity
public class NotificarDatosBusqueda extends ObserverBusqueda {

	@Transient
	private DatosBusquedaRepositoryMemory register;

	public NotificarDatosBusqueda() {
		this.register = new DatosBusquedaRepositoryMemory();
		this.id=1;
	}

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		this.register.agregarDatosBusqueda(datosParaObserver);
	}

	public DatosBusquedaRepositoryMemory getRegister() {
		return this.register;
	}
}
