package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import grupo2.tpAnual.CGP;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public class BajaDePois extends Proceso {

	RestServiceBajaPois servicioRestBajaPois;
	public ObjectMapper mapper = new ObjectMapper();

	public BajaDePois(int hora, LocalDate fecha, AccionEnCasoDeFallo configuracion,
			OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuracion, origenesDeDatos);
		this.servicioRestBajaPois = new RestServiceBajaPois();
	}

	public List<Integer> getNumerosIdentificadoresDePois() throws Exception {
		// obtengo los datos del servicio rest en formato json
		String json = this.servicioRestBajaPois.getPOIs();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // ignora
																					// los
																					// atributos
																					// que
																					// no
																					// uso
		List<CGP> pois = mapper.readValue(json,
				mapper.getTypeFactory().constructCollectionType(ArrayList.class, CGP.class));

		// agrego el numero de identificador del poi a la lista
		List<Integer> numerosID = new ArrayList<>();

		pois.forEach(poi -> numerosID.add(poi.getNumeroVerificador()));

		return numerosID;
	}

	@Override
	public void ejecutarProceso(LogEjecucionProcesos log) {
		int cantidadElementosAfectados = 0;
		List<Integer> poisABorrar;
		try {
			poisABorrar = this.getNumerosIdentificadoresDePois();
			if (poisABorrar != null) {
				for (Integer pid : poisABorrar) {
					this.origenesDeDatos.darDeBajaPOI(pid);
					cantidadElementosAfectados = cantidadElementosAfectados + 1;
				}
				log.loguearProceso(new DatosParaLogEjecucionProcesos(this.getFechaEjecucion(), this.getHoraEjecucion(),
						true, cantidadElementosAfectados));

			}
		} catch (Exception e) {
			this.configuracionFallo.ejecutarConfiguracionPorFallo();
			log.loguearProceso(new DatosParaLogEjecucionProcesos(this.getFechaEjecucion(), this.getHoraEjecucion(),
					false, cantidadElementosAfectados));
		}

	}

}
