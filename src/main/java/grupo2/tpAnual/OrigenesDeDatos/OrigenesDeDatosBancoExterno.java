package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import ServiciosExternos.ServicioExternoBanco;
import grupo2.tpAnual.Banco;
import grupo2.tpAnual.FromJsonToMap;
import grupo2.tpAnual.POI;
import redis.clients.jedis.Jedis;

public class OrigenesDeDatosBancoExterno implements OrigenesDeDatos {
	private ServicioExternoBanco mapaBancoExterno;
	private Jedis jedis;
	private static final String BANCOS = "Bancos";

	public OrigenesDeDatosBancoExterno(ServicioExternoBanco servicio) {
		this.mapaBancoExterno = servicio;
		jedis = new Jedis("localhost");
	}

	public String getKeyJedis() {
		return BANCOS;
	}

	public Jedis getJedis() {
		return jedis;
	}

	@Override
	public List<POI> busqueda(String banco) {
		try {
			List<String> jsons = jedis.lrange(BANCOS, 0, -1);
			List<POI> pois = new ArrayList<>();
			if (jsons.isEmpty()) {
				String json2 = this.mapaBancoExterno.busqueda(banco, "");
				jedis.lpush(BANCOS, json2);
				return FromJsonToMap.transformarAMap(json2).stream().map((poiMap) -> adaptar(poiMap))
						.collect(Collectors.toList());
			} else {
				for (String json : jsons) {
					pois.addAll(FromJsonToMap.transformarAMap(json).stream().map((poiMap) -> adaptar(poiMap))
							.collect(Collectors.toList()));
				}
				return pois;

			}
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
