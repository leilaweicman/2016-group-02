package grupo2.tpAnual;
import java.sql.Time;
import java.time.LocalTime;


public class Rango {	
	private String dia;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	
	private Integer day;
	private Time horaD;
	private Time horaH;
	
	
	
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
	
	
}