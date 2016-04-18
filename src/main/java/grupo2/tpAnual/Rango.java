package grupo2.tpAnual;
import org.joda.time.DateTimeConstants;
import org.joda.time.Hours;

public class Rango {
	//Falta definir bien bien como usarlo
	
	private DateTimeConstants dia;
	private Hours horaDesde;
	private Hours horaHasta;	
	
	public Hours getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(Hours horaDesde) {
		this.horaDesde = horaDesde;
	}
	public Hours getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(Hours horaHasta) {
		this.horaHasta = horaHasta;
	}
	public DateTimeConstants getDia() {
		return dia;
	}
	public void setDia(DateTimeConstants dia) {
		this.dia = dia;
	}
}
