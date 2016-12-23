package grupo2.tpAnual.DBTests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.AccesoriosPois.Direccion;
import grupo2.tpAnual.AccesoriosPois.Disponibilidad;
import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.AccesoriosPois.Rubro;
import grupo2.tpAnual.AccesoriosPois.Servicio;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsSQL;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Pois.Parada;

public class PoiSQLTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	private EntityManager em;
	private Integer dia;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	private Rango rango;
	List<Rango> rangoDisponibilidad;
	Disponibilidad disponibilidad;

	@Before
	public void init(){
		em = PerThreadEntityManagers.getEntityManager();
		
	
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
	public void queryBusquedaTest(){
		em.persist(this.rango);
		em.persist(this.disponibilidad);
		OrigenesDeDatosPOIs repo = new OrigenesDeDatosPOIsSQL();
		Banco banco = new Banco("Santander Rio", Point.and(-32.555, 52.222));
		List <String> palabras = Arrays.asList("depositos","moneda extranjera", "pago de impuestos");
		banco.setPalabrasClaves(palabras);
		banco.setDisponibilidad(this.disponibilidad);
	    repo.agregarPOI(banco);
	    em.flush();
	    assertEquals((repo.busqueda("Santander Rio").size()),1);
	}
	
	@Test
	public void persistirPOISTest(){
		//TODO persistir los dif atributos y en comuna el pont
		em.persist(rango);
		em.persist(this.disponibilidad);
		OrigenesDeDatosPOIs repo = new OrigenesDeDatosPOIsSQL(); 
		
		Banco banco = new Banco("Santander Rio", null);
		Comuna comuna = new Comuna(3,null);
		Direccion direccion = new Direccion("Medrano"," Almagro");
		persist(comuna);
		banco.setComuna(comuna);
		banco.setDireccion(direccion);
		banco.setDisponibilidad(disponibilidad);
		POI cgp = new CGP("Centro de Atención comuna 15", null);
		POI parada = new Parada("Linea 114", null, null);
		
		repo.agregarPOI(cgp);
		repo.agregarPOI(parada);
		repo.agregarPOI(banco);
		em.flush();
		assertEquals(repo.getPOIs().size(),3);
	}
	
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
	public void persistirCGP(){
		persist(disponibilidad);
		
		Servicio servicio = new Servicio(rangoDisponibilidad);
		servicio.setDisponibilidad(disponibilidad);
		servicio.setNombre("Renovacion dni");
		
		persist(servicio);
		
		CGP cgp = new CGP("CGP1", null);
		cgp.agregarServicio(servicio);
		persist(cgp);
		
		CGP cgpBuscado = (CGP) em.createQuery("from CGP where id = :id").setParameter("id", cgp.getId()).getSingleResult();
		assertEquals(cgp.getNombre(), cgpBuscado.getNombre());
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