package grupo2.tpAnual;
import java.time.LocalTime;


public class Rango {	
	private String dia;
	private LocalTime horaDesde;
	private LocalTime horaHasta;	
	
	
	
	public LocalTime getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}
	public LocalTime getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(LocalTime horaHasta) {
		this.horaHasta = horaHasta;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String day) {
		this.dia = day;
	}
	
	
}
