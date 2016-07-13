package grupo2.tpAnual.Procesos;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.Comercio;
import grupo2.tpAnual.Rango;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.EnviarMailFalloProceso;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActualizacionLocalesComercialesTest {
	private OrigenesDeDatosPOIs origenesDeDatos;
	//private LogEjecucionProcesos log;
	private ActualizacionLocalesComerciales proceso;
	public EnviarMailFalloProceso config1;	
	List<AccionEnCasoDeFallo> configuraciones;
	Rango unRango;
	Rango otroRango;
	Rango rango;
	
	@Before
	public void init() {
		origenesDeDatos = new OrigenesDeDatosPOIs();
		unRango = new Rango();
		otroRango = new Rango();
		rango = new Rango();
		
		unRango.setDia(1);
		unRango.setHoraDesde(LocalTime.of(9, 0, 0));
		unRango.setHoraHasta(LocalTime.of(18, 0, 0));

		otroRango.setDia(3);
		otroRango.setHoraDesde(LocalTime.of(9, 0, 0));
		otroRango.setHoraHasta(LocalTime.of(13, 0, 0));

		rango.setDia(3);
		rango.setHoraDesde(LocalTime.of(15, 0, 0));
		rango.setHoraHasta(LocalTime.of(18, 30, 0));
		
		configuraciones= new ArrayList<>();
		config1 = new EnviarMailFalloProceso ();
		configuraciones.add(config1);
		List<Rango>  listaRangos = Arrays.asList(unRango, otroRango, rango);
		
		
		Comercio comercio = new Comercio(listaRangos, "Carrousel");
		origenesDeDatos.agregarPOI(comercio);

		proceso = new ActualizacionLocalesComerciales(14, new LocalDate(),configuraciones,origenesDeDatos);
		
		
	}
	
	//@Test
	//No es un test, simplemente lo corro para ver si lee bien el archivo
	//public void obtenerStringDeArchivo() throws Exception {
	//	System.out.println(proceso.getFile("C:\\Users\\pc hogar.pc\\Desktop\\prueba.txt")); 
	//}
	
	@Test
	public void ejecutarProcesoTest() {
		proceso.ejecutarProceso();
		Comercio com = (Comercio)origenesDeDatos.getPOIs().iterator().next() ;
		Assert.assertEquals(com.getPalabraClave().size(), 4);
	}
}
