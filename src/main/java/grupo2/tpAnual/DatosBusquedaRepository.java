package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatosBusquedaRepository {
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

	public Stream<DatosDeBusqueda> obtenerPorNombre(String nombreTerminal) {
		return listaDatosDeBusqueda.stream().filter(registro -> registro.getNombre().equals(nombreTerminal));
	}

	public List<Integer> obtenerTotalResultadosPorTerminal(String nombreTerminal) {
		return this.obtenerPorNombre(nombreTerminal).map(registro -> registro.getTotalDeResultados()).collect(Collectors.toList());
	}

	public Integer cantidadDeBusquedasDe(String nombre) {
		return this.obtenerTotalResultadosPorTerminal(nombre).stream().reduce(0, (a, b) -> a + b);
	}
}