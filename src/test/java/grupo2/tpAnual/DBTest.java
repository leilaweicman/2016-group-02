package grupo2.tpAnual;

import static org.junit.Assert.*;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
//import org.hibernate.jpa.HibernatePersistenceProvider;

import grupo2.tpAnual.Direccion;

public class DBTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	private EntityManager em;
	
	@Before
	public void init(){
		em = PerThreadEntityManagers.getEntityManager();	
	}
	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}
	@Test
	public void persistirDireccion(){
		beginTransaction();
		Direccion dire= new Direccion("Medrano", "Almagro");
		persist(dire);
		commitTransaction();
		em.clear();
		
		Direccion direccionBuscada = (Direccion) em.createQuery("from Direccion where zona = :zona")
				.setParameter("zona", "Almagro").getSingleResult();
		assertEquals(direccionBuscada.getZona(),"Almagro");
		assertEquals(direccionBuscada.getCalle(),"Medrano");
	}
	
}