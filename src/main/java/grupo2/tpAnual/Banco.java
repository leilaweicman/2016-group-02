package grupo2.tpAnual;
import java.util.ArrayList;
import java.util.Date;

import org.uqbar.geodds.Point;

//import org.joda.time.DateTimeConstants;

public class Banco extends POI {	
	private ArrayList<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	private ArrayList<String> days = new ArrayList<String>();
	
	//constructor
	public Banco(){
		days.add("lunes");
		days.add("martes");
		days.add("miercoles");
		days.add("jueves");
		days.add("viernes");
		
		for (String day : days){
			Rango unRango = new Rango();
			unRango.setDia(day);
			unRango.setHoraDesde("10:00");
			unRango.setHoraHasta("15:00");
			rangoDisponibilidad.add(unRango);
		}
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
	
	public boolean estaCerca(Point coordenadaDeseada){
		return (this._Ubicacion.distance(coordenadaDeseada)<0.5);
	}
	
}
