package grupo2.tpAnual.DBTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;
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
import grupo2.tpAnual.AccesoriosPois.Disponibilidad;
import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.AccesoriosPois.Rubro;
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
	private Integer dia;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	private Rango rango;
	List<Rango> rangoDisponibilidad;
	Disponibilidad disponibilidad;
	
	
	@Before
	public void init(){
		em = PerThreadEntityManagers.getEntityManager();
		
		this.listaDeOrigenes = new ArrayList<OrigenesDeDatos>();

		this.origenesDeDatosPois = new OrigenesDeDatosPOIsMemory();

		this.dia = 1;
		this.horaDesde = LocalTime.of(10,0,0);
		this.horaHasta = LocalTime.of(15, 0,0);
		this.rango = new Rango(dia, horaDesde, horaHasta);

		this.rangoDisponibilidad = new ArrayList<Rango>();
		rangoDisponibilidad.add(rango);

		this.disponibilidad = new Disponibilidad(rangoDisponibilidad);

		
		
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
	public void queryBusquedaTest(){
		persist(this.disponibilidad);
		OrigenesDeDatosPOIs repo = new OrigenesDeDatosPOIsSQL(); 
		Banco banco = new Banco("Santander Rio", null);
		List <String> palabras = Arrays.asList("depositos","moneda extranjera", "pago de impuestos");
		banco.setPalabrasClaves(palabras);
		banco.setDisponibilidad(this.disponibilidad);
		repo.agregarPOI(banco);
		assertEquals((repo.busqueda("Santander Rio").size()),1);
		
		repo.darDeBajaPOI(banco.getId());
	}
	
	@Test
	public void persistirPOISTest(){
		//TODO persistir los dif atributos y en comuna el point
		
		persist(this.disponibilidad);
		
		OrigenesDeDatosPOIs repo = new OrigenesDeDatosPOIsSQL(); 
		
		Banco banco = new Banco("Santander Rio", null);
		Comuna comuna = new Comuna(3,null);
		Direccion direccion = new Direccion("Medrano"," Almagro");
		persist(comuna);
		banco.setComuna(comuna);
		banco.setDireccion(direccion);
		banco.setDisponibilidad(this.disponibilidad);
		
		POI comercio = new Comercio("Kosiuko", null, null);
		POI cgp = new CGP("Centro de Atención comuna 15", null);
		POI parada = new Parada("Linea 114", null, null);
		
		repo.agregarPOI(comercio);
		repo.agregarPOI(cgp);
		repo.agregarPOI(parada);
		repo.agregarPOI(banco);
		assertEquals(repo.getPOIs().size(),4);
		
		repo.darDeBajaPOI(banco.getId());
		repo.darDeBajaPOI(comercio.getId());
		repo.darDeBajaPOI(parada.getId());
		repo.darDeBajaPOI(cgp.getId());
		assertEquals(repo.getPOIs().size(),0);
	}*/
	
	@Test
	public void persistirRango(){
		persist(this.rango);
		
		Rango rangoBuscado = (Rango) em.createQuery("from Rango where horaDesde = :horaDesde").setParameter("horaDesde", rango.getHoraDesde()).getSingleResult();
		assertEquals(rangoBuscado.getHoraDesde(),rango.getHoraDesde());
		
	}
	
	@Test
	public void persistirDisponibilidad(){
		
		persist(this.disponibilidad);
		
		DateTime momento = new DateTime("2016-04-25T11:00:00");
		
		Disponibilidad disponibilidadBuscada = (Disponibilidad) em.createQuery("from Disponibilidad where id = :id").setParameter("id", disponibilidad.getId()).getSingleResult();
		assertEquals(disponibilidad.estaDisponible(momento),disponibilidadBuscada.estaDisponible(momento));

	}
	
	@Test
	public void persistirParada(){
		Parada parada = new Parada("Linea 114", null, "114");
		persist(parada);
		
		Parada paradaBuscada = (Parada) em.createQuery("from Parada where id = :id").setParameter("id", parada.getId()).getSingleResult();
		assertEquals(parada.getLinea(), paradaBuscada.getLinea());
	}
	
	@Test
	public void persistirRubro(){
		persist(rango);
		
		Rubro rubro = new Rubro();
		rubro.setDisponibilidad(rango);
		rubro.setTipo("ropa");
		persist(rubro);
		
		Rubro rubroBuscado = (Rubro) em.createQuery("from Rubro where id = :id").setParameter("id", rubro.getId()).getSingleResult();
		assertEquals(rubro.getTipo(), rubroBuscado.getTipo());
	}
	
	@Test
	public void persistirServicio(){
		persist(disponibilidad);
		
		Servicio servicio = new Servicio(rangoDisponibilidad);
		servicio.setDisponibilidad(disponibilidad);
		servicio.setNombre("Renovacion dni");
		persist(servicio);
		
		Servicio servicioBuscado = (Servicio) em.createQuery("from Servicio where id = :id").setParameter("id", servicio.getId()).getSingleResult();
		assertEquals(servicio.getNombre(), servicioBuscado.getNombre());
	}
	
	@Test
	public void persistirComercio(){
		persist(rango);
		persist(disponibilidad);
		
		Rubro rubro = new Rubro();
		rubro.setDisponibilidad(rango);
		rubro.setTipo("ropa");
		persist(rubro);
		
		Comercio comercio = new Comercio("Wallgreens", null, rangoDisponibilidad);
		comercio.setRubro(rubro);
		comercio.setDisponibilidad(disponibilidad);
		persist(comercio);
		
		Comercio comercioBuscado = (Comercio) em.createQuery("from Comercio where id = :id").setParameter("id", comercio.getId()).getSingleResult();
		assertEquals(comercio.getRubro(), comercioBuscado.getRubro());
	}
	
	@Test
	public void persistirBanco(){
		
		persist(this.disponibilidad);
		
		Banco banco;

		banco = new Banco("santander", Point.and(-34.664837, -58.385674));
		banco.setDisponibilidad(this.disponibilidad);
		persist(banco);
		
		Banco bancoBuscado = (Banco) em.createQuery("from Banco where id = :id").setParameter("id", banco.getId()).getSingleResult();
		assertEquals(bancoBuscado.getId(), banco.getId());
		
	}
	
	@After
	public void after(){
		rollbackTransaction();
	}
}