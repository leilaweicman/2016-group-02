package grupo2.tpAnual;
import java.util.List;


public class CGP extends POI{
	private List<Servicio> _Servicios;
	
	public List<Servicio> getServicios(){
		return _Servicios;
	}
	public void setServicio(List<Servicio> ser) {
		this._Servicios = ser;
	}
	
	public boolean Busqueda (String texto){
		if(getPalabraClave().equals(texto))
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
}
