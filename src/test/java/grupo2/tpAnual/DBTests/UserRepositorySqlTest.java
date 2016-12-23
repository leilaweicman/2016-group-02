package grupo2.tpAnual.DBTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Observers.EnviarMailBusqueda;
import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.Repositorios.SQLUserRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class UserRepositorySqlTest  extends AbstractPersistenceTest implements WithGlobalEntityManager{
	private EntityManager em;
	private Usuario user;
	private UserRepository repo;
	
	@Before
	public void init(){
		em = PerThreadEntityManagers.getEntityManager();
		user = new Usuario();
		repo = new SQLUserRepository();
		
		beginTransaction();
		user.setNombre("juan");

	}
	
	@Test
	public void persistirUsuario(){
		repo.saveUser(user);
		Usuario userBuscado = (Usuario) em.createQuery("from Usuario where nombre = :nombre").setParameter("nombre","juan").getSingleResult();
		assertEquals(userBuscado.getNombre(),user.getNombre());
	}
	
	@Test
	public void persistirUsuarioConComuna(){
		List<Point> points = new ArrayList<Point>();
		points.add(Point.and(-34.664837, -58.385674));
		Comuna comuna1 = new Comuna(1, points);
		persist(comuna1);
		user.setComuna(comuna1);
		repo.saveUser(user);
		Usuario userBuscado = (Usuario) em.createQuery("from Usuario where nombre = :nombre").setParameter("nombre","juan").getSingleResult();
		assertEquals(userBuscado.getComuna().getNumeroComuna(), comuna1.getNumeroComuna());
		}
	
	@Test
	public void persitirObservers(){
		ObserverBusqueda notificarDatos = new NotificarDatosBusqueda(true);
		ObserverBusqueda enviarMail = new EnviarMailBusqueda(0, null, null);
		persist(notificarDatos);
		persist(enviarMail);
		List <ObserverBusqueda> obsBuscado = (List <ObserverBusqueda>) em.createQuery("from ObserverBusqueda").getResultList();
		assertEquals(obsBuscado.size(),2);
	}
	
	@Test
	public void userRepositoryTest(){
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		
		usuario1.setNombre("Pablo");
		usuario2.setNombre("Rodrigo");
		
		repo.saveUser(usuario1);
		repo.saveUser(usuario2);
		assertEquals(repo.getUsuarios().size(),2);
	}
	

	@Test
	public void UsuarioConObservers(){
		ObserverBusqueda notificar = new NotificarDatosBusqueda(true);
		ObserverBusqueda enviarMail = new EnviarMailBusqueda(15, null, "juan@gmail.com");
		List <ObserverBusqueda> observers = Arrays.asList(enviarMail,notificar);
		user.setAccionesBusqueda(observers);
		
		repo.saveUser(user);
		assertTrue(repo.getUsuarios().contains(user));
	}
	
	@After
	public void after(){
		rollbackTransaction();
	}

}
