package grupo2.tpAnual;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Parada extends POI {
	private String linea;
	
	public Parada( String linea){
		this.linea = linea;
	}
	public String getLinea() {
		return linea;
	}

	@Override
	public boolean tieneTextoEnOtrosAtributos(String texto) {
		return linea.equals(texto);
	}

	@Override
	public boolean estaCerca(Point coordenadaDeseada) {
		return (this.ubicacion.distance(coordenadaDeseada) < 0.1);
	}

	@Override
	public boolean estaDisponible(DateTime momento, String nombreServicio) {
		// las paradas siempre estan disponibles
		return true;
	}

}