package grupo2.tpAnual;


import java.time.LocalTime;

import static java.lang.System.out;

public class Rango {
	private Integer day;
	private LocalTime horaD;
	private LocalTime horaH;

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public LocalTime getHoraD() {
		return horaD;
	}

	public void setHoraD(LocalTime horaD) {
		this.horaD = horaD;
	}

	public LocalTime getHoraH() {
		return horaH;
	}

	public void setHoraH(LocalTime horaH) {
		this.horaH = horaH;
	}
	
	public boolean estaDisponible(int dia, LocalTime horaCompleta) {
		
		boolean disponible = false;
		if ((dia == this.getDay()) && ((this.getHoraD()).compareTo(horaCompleta) == -1)
				&& ((this.getHoraH()).compareTo(horaCompleta) == 1)) {
			disponible = true;
		}
		
		return disponible;
	}
}