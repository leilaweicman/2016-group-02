package grupo2.tpAnual.Repositorios;


import org.bson.types.ObjectId;
import org.joda.time.LocalDate;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
@Entity("datosDeBusqueda")
//@Table(name="DatosDeBusqueda")
public class DatosDeBusqueda {
	//@Id @GeneratedValue  @Column(name="id_usuario")
	//private long id;
	@Id
	private ObjectId id;
	//@Column(name="txtBuscado")
	private String txtBuscado;
	//@Column(name="segundosTardados")
	private long segundosQueTardoLaBusqueda;
	//@Column(name="totalDeResultados")
	private int totalDeResultados;
	//@Column(name="fecha") @Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate fecha;
	//@Column(name="nombre")
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
