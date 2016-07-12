package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import grupo2.tpAnual.Usuario;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;


public class AsignacionDeAccionesParaUsuarios extends Proceso {

	public AsignacionDeAccionesParaUsuarios(int hora, LocalDate fecha, 
			List<AccionEnCasoDeFallo> configuraciones, OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuraciones, origenesDeDatos);
		
	}
	public List<Usuario> listaUsuarios;
	public List<ObserverBusqueda> accionesParaAgregarAUsuario=new ArrayList<>();
	public List<ObserverBusqueda> accionesParaSacarAUsuario=new ArrayList<>();
	
	public List<Usuario> getUsuariosSegunCriterio(){
		listaUsuarios=new ArrayList<>();
		listaUsuarios.addAll(this.criterio.dameUsuarios(this.deComuna));
		return listaUsuarios;
	}
	
	public void setAccionesParaAgregarAUsuario(ObserverBusqueda accion){
		this.accionesParaAgregarAUsuario.add(accion);
	}
	
	public List<ObserverBusqueda> getAccionesParaSacarAUsuario(){
		return this.accionesParaSacarAUsuario;
	}
	
	public void setAccionesParaSacarAUsuario(ObserverBusqueda accion){
		this.accionesParaSacarAUsuario.add(accion);
	}
	
	public List<ObserverBusqueda> getAccionesParaAgregarAUsuario(){
		return this.accionesParaAgregarAUsuario;
	}
	
	@Override
	public void ejecutarProceso() {
		
		this.listaUsuarios.forEach(usuario -> usuario.agregarObserversBusqueda(this.accionesParaAgregarAUsuario));
		
		this.listaUsuarios.forEach(usuario -> usuario.quitarObserversBusqueda(this.accionesParaSacarAUsuario));
	
	}
	
}
