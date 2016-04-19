package grupo2.tpAnual;

import java.util.Date;

import org.uqbar.geodds.Point;

public class Comercio extends POI{
	private Rubro _Rubro;
	private Rango _Disponibilidad;
	
	public Rubro getRubro() {
		return _Rubro;
	}

	public void setRubro(Rubro rub) {
		this._Rubro = rub;
	}
	
	public boolean Busqueda(String Texto){
		return getPalabraClave().equals(Texto);
	}
	
	public Rango getDisponibilidad() {
		return _Disponibilidad;
	}

	public void setDisponibilidad(Rango dis) {
		this._Disponibilidad = dis;
	}
	
	public boolean estaDisponible(Date fecha){		
		//falta calculo
		return true;
	}
	
	public boolean estaCerca(Point coordenadaDeseada){
		return (this._Ubicacion.distance(coordenadaDeseada)<_Rubro.getRadioCercania());
	}
		
}
