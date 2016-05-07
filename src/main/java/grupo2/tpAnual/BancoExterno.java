package grupo2.tpAnual;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class BancoExterno extends POI {
	public String banco;
	public double x;
	public double y;
	public String sucursal;
	public String gerente;
	public List<String> servicios;
	@Override
	boolean estaDisponible(DateTime momento, String nombreServicio) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	boolean estaCerca(Point coordenadaDeseada) {
		// TODO Auto-generated method stub
		return false;
	}	
}
