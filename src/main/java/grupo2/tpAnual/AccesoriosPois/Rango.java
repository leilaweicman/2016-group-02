package grupo2.tpAnual.AccesoriosPois;

import java.time.LocalTime;
import javax.persistence.*;

@org.mongodb.morphia.annotations.Entity
@Entity
public class Rango {
	@Id	@GeneratedValue 
	@org.mongodb.morphia.annotations.Transient
	private Integer id;
	@Column
	@org.mongodb.morphia.annotations.Transient
	private Integer dia;
	@Column
	@org.mongodb.morphia.annotations.Transient
	private LocalTime horaDesde;
	@Column
	@org.mongodb.morphia.annotations.Transient
	private LocalTime horaHasta;

	public Rango(Integer day, LocalTime horaD, LocalTime horaH) {
		this.dia = day;
		this.horaDesde = horaD;
		this.horaHasta = horaH;
	}
	
	public Rango() {
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