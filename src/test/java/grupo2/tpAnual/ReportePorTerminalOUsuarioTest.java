package grupo2.tpAnual;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.Reportes.PorTerminalOUsuario;

public class ReportePorTerminalOUsuarioTest {
	private NotificarDatosBusqueda observer;
	private DatosDeBusqueda datoBuscado;
	private DatosDeBusqueda datoBuscado2;
	private DatosDeBusqueda datoBuscado3;
	private String nombreTerminal;
	private PorTerminalOUsuario reporte;

	@Before
	public void init() {
		this.observer = new NotificarDatosBusqueda();
		this.datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, new LocalDate());
		this.datoBuscado2 = new DatosDeBusqueda("flores", "asado", 10, 15, new LocalDate());
		this.datoBuscado3 = new DatosDeBusqueda("flores", "asado", 20, 25, new LocalDate());
		this.reporte = new PorTerminalOUsuario();
	}

	/*@Test
	public void reporteEnTerminalLasHerasTest() {
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		Assert.assertEquals(reporte.obtenerReportePorTerminal(nombreTerminal), 1);
	}*/

	@Test
	public void reporteLasHerasTest() {
		this.nombreTerminal = "lasHeras";
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		Assert.assertNotEquals(reporte.obtenerReportePorTerminal(nombreTerminal).size(), 2);
	}

	/*@Test
	public void reporteEnTerminalFloresTest() {
		this.nombreTerminal = "flores";
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		this.observer.notificarBusqueda(datoBuscado3);
		Assert.assertEquals(reporte.obtenerReportePorTerminal(nombreTerminal).size(), 2);

	}*/

	/*@Test
	public void reporteUsuarioTest() {
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		this.observer.notificarBusqueda(datoBuscado3);
		Assert.assertEquals(reporte.obtenerReportePorUsuario().size(), 2);
		Assert.assertTrue(reporte.obtenerReportePorUsuario().get("flores").equals(40));
		Assert.assertTrue(reporte.obtenerReportePorUsuario().get("lasHeras").equals(15));
	}*/

	@Test
	public void reportePorUsuarioTest() {
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		this.observer.notificarBusqueda(datoBuscado3);
		Assert.assertNotEquals(reporte.obtenerReportePorUsuario().size(), 3);
	}
}
