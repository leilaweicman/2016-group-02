package grupo2.tpAnual;

import java.util.Map;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.Reportes.PorBusquedaPorFecha;
import org.joda.time.LocalDate;

public class ReporteBusquedaPorFechaTest {
	DatosBusquedaRepository registro;
	DatosDeBusqueda datoBuscado;
	DatosDeBusqueda datoBuscado2;
	DatosDeBusqueda datoBuscado3;
	PorBusquedaPorFecha reporte;

	@Before
	public void init() {
		registro = new DatosBusquedaRepository();
		datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, new LocalDate());
		datoBuscado2 = new DatosDeBusqueda("flores", "carpetas", 14, 15, new LocalDate().minusDays(1));
		datoBuscado3 = new DatosDeBusqueda("flores", "carpetas", 14, 15, new LocalDate().minusDays(2));
		registro.agregarDatosBusqueda(datoBuscado);
		registro.agregarDatosBusqueda(datoBuscado2);
		registro.agregarDatosBusqueda(datoBuscado3);
		reporte = new PorBusquedaPorFecha(registro);
	}

	@Test
	public void obtenerReporteTest() {
		Map<LocalDate, Integer> map = reporte.busquedasPorFecha();
		Assert.assertEquals(map.size(), 3);
	}
}
