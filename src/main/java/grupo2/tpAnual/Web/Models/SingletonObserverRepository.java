package grupo2.tpAnual.Web.Models;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import grupo2.tpAnual.Observers.EnviarMailBusqueda;
import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.Repositorios.MemoryObserverRepository;
import grupo2.tpAnual.Repositorios.ObserversRepository;
import grupo2.tpAnual.Repositorios.SQLObserverRepository;
import grupo2.tpAnual.Web.Server;

public class SingletonObserverRepository extends AbstractPersistenceTest implements WithGlobalEntityManager{
	
	public static ObserversRepository instance;
	private static EntityManager em;

	public static ObserversRepository get() {
		ObserverBusqueda notificarDatos = new NotificarDatosBusqueda(true);
		ObserverBusqueda enviarMail = new EnviarMailBusqueda(0, null, null);
		
		notificarDatos.setNombre("notificarDatos");
		enviarMail.setNombre("enviarMail");
		
		if (instance == null) {
			if (Server.inMemory == true){				
				instance = new MemoryObserverRepository();
				instance.saveObserver(notificarDatos);
				instance.saveObserver(enviarMail);
			}
			else{
				em = PerThreadEntityManagers.getEntityManager();
				em.getTransaction().begin();
				instance = SQLObserverRepository.get();
				instance.saveObserver(notificarDatos);
				instance.saveObserver(enviarMail);				
				em.getTransaction().commit();
			}
		}
		return instance;
	}

}
