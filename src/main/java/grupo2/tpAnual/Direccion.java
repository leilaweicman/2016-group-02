package grupo2.tpAnual;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Direccion")
public class Direccion {
	@Id	@GeneratedValue @Column(name="id_direccion")
	private Integer id;
	@Column(name="calle")
	private String calle;
	@Column(name="altura")
	private int altura;
	@Column(name="zona")
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
