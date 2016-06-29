package grupo2.tpAnual;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.BajaDePois;
import grupo2.tpAnual.Procesos.DatosParaLogEjecucionProcesos;
import grupo2.tpAnual.Procesos.LogEjecucionProcesos;

public class LogEjecucionProcesosTest {
	
	private LogEjecucionProcesos log;
	private BajaDePois proceso;
	private OrigenesDeDatosPOIs origenesDeDatos;
	private Banco banco;
	private CGP cgp;
	private Parada parada;
	
	@Before
	public void init(){
		this.log = new LogEjecucionProcesos();
		origenesDeDatos = new OrigenesDeDatosPOIs();
		banco = new Banco();
		cgp = new CGP();
		parada = new Parada("linea 224");
		banco.setNumeroVerificador(122);
		cgp.setNumeroVerificador(123);
		parada.setNumeroVerificador(155);
		origenesDeDatos.agregarPOI(banco);
		origenesDeDatos.agregarPOI(parada);
		origenesDeDatos.agregarPOI(cgp);
		this.proceso = new BajaDePois(17, new LocalDate(),null,origenesDeDatos);
	}
	@Test
	public void registrarDatosDeLog(){
		log.loguearProceso(new DatosParaLogEjecucionProcesos(new LocalDate(),18, false, 9));
		Assert.assertEquals(log.obtenerLog().size(),1);
	}
	
	@Test
	public void ejecucionDeProcesoConLogTest(){
		proceso.ejecutarProceso(log);
		Assert.assertEquals(log.obtenerLog().size(), 1);
	}

}
