package grupo2.tpAnual;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;

import ServiciosExternos.ServicioExternoBanco;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosBancoExterno;
import redis.clients.jedis.Jedis;

public class OrigenesDeDatosBancoExternoTest {

	private OrigenesDeDatosBancoExterno origenDeDatos;
	
	@Mock
	private ServicioExternoBanco bancos; 
	
	@Before
	public void init() {
		bancos = Mockito.mock(ServicioExternoBanco.class);
	}

	@After
	public void after(){
		String key = origenDeDatos.getKeyJedis();
		origenDeDatos.getJedis().del(key);
	}
	@Test
	public void adaptarNombreTest(){
		Mockito.when(bancos.busqueda("", "")).thenReturn( "[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }" + "]");
		origenDeDatos = new OrigenesDeDatosBancoExterno(bancos);
		List<POI> bancos = origenDeDatos.busqueda("Banco de la Plaza");
		Assert.assertEquals(bancos.get(0).getNombre(), "Banco de la Plaza");		
	}

	@Test
	public void adaptarServiciosTest(){
		Mockito.when(bancos.busqueda("", "")).thenReturn( "[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }" + "]");
		origenDeDatos = new OrigenesDeDatosBancoExterno(bancos);
		List<POI> bancos = origenDeDatos.busqueda("cobro cheques");
		Assert.assertEquals(bancos.get(0).getPalabraClave().get(0), "cobro cheques");
	}
	
	@Test
	public void adaptarUbicacionTest(){
		Mockito.when(bancos.busqueda("", "")).thenReturn( "[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.5," + "\"y\": 72.5,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }" + "]");
		origenDeDatos = new OrigenesDeDatosBancoExterno(bancos);
		List<POI> bancos = origenDeDatos.busqueda("depósitos");
		Assert.assertEquals(bancos.get(0).getUbicacion().toString(), new Point(-35.5,72.5).toString());
		
	}
	
	@Test(expected=RuntimeException.class)
	public void AdaptarBancoSinUbicacionTest(){
		Mockito.when(bancos.busqueda("", "")).thenReturn( "[" + "{ \"banco\": \"Banco de la Plaza\","
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }" + "]");
		origenDeDatos = new OrigenesDeDatosBancoExterno(bancos);
		origenDeDatos.busqueda("Hola");
	}
	
	@Test(expected=RuntimeException.class)
	public void AdaptarBancoSinNombreTest(){
		Mockito.when(bancos.busqueda("", "")).thenReturn( "[" + "{ \"x\": -35.5," + "\"y\": 72.5,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }" + "]");
		origenDeDatos = new OrigenesDeDatosBancoExterno(bancos);
		 origenDeDatos.busqueda("Hola");
	}
}
