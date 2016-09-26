package grupo2.tpAnual;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Direccion {
	@Id	@GeneratedValue
	private Integer id;
	private String calle;
	private int altura;
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

}
