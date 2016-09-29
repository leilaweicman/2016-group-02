package grupo2.tpAnual;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosBancoExterno;
import redis.clients.jedis.Jedis;

public class RedisTest {
	private Jedis jedis;
	private OrigenesDeDatosBancoExterno mapa;
	private String json;
	private String json3;
	private String json2;
	
	@Before
	public void init(){
	 mapa = new OrigenesDeDatosBancoExterno(null); 
	 
	 jedis = mapa.getJedis();
	 jedis.del("Bancos");
	json = "[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }" + "]";
	json2 = "[" + "{ \"banco\": \"Banco Frances\"," + "\"x\": -35.95122," + "\"y\": 7.455589,"
			+ "\"sucursal\": \"Lanús\"," + "\"gerente\": \"Aquiles Bailo\","
			+ " \"servicios\": [ \"cambio moneda extranjera\", \"depósitos\", \"pago de deudas\", \"cobro de sueldos\", \"tramite de tarjetas\", \"\", \"\", \"\" ]"
			+ " }" + "]";
	
	json3 = "[" + "{ \"banco\": \"Banco Galicia\"," + "\"x\": -45558.555," + "\"y\": 7.58889,"
			+ "\"sucursal\": \"Devoto\"," + "\"gerente\": \"Susana Torio\","
			+ " \"servicios\": [ \"venta al por mayor\", \"depósitos\", \"impuestos\", \"dolar\", \"plazo fijo\", \"\", \"\", \"\" ]"
			+ " }" + "]";

	}
	
	@Test
	public void testPruebaRedis(){
		jedis.lpush("Bancos", json);
		String value = jedis.lpop("Bancos");
		Assert.assertEquals(value.compareTo(json),0);
	}
	
	@Test
	public void testBancoExterno(){
		jedis.lpush("Bancos", json2);
		
		 List<POI> pois = mapa.busqueda("hola");
		 Assert.assertEquals(pois.get(0).getNombre(), "Banco Frances");
	}
	
	@Test
	public void testSeGuardanTodosEnRedis(){
		jedis.lpush("Bancos", json3);
		jedis.lpush("Bancos", json2);
		jedis.lpush("Bancos", json);
		List<String> respuestas = jedis.lrange("Bancos", 0, -1);
	    Assert.assertEquals(respuestas.size(), 3);
	}
	
	@Test
	public void testSeGuardanTodosEnRedisYSeConviertenEnPois(){
		jedis.lpush("Bancos", json3);
		jedis.lpush("Bancos", json2);
		jedis.lpush("Bancos", json);
	    Assert.assertEquals(mapa.busqueda("alo").get(2).getClass(), Banco.class);
	    Assert.assertEquals(mapa.busqueda("alo").get(2).getNombre(),"Banco Galicia");
	}
	
	@After
	public void cerrarConexion(){
		jedis.close();
	}
	
}
