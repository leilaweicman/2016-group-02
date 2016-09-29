package grupo2.tpAnual;
import java.util.List;
import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;


public interface datosDeBusquedaRepositoryDAO extends DAO<DatosDeBusqueda, ObjectId>{
	
	public Stream<DatosDeBusqueda> obtenerPorNombre(String nombreTerminal);
	
	public void agregarDatosBusqueda(DatosDeBusqueda registroBusqueda);
	
	public List<DatosDeBusqueda> consultarDatos();
}
