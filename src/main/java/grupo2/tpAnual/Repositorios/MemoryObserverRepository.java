package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo2.tpAnual.Observers.ObserverBusqueda;

public class MemoryObserverRepository implements ObserversRepository{
	
	private List<ObserverBusqueda> observers = new ArrayList<>();
	
	@Override
	public void deleteObserver(ObserverBusqueda observer) {
		this.observers.remove(observer);
	}

	@Override
	public void saveObserver(ObserverBusqueda observer) {
		this.observers.add(observer);
	}

	@Override
	public List<ObserverBusqueda> getObservers() {
		return this.observers;
	}

	@Override
	public ObserverBusqueda getObserverByName(String name) {
		return (this.observers.stream().filter(observer -> (observer.getNombre().equals(name))).collect(Collectors.toList())).get(0);
	}

}
