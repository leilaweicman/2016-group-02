package grupo2.tpAnual;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import grupo2.tpAnual.Observers.RegistrarBusqueda;

public class BusquedaPorFechaTest {
	RegistrarBusqueda observer;
	DatosDeBusqueda datoBuscado;
	DatosDeBusqueda datoBuscado2;
	DatosDeBusqueda datoBuscado3;
	Reportes reporte;
	
	@Before
	public void init() {
	datoBuscado = new DatosDeBusqueda("lasHeras","libros", 18, 10, 15, new LocalDate());
	datoBuscado2 = new DatosDeBusqueda("flores","carpetas", 16, 14, 15, new LocalDate().minusDays(1));
	datoBuscado3 = new DatosDeBusqueda("flores","carpetas", 16, 14, 15, new LocalDate().minusDays(2));
	observer = new RegistrarBusqueda();
	reporte = new Reportes();
	}

	
	@Test
	public void obtenerReporteTest(){
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		this.observer.notificarBusqueda(datoBuscado3);
		
	}
	
	public void obtenerReporte(){
		List<DatosDeBusqueda> lista = new ArrayList<DatosDeBusqueda>();

		lista.add(datoBuscado);
		lista.add(datoBuscado2);
		lista.add(datoBuscado3);
		Map<String, Integer> map = reporte.busquedasPorFecha(lista);
		Assert.assertEquals(map.size(), 3);
	}
}
