package grupo2.tpAnual.Repositorios;

import java.util.List;

import grupo2.tpAnual.Observers.ObserverBusqueda;


public interface ObserversRepository {
	
	public void deleteObserver(ObserverBusqueda observer);
	
	public void saveObserver(ObserverBusqueda observer);

	public List<ObserverBusqueda> getObservers();
	
	public ObserverBusqueda getObserverByName(String name);

}
