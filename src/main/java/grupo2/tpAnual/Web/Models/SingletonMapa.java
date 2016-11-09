package grupo2.tpAnual.Web.Models;

import java.util.Arrays;
import java.util.List;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMemory;

public class SingletonMapa {
	private static grupo2.tpAnual.Mapa instance;

	public static grupo2.tpAnual.Mapa get() {
		if (instance == null) {
			List<OrigenesDeDatos> listaDeOrigenes = Arrays.asList();
			DatosBusquedaRepositoryMemory repositorioDB = new DatosBusquedaRepositoryMemory();
			//TODO: Hay que pasarle los parametros correspondientes a mapa
			instance = new grupo2.tpAnual.Mapa(listaDeOrigenes, repositorioDB);
		}
		return instance;
	}
}
