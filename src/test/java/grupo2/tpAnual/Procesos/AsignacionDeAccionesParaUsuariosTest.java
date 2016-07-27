package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.Banco;
import grupo2.tpAnual.CGP;
import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.Parada;
import grupo2.tpAnual.UserRepository;
import grupo2.tpAnual.Usuario;
import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.EnviarMailFalloProceso;

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

	private NotificarDatosBusqueda observerRegistro;

	private UserRepository repo;

	@Before
	public void init() {
		origenesDeDatos = new OrigenesDeDatosPOIs();
		cgp = new CGP();
		parada = new Parada("linea 7");
		banco = new Banco();
		origenesDeDatos.agregarPOI(banco);
		origenesDeDatos.agregarPOI(cgp);
		origenesDeDatos.agregarPOI(parada);
		List<AccionEnCasoDeFallo> configuraciones = new ArrayList<>();
		configuraciones.add(config1);
		proceso3 = new AsignacionDeAccionesParaUsuarios(17, new LocalDate(), configuraciones, origenesDeDatos);
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

		repo = new UserRepository();
		this.repo.setUsuarios(juan);
		this.repo.setUsuarios(ana);

		this.juan.setComuna(comuna1);
		this.ana.setComuna(comuna2);

		this.porComuna.setRepositorioUsuarios(repo);
		this.todos.setRepositorioUsuarios(repo);
		this.picky.setRepositorioUsuarios(repo);

		this.picky.agregarUsuario(juan);

		observerRegistro = new NotificarDatosBusqueda();
		this.proceso3.setAccionesParaAgregarAUsuario(observerRegistro);
		this.proceso3.setDeComuna(comuna1);
	}

	@Test
	public void testEjecutarProceso() {
		this.proceso3.setCriterio(todos);
		proceso3.getUsuariosSegunCriterio();
		proceso3.ejecutarProceso();
		Assert.assertEquals(juan.getAccionesBusqueda().size(), 1);
		Assert.assertEquals(ana.getAccionesBusqueda().size(), 1);

	}

	@Test
	public void testEjecutarProceso2() {
		this.proceso3.setCriterio(porComuna);
		proceso3.getUsuariosSegunCriterio();
		proceso3.ejecutarProceso();
		Assert.assertEquals(juan.getAccionesBusqueda().size(), 1);
		Assert.assertEquals(ana.getAccionesBusqueda().size(), 0);

	}

	@Test
	public void testEjecutarProceso3() {
		this.proceso3.setCriterio(picky);
		proceso3.getUsuariosSegunCriterio();
		proceso3.ejecutarProceso();
		Assert.assertEquals(juan.getAccionesBusqueda().size(), 1);
		Assert.assertEquals(ana.getAccionesBusqueda().size(), 0);

	}
}
