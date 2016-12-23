package grupo2.tpAnual.DBTests;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ServiciosExternos.ServicioExternoBanco;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.OrigenesDeDatos.BancosExternos;
import grupo2.tpAnual.OrigenesDeDatos.BancosConRedis;

public class RedisTest {
	private OrigenesDeDatos mapa;
	private ServicioExternoBanco serv;
	
	@Before
	public void init(){
	serv = Mockito.mock(ServicioExternoBanco.class);
	mapa = new BancosExternos(serv);
	mapa = new BancosConRedis(mapa,serv);
	}
	
	@Test
	public void testVerificoSiUsaRedis(){
		String json= "[" + "{ \"banco\": \"Banco Frances\"," + "\"x\": -35.95122," + "\"y\": 7.455589,"
				+ "\"sucursal\": \"Lanús\"," + "\"gerente\": \"Aquiles Bailo\","
				+ " \"servicios\": [ \"cambio moneda extranjera\", \"depósitos\", \"pago de deudas\", \"cobro de sueldos\", \"tramite de tarjetas\", \"\", \"\", \"\" ]"
				+ " }" + "]";
		Mockito.when(serv.busqueda("Banco Galicia","")).thenReturn(json);
		mapa.busqueda("Banco Galicia");
		mapa.busqueda("Banco Galicia");
		Mockito.verify(serv).busqueda("Banco Galicia", "");
	}
	
}
