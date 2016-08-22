package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.Banco;
import grupo2.tpAnual.CGP;
import grupo2.tpAnual.Parada;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.EnviarMailFalloProceso;

public class LogEjecucionProcesosTest {

	private BajaDePois proceso;
	private OrigenesDeDatosPOIs origenesDeDatos;
	private Banco banco;
	private CGP cgp;
	private Parada parada;
	public EnviarMailFalloProceso config1;

	@Before
	public void init() {
		origenesDeDatos = new OrigenesDeDatosPOIs();
		banco = new Banco();
		cgp = new CGP();
		parada = new Parada("linea 224");
		banco.setId(122);
		cgp.setId(123);
		parada.setId(155);
		origenesDeDatos.agregarPOI(banco);
		origenesDeDatos.agregarPOI(parada);
		origenesDeDatos.agregarPOI(cgp);
		List<AccionEnCasoDeFallo> configuraciones = new ArrayList<>();
		configuraciones.add(config1);
		this.proceso = new BajaDePois(17, new LocalDate(), configuraciones, origenesDeDatos);
	}

	@Test
	public void registrarDatosDeLog() {
		proceso.getLog().loguearProceso(new DatosParaLogEjecucionProcesos(new LocalDate(), 18, false, 9));
		Assert.assertEquals(proceso.getLog().obtenerLog().size(), 1);
	}

	@Test
	public void ejecucionDeProcesoConLogTest() {
		proceso.ejecutarProceso();
		Assert.assertEquals(proceso.getLog().obtenerLog().size(), 1);
	}

}
