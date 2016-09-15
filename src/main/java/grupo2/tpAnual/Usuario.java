package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import grupo2.tpAnual.Observers.ObserverBusqueda;

public class Usuario {
	@Id @GeneratedValue
	private long id; 
	@OneToMany
	@JoinColumn
	private List<ObserverBusqueda> accionesBusqueda = new ArrayList<ObserverBusqueda>();
	@ManyToOne
	public Comuna comuna;

	public void setComuna(Comuna com) {
		this.comuna = com;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public List<ObserverBusqueda> accionesDeBusqueda() {
		return this.accionesBusqueda;
	}

	public void agregarObserversBusqueda(List<ObserverBusqueda> observers) {
		this.accionesBusqueda.addAll(observers);
	}

	public List<ObserverBusqueda> getAccionesBusqueda() {
		return accionesBusqueda;
	}

	public void quitarObserversBusqueda(List<ObserverBusqueda> observers) {
		this.accionesBusqueda.removeAll(observers);

	}
}