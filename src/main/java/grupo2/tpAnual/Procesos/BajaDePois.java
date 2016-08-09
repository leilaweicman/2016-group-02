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

	public BajaDePois(int hora, LocalDate fecha, List<AccionEnCasoDeFallo> configuraciones,
			OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuraciones, origenesDeDatos);
		this.servicioRestBajaPois = new RestServiceBajaPois();
	}

	public List<Integer> getNumerosIdentificadoresDePois() throws Exception {
		// obtengo los datos del servicio rest en formato json
		String json = this.servicioRestBajaPois.getPOIs();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<CGP> pois = mapper.readValue(json,
				mapper.getTypeFactory().constructCollectionType(ArrayList.class, CGP.class));

		// agrego el numero de identificador del poi a la lista
		List<Integer> numerosID = new ArrayList<>();
		pois.forEach(poi -> numerosID.add(poi.getId()));
		return numerosID;
	}

	@Override
	public void ejecutarProceso() {
		int cantidadElementosAfectados = 0;
		List<Integer> poisABorrar;
		try {
			poisABorrar = this.getNumerosIdentificadoresDePois();
			if (poisABorrar != null) {
				for (Integer pid : poisABorrar) {
					this.origenesDeDatos.darDeBajaPOI(pid);
					cantidadElementosAfectados = cantidadElementosAfectados + 1;
				}
				this.setEstadoProceso(true);
			}
		} catch (Exception e) {
			this.configuracionesFallo.forEach(configuracion -> configuracion.ejecutarConfiguracionPorFallo(this));
			this.setEstadoProceso(false);
		}
		this.log.loguearProceso(new DatosParaLogEjecucionProcesos(this.getFechaEjecucion(), this.getHoraEjecucion(),
				this.ejecucionExitosa, cantidadElementosAfectados));
	}
}
