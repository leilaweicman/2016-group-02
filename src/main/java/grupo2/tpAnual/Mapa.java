package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.joda.time.LocalDate;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;

@Entity
public class Mapa {
	@ElementCollection
	@OneToMany
	@JoinColumn
	private List<OrigenesDeDatos> origenesDeDatos;
	@Id
	private String nombre;
	@OneToOne
	private Usuario usuario;

	public Mapa(List<OrigenesDeDatos> listaDeOrigenes) {
		
		origenesDeDatos = new ArrayList<OrigenesDeDatos>();
		this.origenesDeDatos.addAll(listaDeOrigenes);

	}

	public List<POI> busquedaRealizadaPorElUsuario(String txtABuscar) {
		long tiempoInicio = System.currentTimeMillis();
		List<POI> result = new ArrayList<POI>();

		
		this.origenesDeDatos.forEach(integracion -> result.addAll(integracion.busqueda(txtABuscar)));
		long tiempoFin = System.currentTimeMillis();

		long segundosTardados = (tiempoFin - tiempoInicio) / 1000;

		DatosDeBusqueda datosParaObserver = new DatosDeBusqueda(this.nombre, txtABuscar, segundosTardados,
				result.size(), new LocalDate());
		usuario.accionesDeBusqueda().forEach(observer -> observer.notificarBusqueda(datosParaObserver));

		return result;
	}

	public List<OrigenesDeDatos> getOrigenesDeDatos() {
		return this.origenesDeDatos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setUsuario(Usuario user) {
		this.usuario = user;
	}
}
