package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.Web.Server;
@Entity
public class Usuario {
	@Id @GeneratedValue
	private long id; 
	private String nombre;
	@OneToMany 
	private List<ObserverBusqueda> accionesBusqueda = new ArrayList<ObserverBusqueda>();
	@ManyToOne 
	public Comuna comuna;
	
	private boolean esAdmin;
	
	
	public Usuario(){
		if(Server.inMemory){
			generarId();
		}
		
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre(){
		return this.nombre;
	}

	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
	}
	public void setComuna(Comuna com) {
		this.comuna = com;
	}

	public Comuna getComuna() {
		return comuna;
	}


	public void setAccionesBusqueda(List<ObserverBusqueda> observers) {
		this.accionesBusqueda.addAll(observers);
	}


	public void setAccionBusqueda(ObserverBusqueda observer) {
		this.accionesBusqueda.add(observer);
	}
	
	public List<ObserverBusqueda> getAccionesBusqueda() {
		return accionesBusqueda;
	}

	public void quitarObserversBusqueda(List<ObserverBusqueda> observers) {
		this.accionesBusqueda.removeAll(observers);

	}
	
	public void setEsAdmin(boolean admin){
		esAdmin = admin;
	}
	
	public boolean getEsAdmin(){
		return esAdmin;
	}
	
	private void generarId(){
		Random rnd = new Random();
		this.id = rnd.nextLong();
		//this.id = java.util.UUID.randomUUID().;
	}
}