package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;

public class Mapa {
	private List<OrigenesDeDatos> origenesDeDatos;
	private List<ObserverBusqueda> observersBusqueda;
	private String nombre;
	private Usuario usuario;

	public Mapa(List<OrigenesDeDatos> listaDeOrigenes) {
		observersBusqueda = new ArrayList<ObserverBusqueda>();
		origenesDeDatos = new ArrayList<OrigenesDeDatos>();
		this.origenesDeDatos.addAll(listaDeOrigenes);

	}

	public List<POI> busquedaRealizadaPorElUsuario(String txtABuscar) {
		long tiempoInicio = System.currentTimeMillis();
		List<POI> result = new ArrayList<POI>();

		this.observersBusqueda.addAll(usuario.enviarObservers());
		this.origenesDeDatos.forEach(integracion -> result.addAll(integracion.busqueda(txtABuscar)));
		long tiempoFin = System.currentTimeMillis();

		long segundosTardados = (tiempoFin - tiempoInicio) / 1000;

		DatosDeBusqueda datosParaObserver = new DatosDeBusqueda(this.nombre, txtABuscar, segundosTardados,
				result.size(), new LocalDate());
		this.observersBusqueda.forEach(observer -> observer.notificarBusqueda(datosParaObserver));

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
