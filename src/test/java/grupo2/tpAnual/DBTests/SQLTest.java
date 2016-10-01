package grupo2.tpAnual.DBTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import grupo2.tpAnual.Direccion;
import grupo2.tpAnual.Observers.EnviarMailBusqueda;
import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.Pois.Comuna;
import grupo2.tpAnual.Repositorios.SQLUserRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class SQLTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
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
	
	@Test
	public void persistirDireccion(){
		Direccion dire= new Direccion("Medrano", "Almagro");
		persist(dire);
	
		Direccion direccionBuscada = (Direccion) em.createQuery("from Direccion where zona = :zona").setParameter("zona", "Almagro").getSingleResult();
		assertEquals(direccionBuscada.getZona(),"Almagro");
		assertEquals(direccionBuscada.getCalle(),"Medrano");
	}
	
	@Test
	public void persistirUsuario(){
		Usuario user = new Usuario();
		user.setNombre("juan");
		persist(user);
		Usuario userBuscado = (Usuario) em.createQuery("from Usuario where nombre = :nombre").setParameter("nombre","juan").getSingleResult();
		assertEquals(userBuscado.getNombre(),user.getNombre());
	}
	
	@Test
	public void persistirUsuarioConComuna(){
		Usuario user = new Usuario();
		user.setNombre("juan");
		Comuna comuna1 = new Comuna(1, null);
		user.setComuna(comuna1);
		persist(comuna1);
		persist(user);
		Usuario userBuscado = (Usuario) em.createQuery("from Usuario where nombre = :nombre").setParameter("nombre","juan").getSingleResult();
		assertEquals(userBuscado.getComuna().getNumeroComuna(), comuna1.getNumeroComuna());
		}
	
	@Test
	public void persitirObservers(){
		ObserverBusqueda notificarDatos = new NotificarDatosBusqueda();
		ObserverBusqueda enviarMail = new EnviarMailBusqueda(0, null, null);
		persist(notificarDatos);
		persist(enviarMail);
		List <ObserverBusqueda> obsBuscado = (List <ObserverBusqueda>) em.createQuery("from ObserverBusqueda").getResultList();
		assertEquals(obsBuscado.size(),2);
	}
	
	@Test
	public void userRepositoryTest(){
		UserRepository repo = new SQLUserRepository();
		
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		
		usuario1.setNombre("Pablo");
		usuario2.setNombre("Rodrigo");
		
		repo.setUsuario(usuario1);
		repo.setUsuario(usuario2);
		assertEquals(repo.getUsuarios().size(),2);
		repo.deleteUsuario(usuario1);
		repo.deleteUsuario(usuario2);
	}
	

	@Test
	public void UsuarioConObservers(){
		UserRepository repo = new SQLUserRepository();
		
		ObserverBusqueda notificar = new NotificarDatosBusqueda();
		ObserverBusqueda enviarMail = new EnviarMailBusqueda(15, null, "juan@gmail.com");
		List <ObserverBusqueda> observers = Arrays.asList(enviarMail,notificar);
		
		Usuario usuario1 = new Usuario();
		usuario1.setNombre("Pablo");
		usuario1.setAccionesBusqueda(observers);
		
		repo.setUsuario(usuario1);
		assertTrue(repo.getUsuarios().contains(usuario1));
		repo.deleteUsuario(usuario1);
	}
	/*@Test
	public void persistirBanco(){
		Banco banco;
		//DateTime momento;

		beginTransaction();
		banco = new Banco("santander", Point.and(-34.664837, -58.385674));
		persist(banco);
		commitTransaction();
		
		Banco bancoBuscado = (Banco) em.createQuery("from Banco where id_banco = :id").setParameter("id", banco.getId()).getSingleResult();
		assertEquals(bancoBuscado.getId(),banco.getId());
		
		
		//ver como tendria que hacer para conseguir el rango de disponibilidad desde la db
	}*/
	
	@After
	public void after(){
		rollbackTransaction();
	}
}