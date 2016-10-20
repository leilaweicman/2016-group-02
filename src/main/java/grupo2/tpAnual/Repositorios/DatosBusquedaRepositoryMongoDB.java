package grupo2.tpAnual.Repositorios;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

public class DatosBusquedaRepositoryMongoDB extends BasicDAO<DatosDeBusqueda, ObjectId> implements DatosDeBusquedaRepository{
	
	public DatosBusquedaRepositoryMongoDB(Class<DatosDeBusqueda> entityClass, Datastore dataStore) {
		super(entityClass, dataStore);
	}
	
	public List<DatosDeBusqueda> consultarDatos(){		
		
		Query<DatosDeBusqueda> query = createQuery();		
		return query.asList();
		
	}
	
	public List<DatosDeBusqueda> obtenerPorNombre(String nombreTerminal){
		Query<DatosDeBusqueda> query = createQuery().
				field("nombreTerminal").equal(nombreTerminal);
		
		return query.asList();

	}
	
	public void agregarDatosBusqueda(DatosDeBusqueda registroBusqueda){
		save(registroBusqueda);
	}
	
	public List<Integer> obtenerTotalResultadosPorTerminal(String nombreTerminal) {
		
		List<DatosDeBusqueda> lista = this.obtenerPorNombre(nombreTerminal);
		//lista.stream()
		//TODO buscar en la query la cantidad de resultados
		
		return null;
	}

	public Integer cantidadDeBusquedasDe(String nombre) {
		return null;
	}
}
