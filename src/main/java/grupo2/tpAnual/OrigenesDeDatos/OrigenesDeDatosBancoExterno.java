package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import ServiciosExternos.ServicioExternoBanco;
import grupo2.tpAnual.Banco;
import grupo2.tpAnual.POI;
import grupo2.tpAnual.FromJsonToMap;
import grupo2.tpAnual.helpers.MapExtensions;

public class OrigenesDeDatosBancoExterno implements OrigenesDeDatos {
	private ServicioExternoBanco mapaBancoExterno;

	public OrigenesDeDatosBancoExterno(ServicioExternoBanco servicio) {
		this.mapaBancoExterno = servicio;
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
		String nombre = (String) map.getOrDefault("banco", "");
		double latitud = (double) map.getOrDefault("x", 0.0);
		double longitud = (double) map.getOrDefault("y", 0.0);

		POI poi = new Banco(nombre, Point.and(latitud, longitud));
		poi.setPalabrasClaves(servicios);
		return poi;
	}

}
