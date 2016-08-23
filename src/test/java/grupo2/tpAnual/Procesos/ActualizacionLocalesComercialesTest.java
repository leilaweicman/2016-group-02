package grupo2.tpAnual.Procesos;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.Comercio;
import grupo2.tpAnual.Rango;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.EnviarMailFalloProceso;

public class ActualizacionLocalesComercialesTest {
	private OrigenesDeDatosPOIs origenesDeDatos;
	private ActualizacionLocalesComerciales proceso;
	public EnviarMailFalloProceso config1;
	List<AccionEnCasoDeFallo> configuraciones;
	Rango unRango;
	Rango otroRango;
	Rango rango;

	@Before
	public void init() throws IOException {
		origenesDeDatos = new OrigenesDeDatosPOIs();
		unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
		otroRango = new Rango(3, LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
		rango = new Rango(3, LocalTime.of(15, 0, 0), LocalTime.of(18, 30, 0));

		configuraciones = new ArrayList<>();
		config1 = new EnviarMailFalloProceso();
		configuraciones.add(config1);
		List<Rango> listaRangos = Arrays.asList(unRango, otroRango, rango);

		Comercio comercio = new Comercio("Carrousel", Point.and(-34.664837, -58.385674), listaRangos);
		origenesDeDatos.agregarPOI(comercio);

		proceso = new ActualizacionLocalesComerciales(14, new LocalDate(), configuraciones, origenesDeDatos);

	}

	@Test
	public void ejecutarProcesoTest() {
		proceso.ejecutarProceso();
		Comercio com = (Comercio) origenesDeDatos.getPOIs().iterator().next();
		Assert.assertEquals(com.getPalabraClave().size(), 4);
	}
}
