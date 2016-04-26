package grupo2.tpAnual;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class CGP extends POI{
	private List<Servicio> servicios;
	
	public List<Servicio> getServicios(){
		return servicios;
	}
	public void setServicios(List<Servicio> ser) {
		this.servicios = ser;
	}
	
	public boolean Busqueda (String texto){
		
		if(getPalabraClave().contains(texto))
		{
			return true;
		}else{
			for (Servicio ser : servicios){
				if(ser.getNombre().contains(texto)){
					return true;
				}
			}
			return false;
		}
	}
	
	public boolean estaDisponible(DateTime momento, String nombreServicio){		
		boolean disponible=false;
		if (nombreServicio != ""){
			
			for (Servicio servicio : servicios){
				
				if (servicio.getNombre()== nombreServicio){
					disponible = servicio.estaDisponible(momento);
				}
			}
			
		}else{
			int cont = 0;
			Servicio[] serviciosArray = new Servicio[servicios.size()];
			serviciosArray = servicios.toArray(serviciosArray);			
			while (disponible == false){
				cont++;
				disponible = serviciosArray[cont].estaDisponible(momento);			
			}
		}
		return true;
	}
	
	
	
	//se lo delego a la comuna 
	public boolean estaCerca(Point punto) {
		return (this.comuna.estaAdentro(punto));
	}
	
	
}