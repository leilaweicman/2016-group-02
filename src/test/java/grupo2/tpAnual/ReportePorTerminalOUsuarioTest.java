package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import grupo2.tpAnual.Reportes.PorTerminalOUsuario;

public class ReportePorTerminalOUsuarioTest {
	private DatosBusquedaRepository registro;
	private DatosDeBusqueda datoBuscado;
	private DatosDeBusqueda datoBuscado2;
	private DatosDeBusqueda datoBuscado3;
	private String nombreTerminal;
	private PorTerminalOUsuario reporte;

	@Before
	public void init() {
		this.registro = new DatosBusquedaRepository();
		this.datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, new LocalDate());
		this.datoBuscado2 = new DatosDeBusqueda("flores", "asado", 10, 15, new LocalDate());
		this.datoBuscado3 = new DatosDeBusqueda("flores", "asado", 20, 25, new LocalDate());
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
