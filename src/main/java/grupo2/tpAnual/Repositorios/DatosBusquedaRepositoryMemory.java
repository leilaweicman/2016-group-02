package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

public class DatosBusquedaRepositoryMemory /*extends BasicDAO<DatosDeBusqueda, ObjectId>*/ implements DatosDeBusquedaRepository{
	
	public static DatosBusquedaRepositoryMemory instancia = new DatosBusquedaRepositoryMemory(); 
	
	private List<DatosDeBusqueda> listaDatosDeBusqueda;	
	
	public DatosBusquedaRepositoryMemory() {
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