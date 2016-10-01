package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.Comuna;
import grupo2.tpAnual.Pois.Parada;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.EnviarMailFalloProceso;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class AsignacionDeAccionesParaUsuariosTest {
	private AsignacionDeAccionesParaUsuarios proceso3;
	private OrigenesDeDatosPOIs origenesDeDatos;
	private Banco banco;
	private Parada parada;
	private CGP cgp;
	public EnviarMailFalloProceso config1;
	private Usuario juan;
	private Usuario ana;
	private Comuna comuna1;
	private Comuna comuna2;

	private Point vertice1;
	private Point vertice2;
	private Point vertice3;
	private Point vertice4;
	private CriterioTodos todos;
	private CriterioComuna porComuna;
	private CriterioSeleccionFija picky;
	private List<AccionEnCasoDeFallo> configuraciones;
	private NotificarDatosBusqueda observerRegistro;

	private MemoryUserRepository repo;

	@Before
	public void init() {
		origenesDeDatos = new OrigenesDeDatosPOIs();
		cgp = new CGP("Parque Chacabuco", Point.and(-34.664837, -58.385674));
		parada = new Parada("Amigos del bondi SA", Point.and(-34.664837, -58.385674), "linea 7");
		banco = new Banco("Banco nacion", Point.and(-34.664837, -58.385674));
		origenesDeDatos.agregarPOI(banco);
		origenesDeDatos.agregarPOI(cgp);
		origenesDeDatos.agregarPOI(parada);
		configuraciones = new ArrayList<>();
		configuraciones.add(config1);
		juan = new Usuario();
		ana = new Usuario();
		todos = new CriterioTodos();
		porComuna = new CriterioComuna();
		picky = new CriterioSeleccionFija();

		this.vertice1 = Point.and(-34.668075, -58.380060);
		this.vertice2 = Point.and(-34.673044, -58.387755);
		this.vertice3 = Point.and(-34.668363, -58.398441);
		this.vertice4 = Point.and(-34.661528, -58.388313);

		List<Point> listaVertices = Arrays.asList(vertice1, vertice2, vertice3, vertice4);
		this.comuna1 = new Comuna(1, listaVertices);
		this.comuna2 = new Comuna(2, listaVertices);

		repo = new MemoryUserRepository();
		this.repo.setUsuario(juan);
		this.repo.setUsuario(ana);

		this.juan.setComuna(comuna1);
		this.ana.setComuna(comuna2);

		this.porComuna.setRepositorioUsuarios(repo);
		this.todos.setRepositorioUsuarios(repo);
		this.picky.setRepositorioUsuarios(repo);

		this.picky.agregarUsuario(juan);

		observerRegistro = new NotificarDatosBusqueda();
		
	}

	@Test
	public void testEjecutarProceso() {
		proceso3 = new AsignacionDeAccionesParaUsuarios(17, new LocalDate(), configuraciones, origenesDeDatos, todos);
		this.proceso3.setAccionesParaAgregarAUsuario(observerRegistro);
		this.proceso3.setDeComuna(comuna1);
		proceso3.ejecutarProceso();
		Assert.assertEquals(juan.getAccionesBusqueda().size(), 1);
		Assert.assertEquals(ana.getAccionesBusqueda().size(), 1);

	}

	@Test
	public void testEjecutarProceso2() {
		proceso3 = new AsignacionDeAccionesParaUsuarios(17, new LocalDate(), configuraciones, origenesDeDatos, porComuna);
		this.proceso3.setAccionesParaAgregarAUsuario(observerRegistro);
		this.proceso3.setDeComuna(comuna1);
		proceso3.ejecutarProceso();
		Assert.assertEquals(juan.getAccionesBusqueda().size(), 1);
		Assert.assertEquals(ana.getAccionesBusqueda().size(), 0);

	}

	@Test
	public void testEjecutarProceso3() {
		proceso3 = new AsignacionDeAccionesParaUsuarios(17, new LocalDate(), configuraciones, origenesDeDatos, picky);
		this.proceso3.setAccionesParaAgregarAUsuario(observerRegistro);
		this.proceso3.setDeComuna(comuna1);
		proceso3.ejecutarProceso();
		Assert.assertEquals(juan.getAccionesBusqueda().size(), 1);
		Assert.assertEquals(ana.getAccionesBusqueda().size(), 0);

	}
}
