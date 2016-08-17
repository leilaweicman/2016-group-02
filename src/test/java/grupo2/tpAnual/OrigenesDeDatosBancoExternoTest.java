package grupo2.tpAnual;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ServiciosExternos.ServicioExternoBanco;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosBancoExterno;

public class OrigenesDeDatosBancoExternoTest {

	private OrigenesDeDatosBancoExterno origenDeDatos;
	
	@Mock
	private ServicioExternoBanco bancos; 
	
	@Before
	public void init() {
		bancos = Mockito.mock(ServicioExternoBanco.class);
		origenDeDatos = new OrigenesDeDatosBancoExterno(bancos);
	}

	@Test
	public void busquedaTest(){
		Mockito.when(bancos.busqueda("Hola", "")).thenReturn( "[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }" + "]");
		List<POI> bancos = origenDeDatos.busqueda("Hola");
		Assert.assertEquals(bancos.get(0).getClass(), Banco.class);
	}

}
