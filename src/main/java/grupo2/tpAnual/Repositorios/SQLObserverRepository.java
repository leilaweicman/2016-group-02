package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo2.tpAnual.Observers.ObserverBusqueda;

public class SQLObserverRepository implements ObserversRepository, WithGlobalEntityManager {

	private static SQLObserverRepository instance;

	public static SQLObserverRepository get() {
		if (instance == null) {
			instance = new SQLObserverRepository();
		}
		return instance;
	}

	@Override
	public void deleteObserver(ObserverBusqueda observer) {
		entityManager().remove(observer);	}

	@Override
	public void saveObserver(ObserverBusqueda observer) {
		entityManager().persist(observer);
	}

	@Override
	public List<ObserverBusqueda> getObservers() {
		List<ObserverBusqueda> observers = new ArrayList<ObserverBusqueda>();
		observers = (List<ObserverBusqueda>) entityManager().createQuery("from ObserverBusqueda").getResultList();
		return observers;
	}

	@Override
	public ObserverBusqueda getObserverByName(String name) {
		List<ObserverBusqueda> observers = new ArrayList<ObserverBusqueda>();
		observers = (List<ObserverBusqueda>) entityManager().createQuery("from ObserverBusqueda where nombre = :nombre").setParameter("nombre", name).getResultList();
		return observers.get(0);
	}

}
