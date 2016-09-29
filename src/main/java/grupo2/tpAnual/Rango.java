package grupo2.tpAnual;

import java.time.LocalTime;
import javax.persistence.*;

@org.mongodb.morphia.annotations.Entity
@Entity
@Table(name="Rango") 
public class Rango {
	@Id	@GeneratedValue @Column(name="id_rango")
	private Integer id;
	@Column(name="dia")
	private Integer dia;
	@Column(name="horaDesde")
	private LocalTime horaDesde;
	@Column(name="horaHasta")
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