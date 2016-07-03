package grupo2.tpAnual.Procesos;


import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.MediaType;
import org.joda.time.LocalDate;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import grupo2.tpAnual.Comercio;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public class ActualizacionLocalesComerciales extends Proceso {
	private Client client;
	private String origen;
	public ActualizacionLocalesComerciales(int hora, LocalDate fecha, AccionEnCasoDeFallo configuracion, OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuracion, origenesDeDatos);
		// TODO Auto-generated constructor stub
		client = Client.create();
	}

	@Override
	public void ejecutarProceso(LogEjecucionProcesos log) {
		//WebResource.Builder builder = this.client.resource(origen).accept(MediaType.TEXT_PLAIN);
		//String response = builder.get(String.class);
		
		try {
			String response = getFile();		
			String[] componente = response.split(";");
			//Obtengo unicamente los que son comercio de la busqueda de pois
			List<Comercio> comercios = this.origenesDeDatos.getPOIs().stream().filter(x-> x instanceof Comercio)
										.map(p -> (Comercio) p).collect(Collectors.toList());
			for(Comercio com : comercios){
				if(com.getNombre() == componente[0]){
					com.setPalabrasClaves(Arrays.asList(componente[1].split(" ")));
				}
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getFile() throws IOException{		
		File file = new File(origen);
		return FileUtils.readFileToString(file);
	}
}