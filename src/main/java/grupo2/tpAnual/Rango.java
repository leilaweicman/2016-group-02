package grupo2.tpAnual;

import java.time.LocalTime;

public class Rango {
	private Integer dia;
	private LocalTime horaDesde;
	private LocalTime horaHasta;

	public Integer getDay() {
		return dia;
	}

	public void setDia(Integer day) {
		this.dia = day;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(LocalTime horaD) {
		this.horaDesde = horaD;
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(LocalTime horaH) {
		this.horaHasta = horaH;
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