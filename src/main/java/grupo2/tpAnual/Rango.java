package grupo2.tpAnual;
import java.time.LocalTime;


public class Rango {	
	private String dia;
	private String horaDesde;
	private String horaHasta;	
	
	private LocalTime hora;
	
	
	public String getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}
	public String getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String day) {
		this.dia = day;
	}
	
	
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
}
