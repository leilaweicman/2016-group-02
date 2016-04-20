package grupo2.tpAnual;
import java.util.Date;
import java.util.List;

import org.uqbar.geodds.Point;



public class CGP extends POI{
	private List<Servicio> _Servicios;
	
	public List<Servicio> getServicios(){
		return _Servicios;
	}
	public void setServicio(List<Servicio> ser) {
		this._Servicios = ser;
	}
	
	public boolean Busqueda (String texto){
		if(getPalabraClave().contains(texto))
		{
			return true;
		}else{
			for (Servicio ser : _Servicios){
				if(ser.getNombre().contains(texto)){
					return true;
				}
			}
			return false;
		}
	}

	public boolean estaDisponible(Date fecha){		
		//falta calculo
		return true;
	}
	//se lo delego a la comuna 
	public boolean estaCerca(Point p) {
		return (this._Comuna.laTenesAdentro(p));
	}
	
	
}
