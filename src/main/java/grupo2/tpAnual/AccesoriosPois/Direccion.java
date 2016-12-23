package grupo2.tpAnual.AccesoriosPois;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
public class Direccion {

	@Column
	private String calle;
	@Column
	private int altura;
	@Column
	private String zona;
	
	public Direccion(){
		
	}
	public Direccion(String calle, String zona) {
		this.calle = calle;
		this.zona = zona;
	}

	public String getCalle() {
		return calle;
	}

	public int getAltura() {
		return altura;
	}

	public String getZona() {
		return zona;
	}
	
	public void setCalle(String calle){
		this.calle = calle;
	}

}
