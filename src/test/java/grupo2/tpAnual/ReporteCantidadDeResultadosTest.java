package grupo2.tpAnual;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.Observers.RegistrarBusqueda;
import grupo2.tpAnual.Reportes.ReporteCantidadDeResultados;

public class ReporteCantidadDeResultadosTest {
	RegistrarBusqueda observer;
	DatosDeBusqueda datoBuscado;
	DatosDeBusqueda datoBuscado2;
	DatosDeBusqueda datoBuscado3;
	String nombreTerminal;
	ReporteCantidadDeResultados reporte;
	
	//FALTAN LOS TESTS DE OBTENERREPORTEPORUSUARIO PORQUE FALTA CORREGIR EL METODO
	
	@Before
	public void init() {
		datoBuscado = new DatosDeBusqueda("lasHeras","libros", 18, 10, 15, new LocalDate());
		datoBuscado2 = new DatosDeBusqueda("flores","asado", 5, 10, 15, new LocalDate());
		datoBuscado3 = new DatosDeBusqueda("flores","asado", 10, 20, 25, new LocalDate());
		observer = new RegistrarBusqueda();
		reporte=new ReporteCantidadDeResultados();
	}	
	
	@Test
	public void reporteEnTerminalLasHerasTest() {
		nombreTerminal= "lasHeras";		
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		Assert.assertEquals(reporte.obtenerReportePorTerminal(observer.getRegistroBusqueda(), nombreTerminal).size(), 1);
	}
	
	@Test
	public void reporteLasHerasTest() {
		nombreTerminal= "lasHeras";		
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		Assert.assertNotEquals(reporte.obtenerReportePorTerminal(observer.getRegistroBusqueda(), nombreTerminal).size(), 2);
	}
	
	@Test
	public void reporteEnTerminalFloresTest() {
		nombreTerminal= "flores";		
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado2);
		this.observer.notificarBusqueda(datoBuscado3);
		Assert.assertEquals(reporte.obtenerReportePorTerminal(observer.getRegistroBusqueda(), nombreTerminal).size(), 2);

	}
}
