package grupo2.tpAnual.Repositorios;
import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;


public interface DatosDeBusquedaRepository {
	
	public List<DatosDeBusqueda> obtenerPorNombre(String nombreTerminal);
	
	public void agregarDatosBusqueda(DatosDeBusqueda registroBusqueda);
	
	public List<DatosDeBusqueda> consultarDatos();

	public List<Integer> obtenerTotalResultadosPorTerminal(String nombreTerminal);

	public Integer cantidadDeBusquedasDe(String nombre);
	
	public List<Integer> filtrar(String nombreTerminal, String cantidad, LocalDate desde, LocalDate hasta);

}
