package grupo2.tpAnual;
import java.util.ArrayList;
import java.util.Date;

//import org.joda.time.DateTimeConstants;

public class Banco extends POI {	
	private ArrayList<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	
	//constructor
	public Banco(){
		/*Rango rango;
		rango.setDia(DateTimeConstants.MONDAY);
		this.addRango(rango);*/
	}
	
	public ArrayList<Rango> getRango() {
		return rangoDisponibilidad;
	}

	public void setRango(ArrayList<Rango> rango) {
		this.rangoDisponibilidad = rango;
	}
	
	public void addRango(Rango rango) {
		this.rangoDisponibilidad.add(rango);
	}
	
	
	public boolean Busqueda (String Texto){
		return getPalabraClave().equals(Texto);
	}
	
	public boolean estaDisponible(Date fecha){		
		//falta calculo
		return true;
	}
	
}
