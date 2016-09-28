package grupo2.tpAnual;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.LocalDate;
@Entity
@Table(name="DatosDeBusqueda")
public class DatosDeBusqueda {
	@Id @GeneratedValue  @Column(name="id_usuario")
	private long id;
	@Column(name="txtBuscado")
	private String txtBuscado;
	@Column(name="segundosTardados")
	private long segundosQueTardoLaBusqueda;
	@Column(name="totalDeResultados")
	private int totalDeResultados;
	@Column(name="fecha") @Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate fecha;
	@Column(name="nombre")
	private String nombre;

	public DatosDeBusqueda(String nombre, String texto, long segundos, int totalResultados, LocalDate fecha) {
		this.txtBuscado = texto;
		this.segundosQueTardoLaBusqueda = segundos;
		this.totalDeResultados = totalResultados;
		this.fecha = fecha;
		this.nombre = nombre;
	}

	public String getTxtBuscado() {
		return txtBuscado;
	}

	public long getSegundosQueTardoLaBusqueda() {
		return segundosQueTardoLaBusqueda;
	}

	public int getTotalDeResultados() {
		return totalDeResultados;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getNombre() {
		return nombre;
	}

}
