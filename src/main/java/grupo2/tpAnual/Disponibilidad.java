package grupo2.tpAnual;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Disponibilidad {
	
	private List<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	
	public boolean estaDisponible(DateTime momento, String nombre) {
		int dia = momento.getDayOfWeek();
		int hora = momento.getHourOfDay();
		int minutos = momento.getMinuteOfHour();
		int segundos = momento.getSecondOfMinute();

		Time horaCompleta = new Time(hora, minutos, segundos);
		boolean disponible = false;
		for (Rango rango : rangoDisponibilidad) {
			
			disponible = rango.estaDisponible(dia, horaCompleta);
			/*if ((dia == rango.getDay()) && ((rango.getHoraD()).compareTo(horaCompleta) == -1)
					&& ((rango.getHoraH()).compareTo(horaCompleta) == 1)) {
				disponible = true;
			}*/
		}
		return disponible;
	}
	
}