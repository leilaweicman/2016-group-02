package grupo2.tpAnual;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public abstract class POI {
	private Direccion _Direccion;
	private List<String> _PalabraClave;
	protected Point _Ubicacion;
	protected Comuna _Comuna;
	//public Point Point; 
	
	public POI(){
		_PalabraClave = new ArrayList<String>();
	}
	
	public Direccion getDireccion() {
		return _Direccion;
	}

	public void setDireccion(Direccion dir) {
		this._Direccion = dir;
	}
	
	public void addPalabraClave(String pc){
		this._PalabraClave.add(pc);
	}
	public List<String> getPalabraClave(){
		return _PalabraClave;
	}
		
	abstract boolean estaDisponible (DateTime momento, String nombreServicio);
	 
	abstract boolean Busqueda(String texto);
	
	public void setUbicacion(double latitud, double longitud){
		this._Ubicacion=Point.and(latitud, longitud);
	}
	abstract boolean estaCerca(Point p);
	
	public Point getUbicacion (){
		return _Ubicacion;
	}
	
	public void setComuna(Comuna com){
		this._Comuna = com;
	}
}