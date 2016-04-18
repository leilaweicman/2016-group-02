package grupo2.tpAnual;
import org.joda.time.DateTimeConstants;
import org.joda.time.Hours;

public class Rango {
	//Falta definir bien bien como usarlo
	
	private DateTimeConstants diaDesde;
	private DateTimeConstants diaHasta;
	private Hours horaDesde;
	private Hours horaHasta;
	
	public DateTimeConstants getDiaDesde() {
		return diaDesde;
	}
	public void setDiaDesde(DateTimeConstants diaDesde) {
		this.diaDesde = diaDesde;
	}
	public DateTimeConstants getDiaHasta() {
		return diaHasta;
	}
	public void setDiaHasta(DateTimeConstants diaHasta) {
		this.diaHasta = diaHasta;
	}
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
}
