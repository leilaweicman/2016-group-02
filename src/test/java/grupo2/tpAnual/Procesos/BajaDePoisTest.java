package grupo2.tpAnual.Procesos;

import grupo2.tpAnual.Banco;
import grupo2.tpAnual.CGP;
import grupo2.tpAnual.Parada;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.BajaDePois;
import grupo2.tpAnual.Procesos.LogEjecucionProcesos;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.EnviarMailFalloProceso;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class BajaDePoisTest {

	private BajaDePois procesoBajaDePois;
	private OrigenesDeDatosPOIs origenesDeDatos;
	// private LogEjecucionProcesos log;
	private Banco banco;
	private Parada parada;
	private CGP cgp;
	public EnviarMailFalloProceso config1;

	@Before
	public void init() {
		origenesDeDatos = new OrigenesDeDatosPOIs();
		cgp = new CGP();
		parada = new Parada("linea 7");
		banco = new Banco();
		cgp.setNumeroVerificador(122);
		banco.setNumeroVerificador(123);
		parada.setNumeroVerificador(12224);
		origenesDeDatos.agregarPOI(banco);
		origenesDeDatos.agregarPOI(cgp);
		origenesDeDatos.agregarPOI(parada);
		List<AccionEnCasoDeFallo> configuraciones = new ArrayList<>();
		configuraciones.add(config1);
		procesoBajaDePois = new BajaDePois(17, new LocalDate(), configuraciones, origenesDeDatos);
	}

	@Test
	public void obtenerListaDeIDPOIs() throws Exception {
		assertTrue(this.procesoBajaDePois.getNumerosIdentificadoresDePois().contains(122));
	}

	@Test
	public void tamanoListaDeIDPOIs() throws Exception {
		Assert.assertEquals(this.procesoBajaDePois.getNumerosIdentificadoresDePois().size(), 2);
	}

	@Test
	public void ejecutarTest() {
		procesoBajaDePois.ejecutarProceso();
		Assert.assertEquals(origenesDeDatos.getPOIs().size(), 1);
	}
}