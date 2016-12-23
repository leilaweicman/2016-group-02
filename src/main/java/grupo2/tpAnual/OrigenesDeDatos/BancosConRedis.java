package grupo2.tpAnual.OrigenesDeDatos;

import java.util.List;
import java.util.stream.Collectors;

import ServiciosExternos.ServicioExternoBanco;
import grupo2.tpAnual.FromJsonToMap;
import grupo2.tpAnual.Pois.POI;
import redis.clients.jedis.Jedis;

public class BancosConRedis extends OrigenesDeDatosBancos{

	private ServicioExternoBanco mapaBancoExterno;
	private Jedis jedis;
	private static final String BANCOS = "Bancos";
	
	public BancosConRedis(OrigenesDeDatos origen, ServicioExternoBanco servicio) {
		mapaBancoExterno = servicio;
		jedis = new Jedis("localhost");
		jedis.del(BANCOS);
	}
	
	@Override
	public List<POI> busqueda(String banco) {
		try {
			String json = jedis.get(BANCOS);
			if (json==null){
				json = this.mapaBancoExterno.busqueda(banco, "");
				jedis.set(BANCOS, json);
			}
			return FromJsonToMap.transformarAMap(json).stream().map((poiMap) -> adaptar(poiMap)).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
}

}
