package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import ServiciosExternos.ServicioExternoBanco;
import grupo2.tpAnual.Banco;
import grupo2.tpAnual.FromJsonToMap;
import grupo2.tpAnual.POI;
import redis.clients.jedis.Jedis;

public class OrigenesDeDatosBancosConRedis extends Decorator{

	private ServicioExternoBanco mapaBancoExterno;
	private Jedis jedis;
	private static final String BANCOS = "Bancos";
	
	public OrigenesDeDatosBancosConRedis(OrigenesDeDatos origen, ServicioExternoBanco servicio) {
		super(origen);
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

	private POI adaptar(Map<String, Object> map) {
		List<String> servicios = (List<String>) map.getOrDefault("servicios", new ArrayList<>());
		String nombre = (String) map.getOrDefault("banco", new RuntimeException());
		double latitud = (double) map.get("x");
		double longitud = (double) map.get("y");

		POI poi = new Banco(nombre, Point.and(latitud, longitud));
		poi.setPalabrasClaves(servicios);
		return poi;
	}

}
