package grupo2.tpAnual;

import java.util.List;
import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

public class datosBusqRepositoryDAOImpl extends BasicDAO<DatosDeBusqueda, ObjectId> implements datosDeBusquedaRepositoryDAO{
	
	public datosBusqRepositoryDAOImpl(Class<DatosDeBusqueda> entityClass, Datastore ds) {
		super(entityClass, ds); 
	}
	
	public List<DatosDeBusqueda> consultarDatos(){		
		
		return null;
		
	}
	
	public Stream<DatosDeBusqueda> obtenerPorNombre(String nombreTerminal){
		Query<DatosDeBusqueda> query = createQuery().
				field("nombreTerminal").equal(nombreTerminal);
		
		//return (Stream<DatosDeBusqueda>) query.asList();
		return null;
		
	}
	
	public void agregarDatosBusqueda(DatosDeBusqueda registroBusqueda){
		
	}
}
