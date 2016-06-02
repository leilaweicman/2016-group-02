package grupo2.tpAnual;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.Observers.RegistrarBusqueda;
import grupo2.tpAnual.Reportes.BusquedasPorFecha;

public class BusquedaPorFechaTest {
	RegistrarBusqueda observer;
	DatosDeBusqueda datoBuscado;
	DatosDeBusqueda datoBuscado2;
	DatosDeBusqueda datoBuscado3;
	BusquedasPorFecha reporte;
	
	@Before
	public void init() {
	datoBuscado = new DatosDeBusqueda("lasHeras","libros", 18, 10, 15, new LocalDate());
	datoBuscado2 = new DatosDeBusqueda("flores","asado", 5, 10, 15, new LocalDate());
	datoBuscado3 = new DatosDeBusqueda("flores","asado", 10, 20, 25, new LocalDate());
	observer = new RegistrarBusqueda();
	reporte = new BusquedasPorFecha();
	}
	
	@Test
	public void obtenerReporteTest(){
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		this.observer.notificarBusqueda(datoBuscado3);
		
	}
	
	public void obtenerReporte(){
		List<DatosDeBusqueda> lista = new ArrayList<DatosDeBusqueda>();
		lista.add(new DatosDeBusqueda("flores","libros", 18, 10, 15, new LocalDate()));
		lista.add(new DatosDeBusqueda("lasHeras","carpetas", 16, 14, 15, new LocalDate().minusDays(1)));
		lista.add(new DatosDeBusqueda("lasHeras","carpetas", 16, 14, 15, new LocalDate().minusDays(2)));
		Assert.assertEquals(lista.size(), 3);
	}
}
