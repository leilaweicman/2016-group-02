package grupo2.tpAnual;
import org.joda.time.DateTimeConstants;
import org.joda.time.Hours;

public class Rango {	
	private String dia;
	private String horaDesde;
	private String horaHasta;	
	
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
}
