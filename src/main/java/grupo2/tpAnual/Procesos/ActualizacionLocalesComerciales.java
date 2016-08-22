package grupo2.tpAnual.Procesos;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.joda.time.LocalDate;

import grupo2.tpAnual.Comercio;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public class ActualizacionLocalesComerciales extends Proceso {
	private String origen;

	public ActualizacionLocalesComerciales(int hora, LocalDate fecha, List<AccionEnCasoDeFallo> configuraciones,
			OrigenesDeDatosPOIs origenesDeDatos) throws IOException {
		super(hora, fecha, configuraciones, origenesDeDatos);
		origen = IOUtils.toString(this.getClass().getResourceAsStream("/Procesos/prueba.txt"), "UTF-8");
	}

	@Override
	public void ejecutar() {
		String response = origen;
		String[] componente = response.split(";");
		List<Comercio> comercios = this.origenesDeDatos.getPOIs().stream().filter(x -> x instanceof Comercio)
				.map(p -> (Comercio) p).collect(Collectors.toList());
		for (Comercio com : comercios) {
			if (com.getNombre().equals(componente[0])) {
				com.setPalabrasClaves(Arrays.asList(componente[1].split(" ")));
				cantidadElementosAfectados++;
			}
		}
	}

}
