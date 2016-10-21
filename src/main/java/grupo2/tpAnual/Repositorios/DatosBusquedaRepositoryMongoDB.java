package grupo2.tpAnual.Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

public class DatosBusquedaRepositoryMongoDB extends BasicDAO<DatosDeBusqueda, ObjectId>
		implements DatosDeBusquedaRepository {

	public DatosBusquedaRepositoryMongoDB(Class<DatosDeBusqueda> entityClass, Datastore dataStore) {
		super(entityClass, dataStore);
	}

	public List<DatosDeBusqueda> consultarDatos() {

		Query<DatosDeBusqueda> query = createQuery();
		
		return query.asList();

	}

	public List<DatosDeBusqueda> obtenerPorNombre(String nombreTerminal) {
		Query<DatosDeBusqueda> query = createQuery().field("nombreTerminal").equal(nombreTerminal);

		return query.asList();

	}

	public void agregarDatosBusqueda(DatosDeBusqueda registroBusqueda) {
		save(registroBusqueda);
	}

	public List<Integer> obtenerTotalResultadosPorTerminal(String nombreTerminal) {

		//Query<DatosDeBusqueda> query = createQuery().field("nombreTerminal").equal(nombreTerminal);
		//Query<DatosDeBusqueda> query2= query.search("totalDeResultados");
		List<DatosDeBusqueda> lista = this.obtenerPorNombre(nombreTerminal);

		return lista.stream().map(datoDeBusqueda -> datoDeBusqueda.getTotalDeResultados()).collect(Collectors.toList());

	}

	public Integer cantidadDeBusquedasDe(String nombre) {

		return this.obtenerTotalResultadosPorTerminal(nombre).stream().reduce(0, (a, b) -> a + b);
	}
}
