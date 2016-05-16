package grupo2.tpAnual;

import java.sql.Time;
import static java.lang.System.out;

public class Rango {
	private Integer day;
	private Time horaD;
	private Time horaH;

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Time getHoraD() {
		return horaD;
	}

	public void setHoraD(Time horaD) {
		this.horaD = horaD;
	}

	public Time getHoraH() {
		return horaH;
	}

	public void setHoraH(Time horaH) {
		this.horaH = horaH;
	}
	
	public boolean estaDisponible(int dia, Time horaCompleta) {
		
		boolean disponible = false;
		if ((dia == this.getDay()) && ((this.getHoraD()).compareTo(horaCompleta) == -1)
				&& ((this.getHoraH()).compareTo(horaCompleta) == 1)) {
			disponible = true;
		}
		
		return disponible;
	}
}