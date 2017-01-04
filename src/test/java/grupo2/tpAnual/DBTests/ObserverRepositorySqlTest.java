package grupo2.tpAnual.DBTests;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import grupo2.tpAnual.Observers.EnviarMailBusqueda;
import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.Repositorios.ObserversRepository;
import grupo2.tpAnual.Repositorios.SQLObserverRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class ObserverRepositorySqlTest extends AbstractPersistenceTest implements WithGlobalEntityManager{ 
	private EntityManager em;
	private ObserversRepository repo;
	
	
	@Before
	public void init(){
		em = PerThreadEntityManagers.getEntityManager();
		repo = new SQLObserverRepository();
		
		beginTransaction();
	}
	
	@Test
	public void AgregarObserversRepositoryTest(){
		ObserverBusqueda notificarDatos = new NotificarDatosBusqueda(true);
		ObserverBusqueda enviarMail = new EnviarMailBusqueda(0, null, null);
		
		notificarDatos.setNombre("notificarDatos");
		enviarMail.setNombre("enviarMail");
		
		repo.saveObserver(notificarDatos);
		repo.saveObserver(enviarMail);
		assertEquals(repo.getObservers().size(),2);
	}
	
	@Test
	public void BorrarObserversRepositoryTest(){
		ObserverBusqueda notificarDatos = new NotificarDatosBusqueda(true);
		ObserverBusqueda enviarMail = new EnviarMailBusqueda(0, null, null);
		
		notificarDatos.setNombre("notificarDatos");
		enviarMail.setNombre("enviarMail");
		
		repo.saveObserver(notificarDatos);
		repo.saveObserver(enviarMail);
		repo.deleteObserver(notificarDatos);
		assertEquals(repo.getObservers().size(),1);
	}

}
