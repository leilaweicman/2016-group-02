package grupo2.tpAnual.Reportes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Reportes.PorTerminalOUsuario;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMemory;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;

public class ReportePorTerminalOUsuarioTest {
	private DatosBusquedaRepositoryMemory registro;
	private DatosDeBusqueda datoBuscado;
	private DatosDeBusqueda datoBuscado2;
	private DatosDeBusqueda datoBuscado3;
	private String nombreTerminal;
	private PorTerminalOUsuario reporte;
	private Rango unRango;
	private Rango otroRango;
	private Rango rango;
	private List<Rango> listaRangos;
	private Comercio comercio;
	private List<POI> pois;
	private LocalDate today;

	@Before
	public void init() {
		today= LocalDate.now();
		unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
		otroRango = new Rango(3, LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
		rango = new Rango(3, LocalTime.of(15, 0, 0), LocalTime.of(18, 30, 0));
		listaRangos = Arrays.asList(unRango, otroRango, rango);
		comercio = new Comercio("Supermercado argenChino", Point.and(-34.664837, -58.385674), listaRangos);
		pois= new ArrayList<>();
		pois.add(comercio);
		this.registro = new DatosBusquedaRepositoryMemory();
		this.datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, today,pois);
		this.datoBuscado2 = new DatosDeBusqueda("flores", "asado", 10, 15, today,pois);
		this.datoBuscado3 = new DatosDeBusqueda("flores", "asado", 20, 25, today,pois);
		this.registro.agregarDatosBusqueda(datoBuscado);
		this.registro.agregarDatosBusqueda(datoBuscado2);
		this.registro.agregarDatosBusqueda(datoBuscado3);
		this.reporte = new PorTerminalOUsuario(registro);
	}

	@Test
	public void reporteLasHerasTamanoBusquedaEqualsTest() {
		this.nombreTerminal = "lasHeras";
		Assert.assertEquals(reporte.obtenerReportePorTerminal(nombreTerminal).size(), 1);
	}

	@Test
	public void reporteLasHerasTamanoBusquedaNotEqualsTest() {
		this.nombreTerminal = "lasHeras";
		Assert.assertNotEquals(reporte.obtenerReportePorTerminal(nombreTerminal).size(), 2);
	}

	@Test
	public void reporteTerminalFloresTamanoBusquedaTest() {
		this.nombreTerminal = "flores";
		Assert.assertEquals(reporte.obtenerReportePorTerminal(nombreTerminal).size(), 2);
	}

	@Test
	public void reporteTerminalFloresCantidadResultadosTest() {
		this.nombreTerminal = "flores";
		List<Integer> lista = new ArrayList<Integer>();
		lista = Arrays.asList(15, 25);
		Assert.assertEquals(reporte.obtenerReportePorTerminal(nombreTerminal), lista);
		// devuelve una lista con el total de resultados en la terminal por
		// busqueda,
		// en este caso devuelve dos resultados, 15 veces se busco asado en
		// flores y otro dato dice q 25 veces se busco
	}

	/*
	 * @Test public void reportePorUsuarioEnTerminalLasHerasTest() {
	 * Assert.assertTrue(reporte.obtenerReportePorUsuario().get("lasHeras").
	 * equals(15)); }
	 * 
	 * 
	 * @Test public void reportePorUsuarioEnTerminalFloresTest() {
	 * Assert.assertTrue(reporte.obtenerReportePorUsuario().get("flores").equals
	 * (40)); }
	 * 
	 * @Test public void reportePorUsuarioTest() {
	 * Assert.assertEquals(reporte.obtenerReportePorUsuario().size(), 2); }
	 */
}
