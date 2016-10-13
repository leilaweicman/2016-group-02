package grupo2.tpAnual.DBTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ServiciosExternos.ServicioExternoBanco;
import ServiciosExternos.ServicioExternoCentroDTO;
import grupo2.tpAnual.Mapa;
import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.AccesoriosPois.Direccion;
import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.AccesoriosPois.Servicio;
import grupo2.tpAnual.Observers.EnviarMailBusqueda;
import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosBancoExterno;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosCentroDTO;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsMemory;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsSQL;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Pois.Parada;
import grupo2.tpAnual.Repositorios.SQLUserRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class SQLTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	private EntityManager em;
	private OrigenesDeDatosPOIsMemory origenesDeDatosPois;
	private List<OrigenesDeDatos> listaDeOrigenes;
	
	
	@Before
	public void init(){
		em = PerThreadEntityManagers.getEntityManager();
		
		this.listaDeOrigenes = new ArrayList<OrigenesDeDatos>();

		this.origenesDeDatosPois = new OrigenesDeDatosPOIsMemory();

		
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
	
	/*rompe y no entiendo por que 
	@Test
	public void persistirMapa(){
	
		Banco santander = new Banco("Santander", Point.and(-34.664837, -58.385674));
		santander.addPalabraClave("plazoFijo");
		santander.addPalabraClave("dolar");
		persist(santander);
		
		CGP rentas = new CGP("Flores", Point.and(-34.664837, -58.385674));
		List<Servicio> servicios = new ArrayList<Servicio>();
		List<Rango> listaRango = new ArrayList<>();
		Servicio ser = new Servicio(listaRango);
		ser.setNombre("Jubilados");
		servicios.add(ser);
		rentas.setServicios(servicios);
		persist(rentas);
		
		this.origenesDeDatosPois.agregarPOI(santander);
		this.origenesDeDatosPois.agregarPOI(rentas);

		
		listaDeOrigenes = Arrays.asList(origenesDeDatosPois);
		Mapa mapa = new Mapa(listaDeOrigenes);
		mapa.setNombre("MapaPrueba");
		persist(mapa);
		
		Mapa mapaBuscado = (Mapa) em.createQuery("from Mapa where nombre = :nombre").setParameter("nombre", "MapaPrueba").getSingleResult();
		assertEquals(mapaBuscado.getNombre(), "MapaPrueba");
	}*/
	
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
	
	@Test
	public void queryBusquedaTest(){
		OrigenesDeDatosPOIs repo = new OrigenesDeDatosPOIsSQL(); 
		POI banco = new Banco("Santander Rio", null);
		List <String> palabras = Arrays.asList("depositos","moneda extranjera", "pago de impuestos");
		banco.setPalabrasClaves(palabras);
		repo.agregarPOI(banco);
		assertEquals(repo.busqueda("Santander Rio").size(),1);
		
		repo.darDeBajaPOI(banco.getId());
	}
	
	@Test
	public void persistirPOISTest(){
		//TODO persistir los dif atributos y en comuna el point
		OrigenesDeDatosPOIs repo = new OrigenesDeDatosPOIsSQL(); 
		
		POI banco = new Banco("Santander Rio", null);
		Comuna comuna = new Comuna(3,null);
		Direccion direccion = new Direccion("Medrano"," Almagro");
		banco.setComuna(comuna);
		banco.setDireccion(direccion);
		
		POI comercio = new Comercio("Kosiuko", null, null);
		POI cgp = new CGP("Centro de Atención comuna 15", null);
		POI parada = new Parada("Linea 114", null, null);
		
		repo.agregarPOI(banco);
		repo.agregarPOI(comercio);
		repo.agregarPOI(cgp);
		repo.agregarPOI(parada);
		assertEquals(repo.getPOIs().size(),4);
		
		repo.darDeBajaPOI(banco.getId());
		repo.darDeBajaPOI(comercio.getId());
		repo.darDeBajaPOI(parada.getId());
		repo.darDeBajaPOI(cgp.getId());
		assertEquals(repo.getPOIs().size(),0);
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