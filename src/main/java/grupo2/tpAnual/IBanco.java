package grupo2.tpAnual;

import java.util.ArrayList;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public interface IBanco {
	
	public Point getUbicacion();
	
	public List<Rango> getRango();

	public void setRango(ArrayList<Rango> rango);

	public void addRango(Rango rango);

	public boolean estaDisponible(DateTime momento, String nombre);

	public boolean estaCerca(Point coordenadaDeseada);

}
