package grupo2.tpAnual;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;


public class BusquedaPorFechaTest {
	Reportes reporte = new Reportes();
	
	@Test
	public void obtenerReporte(){
		List<DatosDeBusqueda> lista = new ArrayList<DatosDeBusqueda>();
		lista.add(new DatosDeBusqueda("libros", 18, 10, 15, new LocalDate()));
		lista.add(new DatosDeBusqueda("carpetas", 16, 14, 15, new LocalDate().minusDays(1)));
		lista.add(new DatosDeBusqueda("carpetas", 16, 14, 15, new LocalDate().minusDays(2)));
		Map<String, Integer> map = reporte.BusquedasPorFecha(lista);
		Assert.assertEquals(map.size(), 3);
	}
}
