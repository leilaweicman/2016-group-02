package grupo2.tpAnual;

import java.time.LocalTime;

public class Rango {
	private Integer dia;
	private LocalTime horaDesde;
	private LocalTime horaHasta;

	public Rango(Integer day, LocalTime horaD, LocalTime horaH) {
		this.dia = day;
		this.horaDesde = horaD;
		this.horaHasta = horaH;
	}

	public Integer getDay() {
		return dia;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public boolean estaDisponible(int dia, LocalTime horaCompleta) {

		boolean disponible = false;
		if ((dia == this.getDay()) && ((this.getHoraDesde()).compareTo(horaCompleta) == -1)
				&& ((this.getHoraHasta()).compareTo(horaCompleta) == 1)) {
			disponible = true;
		}

		return disponible;
	}
}