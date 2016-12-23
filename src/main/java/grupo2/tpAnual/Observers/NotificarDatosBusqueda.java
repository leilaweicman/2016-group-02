package grupo2.tpAnual.Observers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import grupo2.tpAnual.MorphiaService;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMemory;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMongoDB;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;
import grupo2.tpAnual.Repositorios.DatosDeBusquedaRepository;

@Entity
public class NotificarDatosBusqueda extends ObserverBusqueda {
	
	@Transient
	MorphiaService morphia;
	@Transient
	private DatosDeBusquedaRepository register;
	public NotificarDatosBusqueda(){
		
	}
	public NotificarDatosBusqueda(boolean inMemory) {
		if(inMemory) this.register = new DatosBusquedaRepositoryMemory();
		else {
			this.register= new DatosBusquedaRepositoryMongoDB(DatosDeBusqueda.class,morphia.getDatastore());
			morphia = new MorphiaService();
		}
	}

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		this.register.agregarDatosBusqueda(datosParaObserver);
	}

	public DatosDeBusquedaRepository getRegister() {
		return this.register;
	}
}
