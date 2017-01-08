package grupo2.tpAnual.Observers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import grupo2.tpAnual.PersistentEntity;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;
import grupo2.tpAnual.Repositorios.Usuario;
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS )
public abstract class ObserverBusqueda extends PersistentEntity{
	
	@Id
	protected long id;
	
	private String nombre;	
	
	public abstract void notificarBusqueda(DatosDeBusqueda datosParaObserver);
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public long getId(){
		return this.id;
	}
	
	public boolean perteneceAUsuario(Usuario usuario){
		return usuario.tieneObserver(this);
	}
}
