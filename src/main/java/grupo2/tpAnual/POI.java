package grupo2.tpAnual;
import java.util.Date;

import org.uqbar.geodds.Point;

public abstract class POI {
	private Direccion _Direccion;
	private String _PalabraClave;
	protected Point _Ubicacion;
	//public Point Point; 
	
	public Direccion getDireccion() {
		return _Direccion;
	}

	public void setDireccion(Direccion dir) {
		this._Direccion = dir;
	}
	
	public void setPalabraClave(String pc){
		this._PalabraClave = pc;
	}
	public String getPalabraClave(){
		return _PalabraClave;
	}
	
	abstract boolean estaDisponible(Date fecha); 
	
	abstract boolean Busqueda(String texto);
	
	public void setUbicacion (double latitud, double longitud){
		this._Ubicacion=Point.and(latitud, longitud);
	}
	abstract boolean estaCerca(Point p);
	
	public Point getUbicacion (){
		return _Ubicacion;
	}
}
