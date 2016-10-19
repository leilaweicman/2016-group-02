package grupo2.tpAnual.Repositorios;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

public class DatosBusquedaRepositoryMongoDB extends BasicDAO<DatosDeBusqueda, ObjectId> implements DatosDeBusquedaRepository{
	
	private Datastore datastore;
	
	public DatosBusquedaRepositoryMongoDB(Class<DatosDeBusqueda> entityClass, Datastore dataStore) {
		super(entityClass, dataStore);
		this.datastore=dataStore;
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
		datastore.save(registroBusqueda);
	}
	
	public List<Integer> obtenerTotalResultadosPorTerminal(String nombreTerminal) {
		return null;
	}

	public Integer cantidadDeBusquedasDe(String nombre) {
		return null;
	}
}
