package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import grupo2.tpAnual.Usuario;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;


public class AsignacionDeAccionesParaUsuarios extends Proceso {

	public AsignacionDeAccionesParaUsuarios(int hora, LocalDate fecha, List<AccionEnCasoDeFallo> configuraciones, OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuraciones, origenesDeDatos);		
	}
	public List<Usuario> listaUsuarios = new ArrayList<>();		
	public List<ObserverBusqueda> accionesParaAgregarAUsuario=new ArrayList<>();
	public List<ObserverBusqueda> accionesParaSacarAUsuario=new ArrayList<>();
	
	public List<Usuario> getUsuariosSegunCriterio(){		
		this.listaUsuarios.addAll(this.criterio.dameUsuarios(this.deComuna));		
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
	
	public void setListaUsuariosAdmin(Usuario usuario){
		this.listaUsuarios.add(usuario);
	}
	
	public List<ObserverBusqueda> getAccionesParaAgregarAUsuario(){
		return this.accionesParaAgregarAUsuario;
	}
	
	@Override
	public void ejecutarProceso() {		
		int cantidadElementosAfectados=0;		
		try{			
			if(listaUsuarios != null){							
				for(Usuario usuario : listaUsuarios){				
					if(accionesParaAgregarAUsuario != null){					
						usuario.agregarObserversBusqueda(this.accionesParaAgregarAUsuario);
					}			
					if(accionesParaSacarAUsuario != null){
						usuario.quitarObserversBusqueda(this.accionesParaSacarAUsuario);
					}
					cantidadElementosAfectados++;				
				}
			}
			this.setEstadoProceso(true);		
		}catch (Exception e){
			this.configuracionesFallo.forEach(configuracion -> configuracion.ejecutarConfiguracionPorFallo(this));		
			this.setEstadoProceso(false);		
		}
		this.log.loguearProceso(new DatosParaLogEjecucionProcesos(this.getFechaEjecucion(), this.getHoraEjecucion(),
			this.ejecucionExitosa, cantidadElementosAfectados));
	}
}
