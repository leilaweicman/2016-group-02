package grupo2.tpAnual;

import java.time.LocalTime;

@org.mongodb.morphia.annotations.Entity
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
		return this.dia.equals(dia) && horaDesde.isBefore(horaCompleta) && horaHasta.isAfter(horaCompleta);
	}
}