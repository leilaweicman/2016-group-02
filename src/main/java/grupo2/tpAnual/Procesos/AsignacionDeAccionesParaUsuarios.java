package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import grupo2.tpAnual.Usuario;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public class AsignacionDeAccionesParaUsuarios extends Proceso {

	public Criterio criterio;

	public AsignacionDeAccionesParaUsuarios(int hora, LocalDate fecha, List<AccionEnCasoDeFallo> configuraciones,
			OrigenesDeDatosPOIs origenesDeDatos, Criterio crit) {
		super(hora, fecha, configuraciones, origenesDeDatos);
		this.criterio = crit;
	}

	public List<Usuario> listaUsuarios = new ArrayList<>();
	public List<ObserverBusqueda> accionesParaAgregarAUsuario = new ArrayList<>();
	public List<ObserverBusqueda> accionesParaSacarAUsuario = new ArrayList<>();

	public Criterio getCriterio() {
		return criterio;
	}

	public void setAccionesParaAgregarAUsuario(ObserverBusqueda accion) {
		this.accionesParaAgregarAUsuario.add(accion);
	}

	public List<ObserverBusqueda> getAccionesParaSacarAUsuario() {
		return this.accionesParaSacarAUsuario;
	}

	public void setAccionesParaSacarAUsuario(ObserverBusqueda accion) {
		this.accionesParaSacarAUsuario.add(accion);
	}

	public void setListaUsuariosAdmin(Usuario usuario) {
		this.listaUsuarios.add(usuario);
	}

	public List<ObserverBusqueda> getAccionesParaAgregarAUsuario() {
		return this.accionesParaAgregarAUsuario;
	}

	@Override
	public void ejecutar() {
		this.cantidadElementosAfectados = 0;
		this.listaUsuarios.addAll(this.criterio.dameUsuarios(this.deComuna));
		for (Usuario usuario : listaUsuarios) {
			usuario.agregarObserversBusqueda(this.accionesParaAgregarAUsuario);
			usuario.quitarObserversBusqueda(this.accionesParaSacarAUsuario);
			cantidadElementosAfectados++;
		}
	}
}
