package grupo2.tpAnual;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import java.sql.Time;
import java.util.*;

public class Comercio extends POI{
	private Rubro rubro;
	private ArrayList<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	
	public ArrayList<Rango> getRango() {
		return rangoDisponibilidad;
	}

	public void setRango(ArrayList<Rango> rango) {
		this.rangoDisponibilidad = rango;
	}
	
	public void addRango(Rango rango) {
		this.rangoDisponibilidad.add(rango);
	}
	
	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rub) {
		this.rubro = rub;
	}
	
	public boolean Busqueda(String Texto){
		return getPalabraClave().contains(Texto);
	}
		 
	public boolean estaDisponible(DateTime momento, String nombre){		
		int dia= momento.getDayOfWeek();
		int hora = momento.getHourOfDay();
		int minutos = momento.getMinuteOfHour();
		int segundos = momento.getSecondOfMinute();
		
		Time horaCompleta = new Time(hora, minutos, segundos);
		boolean disponible = false;
		for (Rango rango : rangoDisponibilidad){
			  
			if( (dia == rango.getDay()) && 
					((rango.getHoraD()).compareTo(horaCompleta) == -1)
					 && ((rango.getHoraH()).compareTo(horaCompleta) == 1)){
				disponible=true;
				
			}
			
		}
		
		return disponible;
	}
	
	public boolean estaCerca(Point coordenadaDeseada){
		return (this.ubicacion.distance(coordenadaDeseada)<rubro.getRadioCercania());
	}
		
}