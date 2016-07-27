package grupo2.tpAnual.Procesos;


import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;
import grupo2.tpAnual.Comercio;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public class ActualizacionLocalesComerciales extends Proceso {
	//HAY QUE VER DE DONDE OBTENGO EL ORIGEN

	private String origen = "src" + File.separator + "test" + File.separator + "java" + File.separator + "grupo2" + File.separator + "tpAnual" + File.separator + "Procesos" + File.separator + "prueba.txt";
	public ActualizacionLocalesComerciales(int hora, LocalDate fecha, List<AccionEnCasoDeFallo> configuraciones, OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuraciones, origenesDeDatos);

	}

	@Override
	public void ejecutarProceso() {
		int cantidadElementosAfectados = 0;
		try {
			String response = getFile(origen);		
			String[] componente = response.split(";");
			//Obtengo unicamente los que son comercio de la busqueda de pois
			List<Comercio> comercios = this.origenesDeDatos.getPOIs().stream().filter(x-> x instanceof Comercio)
										.map(p -> (Comercio) p).collect(Collectors.toList());
			for(Comercio com : comercios){
				if(com.getNombre().equals(componente[0])){
					com.setPalabrasClaves(Arrays.asList(componente[1].split(" ")));
					cantidadElementosAfectados = cantidadElementosAfectados + 1;
				}
			}	
		} catch (IOException e) {
			this.configuracionesFallo.forEach(configuracion -> configuracion.ejecutarConfiguracionPorFallo(this));
			this.setEstadoProceso(false);
		}
		this.log.loguearProceso(new DatosParaLogEjecucionProcesos(this.getFechaEjecucion(), this.getHoraEjecucion(),
				this.ejecucionExitosa, cantidadElementosAfectados));
	}
	
	public String getFile(String ruta) throws IOException{		
		File file = new File(ruta);
		return FileUtils.readFileToString(file);
	}
}