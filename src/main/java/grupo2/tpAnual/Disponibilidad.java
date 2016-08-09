package grupo2.tpAnual;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Disponibilidad {

	private List<Rango> rangoDisponibilidad = new ArrayList<Rango>();

	public Disponibilidad(List<Rango> rango) {
		this.rangoDisponibilidad = rango;
	}

	public boolean estaDisponible(DateTime momento) {
		int dia = momento.getDayOfWeek();
		int hora = momento.getHourOfDay();
		int minutos = momento.getMinuteOfHour();
		int segundos = momento.getSecondOfMinute();

		LocalTime horaCompleta = LocalTime.of(hora, minutos, segundos);

		boolean disponible = false;

		disponible = this.rangoDisponibilidad.stream().anyMatch(rango -> rango.estaDisponible(dia, horaCompleta));

		return disponible;
	}

	public void setDisponibilidad(List<Rango> rango) {
		this.rangoDisponibilidad = rango;
	}

	public void addRango(Rango rango) {
		this.rangoDisponibilidad.add(rango);
	}

}
