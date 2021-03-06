package grupo2.tpAnual;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;
import grupo2.tpAnual.Repositorios.DatosDeBusquedaRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class Mapa {

	private List<OrigenesDeDatos> origenesDeDatos;
	private String nombre;
	private Usuario usuario;
	private DatosDeBusquedaRepository repositorioDB;
	
	public Mapa(List<OrigenesDeDatos> listaDeOrigenes, DatosDeBusquedaRepository repositorio) {
		origenesDeDatos = new ArrayList<OrigenesDeDatos>();
		this.origenesDeDatos.addAll(listaDeOrigenes);
		this.repositorioDB=repositorio;

	}

	public List<POI> busquedaRealizadaPorElUsuario(String txtABuscar) {
		long tiempoInicio = System.currentTimeMillis();
		List<POI> result = new ArrayList<POI>();

		
		this.origenesDeDatos.forEach(integracion -> result.addAll(integracion.busqueda(txtABuscar)));
		long tiempoFin = System.currentTimeMillis();
		LocalDate today= LocalDate.now();
		long segundosTardados = (tiempoFin - tiempoInicio) / 1000;

		DatosDeBusqueda datosParaObserver = new DatosDeBusqueda(this.nombre, txtABuscar, segundosTardados,
				result.size(), today , result);
		
		this.repositorioDB.agregarDatosBusqueda(datosParaObserver); 
		
		usuario.getAccionesBusqueda().forEach(observer -> observer.notificarBusqueda(datosParaObserver));

		return result;
	}
	
	public POI buscarPorId (Integer id){
		List<OrigenesDeDatosPOIs> list = new ArrayList<OrigenesDeDatosPOIs>();
		for (int i = 0; i < this.origenesDeDatos.size(); i++) {			
			if(this.origenesDeDatos.get(i) instanceof OrigenesDeDatosPOIs)
			{
				list.add((OrigenesDeDatosPOIs) this.origenesDeDatos.get(i));
			}
		}
		List<POI> result = new ArrayList<POI>();
		list.forEach(inter -> result.add(inter.buscarPorId(id)));
		if(result.size()> 0){
			return result.get(0);
		}
		return null;	
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
