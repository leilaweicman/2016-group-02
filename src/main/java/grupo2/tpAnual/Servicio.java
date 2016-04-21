package grupo2.tpAnual;
import java.sql.Time;
import java.util.ArrayList;
import org.joda.time.DateTime;

public class Servicio {
	private String nombre;
	private ArrayList<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String Nom) {
		this.nombre = Nom;
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

	public boolean estaDisponible(DateTime momento) {
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
	
}