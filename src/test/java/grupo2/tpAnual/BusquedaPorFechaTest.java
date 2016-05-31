package grupo2.tpAnual;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import grupo2.tpAnual.Reportes.BusquedasPorFecha;

public class BusquedaPorFechaTest {
	BusquedasPorFecha reporte = new BusquedasPorFecha();
	
	@Test
	public void obtenerReporte(){
		List<DatosDeBusqueda> lista = new ArrayList<DatosDeBusqueda>();
		lista.add(new DatosDeBusqueda("libros", 18, 10, 15, new LocalDate()));
		lista.add(new DatosDeBusqueda("carpetas", 16, 14, 15, new LocalDate().minusDays(1)));
		lista.add(new DatosDeBusqueda("carpetas", 16, 14, 15, new LocalDate().minusDays(2)));
		Assert.assertEquals(lista.size(), 3);
	}
}
