package grupo2.tpAnual.Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

public class DatosBusquedaRepositoryMongoDB extends BasicDAO<DatosDeBusqueda, ObjectId> implements DatosDeBusquedaRepository{
	
	public DatosBusquedaRepositoryMongoDB(Class<DatosDeBusqueda> entityClass, Datastore ds) {
		super(entityClass, ds); 
	}
	
	public List<DatosDeBusqueda> consultarDatos(){		
		
		return null;
		
	}
	
	public List<DatosDeBusqueda> obtenerPorNombre(String nombreTerminal){
		Query<DatosDeBusqueda> query = createQuery().
				field("nombreTerminal").equal(nombreTerminal);
		
		return query.asList();

	}
	
	public void agregarDatosBusqueda(DatosDeBusqueda registroBusqueda){
		
	}
	
	public List<Integer> obtenerTotalResultadosPorTerminal(String nombreTerminal) {
		return this.obtenerPorNombre(nombreTerminal).stream().map(registro -> registro.getTotalDeResultados()).collect(Collectors.toList());
	}

	public Integer cantidadDeBusquedasDe(String nombre) {
		return this.obtenerTotalResultadosPorTerminal(nombre).stream().reduce(0, (a, b) -> a + b);
	}
}
