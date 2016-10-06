package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("datosDeBusquedaRepository")
public class DatosBusquedaRepository {
	
	@Id
	private ObjectId id;

	@Reference
	private List<DatosDeBusqueda> listaDatosDeBusqueda;	
	
	public DatosBusquedaRepository() {
		this.listaDatosDeBusqueda = new ArrayList<DatosDeBusqueda>();
		
	}

	public void agregarDatosBusqueda(DatosDeBusqueda registroBusqueda) {
		this.listaDatosDeBusqueda.add(registroBusqueda);
	}

	public List<DatosDeBusqueda> consultarDatos() {
		return this.listaDatosDeBusqueda;
	}

	public List<DatosDeBusqueda> obtenerPorNombre(String nombreTerminal) {
		return listaDatosDeBusqueda.stream().filter(registro -> registro.getNombre().equals(nombreTerminal)).collect(Collectors.toList());
	}

	public List<Integer> obtenerTotalResultadosPorTerminal(String nombreTerminal) {
		return this.obtenerPorNombre(nombreTerminal).stream().map(registro -> registro.getTotalDeResultados()).collect(Collectors.toList());
	}

	public Integer cantidadDeBusquedasDe(String nombre) {
		return this.obtenerTotalResultadosPorTerminal(nombre).stream().reduce(0, (a, b) -> a + b);
	}
}