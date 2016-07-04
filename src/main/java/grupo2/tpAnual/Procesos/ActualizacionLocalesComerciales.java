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
	private String origen = "src\\test\\java\\grupo2\\tpAnual\\Procesos\\prueba.txt";
	
	public ActualizacionLocalesComerciales(int hora, LocalDate fecha, AccionEnCasoDeFallo configuracion, OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuracion, origenesDeDatos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutarProceso(LogEjecucionProcesos log) {
		//WebResource.Builder builder = this.client.resource(origen).accept(MediaType.TEXT_PLAIN);
		//String response = builder.get(String.class);
		
		try {
			String response = getFile(origen);		
			String[] componente = response.split(";");
			//Obtengo unicamente los que son comercio de la busqueda de pois
			List<Comercio> comercios = this.origenesDeDatos.getPOIs().stream().filter(x-> x instanceof Comercio)
										.map(p -> (Comercio) p).collect(Collectors.toList());
			for(Comercio com : comercios){
				if(com.getNombre().equals(componente[0])){
					com.setPalabrasClaves(Arrays.asList(componente[1].split(" ")));
				}
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getFile(String ruta) throws IOException{		
		File file = new File(ruta);
		return FileUtils.readFileToString(file);
	}
}