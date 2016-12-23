package grupo2.tpAnual.DBTests;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class InicializacionSQLTest extends AbstractPersistenceTest implements WithGlobalEntityManager{
	private EntityManager em;
	@Before
	public void init(){
		em = PerThreadEntityManagers.getEntityManager();
		beginTransaction();
	}

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}	
	
	@After
	public void after(){
		rollbackTransaction();
	}

}
