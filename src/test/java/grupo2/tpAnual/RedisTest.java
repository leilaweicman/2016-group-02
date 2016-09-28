package grupo2.tpAnual;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ServiciosExternos.CentroDTO;
import ServiciosExternos.ServicioExternoBanco;
import ServiciosExternos.ServicioExternoCentroDTO;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosBancoExterno;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosCentroDTO;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import redis.clients.jedis.Jedis;

public class RedisTest {
	private Jedis jedis;
	private CentroDTO centroDTO1;
	private CentroDTO centroDTO2;
	private CentroDTO centroDTO3;
	
	private ServicioExternoBanco bancosStub;
	private ServicioExternoCentroDTO centrosStub;
	
	private OrigenesDeDatosBancoExterno mapaBancos;
	private OrigenesDeDatosCentroDTO mapaCentroDTO;
	
	
	
	@Before
	public void init(){
	 jedis = new Jedis("localhost");
		this.bancosStub = Mockito.mock(ServicioExternoBanco.class);
		this.mapaBancos= new OrigenesDeDatosBancoExterno(bancosStub);
		
		this.centrosStub = Mockito.mock(ServicioExternoCentroDTO.class);
		this.mapaCentroDTO = new OrigenesDeDatosCentroDTO(centrosStub);
		
		List<CentroDTO> respuesta = Arrays.asList(new CentroDTO(1, "Juan B Justo 1841"));
		Mockito.when(centrosStub.busqueda("centros")).thenReturn(respuesta);
		
		Mockito.when(bancosStub.busqueda("dolar", "")).thenReturn( "[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }" + "]");
		
	}
	
	@Test
	public void persistirDatosDeBusquedaDTO(){
		
	}
	@Test
	public void persistirDatosDeBusquedaBancos(){
		
	}
	@Test
	public void busquedaIntegradora(){
		
	}
	
	@After
	public void cerrarConexion(){
		jedis.close();
	}
	
}
