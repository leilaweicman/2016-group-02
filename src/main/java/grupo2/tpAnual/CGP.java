package grupo2.tpAnual;
import java.util.List;


public class CGP {
	private List<Servicio> _Servicios;
	
	public List<Servicio> getServicios(){
		return _Servicios;
	}
	public void setServicio(List<Servicio> ser) {
		this._Servicios = ser;
	}
	
	public boolean Busqueda (CharSequence nombre){
		for (Servicio ser : _Servicios){
			if(ser.getNombre().contains(nombre)){
				return true;
			}
		}
		return false;
	}
}
