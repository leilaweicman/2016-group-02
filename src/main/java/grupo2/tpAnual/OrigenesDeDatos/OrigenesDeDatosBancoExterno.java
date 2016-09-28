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

public class OrigenesDeDatosBancoExterno implements OrigenesDeDatos {
	private ServicioExternoBanco mapaBancoExterno;
	private Jedis jedis;
	
	public OrigenesDeDatosBancoExterno(ServicioExternoBanco servicio) {
		this.mapaBancoExterno = servicio;
		jedis = new Jedis();
	}

	@Override
	public List<POI> busqueda(String banco) {
		try {
			String json = this.mapaBancoExterno.busqueda(banco, "");

			return FromJsonToMap.transformarAMap(json).stream().map((poiMap) -> adaptar(poiMap))
					.collect(Collectors.toList());
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
