package grupo2.tpAnual.AccesoriosPois;
import javax.persistence.Embeddable;

import org.uqbar.geodds.Point;

@Embeddable
public class PointComuna {
	private double latitud;
	private double longitud;
	public PointComuna(double latitud, double longitud){
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public Point GetPoint() {
		 return new Point(this.latitud, this.longitud);
	}
}
