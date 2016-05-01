package grupo2.tpAnual;

import java.util.Date;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Parada extends POI{
	private String linea;
	
	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	@Override
	public boolean BusquedaParticular (String texto){
		return linea.equals(texto);		
	}
	
	public boolean estaCerca(Point coordenadaDeseada){
		return (this.ubicacion.distance(coordenadaDeseada)<0.1);
	}


	public boolean estaDisponible(DateTime momento, String nombreServicio) {
		//las paradas siempre estan disponibles
		return true;
	}
	
}