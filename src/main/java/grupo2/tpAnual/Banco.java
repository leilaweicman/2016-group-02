package grupo2.tpAnual;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Banco extends POI {	
	private ArrayList<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	private ArrayList<Integer> dias = new ArrayList<Integer>();
	
	//constructor
	public Banco(){		
		dias.add(1);
		dias.add(2);
		dias.add(3);
		dias.add(4);
		dias.add(5);
		
		for (Integer dia : dias){
			Rango unRango = new Rango();
			unRango.setDay(dia);
			unRango.setHoraD(new Time (10,0,0));
			unRango.setHoraH(new Time (15,0,0));
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
		return getPalabraClave().contains(Texto);
	}
	
	public boolean estaDisponible(Date fecha){		
		//falta calculo
		return true; 
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
		return (this._Ubicacion.distance(coordenadaDeseada)<0.5);
	}
	
}
