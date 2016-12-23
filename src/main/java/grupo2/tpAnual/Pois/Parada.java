package grupo2.tpAnual.Pois;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import javax.persistence.*;

@Entity
@DiscriminatorValue("4")
public class Parada extends POI {
	
	@Column
	private String linea;

	public Parada(String nombre, Point ubicacion, String linea) {
		super(nombre, ubicacion);
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
		return true;
	}

}