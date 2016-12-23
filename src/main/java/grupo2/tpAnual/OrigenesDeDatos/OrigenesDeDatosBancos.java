package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.POI;

public abstract class OrigenesDeDatosBancos extends OrigenesDeDatos{

	protected POI adaptar(Map<String, Object> map) {
		List<String> servicios = (List<String>) map.getOrDefault("servicios", new ArrayList<>());
		String nombre = (String) map.getOrDefault("banco", new RuntimeException());
		double latitud = (double) map.get("x");
		double longitud = (double) map.get("y");

		POI poi = new Banco(nombre, Point.and(latitud, longitud));
		poi.setPalabrasClaves(servicios);
		return poi;
	}
}
