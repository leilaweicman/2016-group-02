package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import grupo2.tpAnual.Observers.ObserverBusqueda;
@Entity
@Table(name ="Usuario")
public class Usuario {
	@Id @GeneratedValue @Column(name="id_usuario")
	private long id; 
	
	private List<ObserverBusqueda> accionesBusqueda = new ArrayList<ObserverBusqueda>();
	@ManyToOne @Column(name="comuna") @JoinColumn(name="id_comuna")
	public Comuna comuna;

	public long getId(){
		return id;
	}
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