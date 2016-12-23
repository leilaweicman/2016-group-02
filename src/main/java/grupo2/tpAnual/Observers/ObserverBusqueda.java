package grupo2.tpAnual.Observers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import grupo2.tpAnual.Repositorios.DatosDeBusqueda;
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS )
public abstract class ObserverBusqueda{
	
	@Id
	protected long id;
	
	public abstract void notificarBusqueda(DatosDeBusqueda datosParaObserver);
}
