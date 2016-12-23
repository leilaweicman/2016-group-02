package grupo2.tpAnual.AccesoriosPois;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Direccion {
	@Id	@GeneratedValue
	private Integer id;
	@Column
	private String calle;
	@Column
	private int altura;
	@Column
	private String zona;

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
