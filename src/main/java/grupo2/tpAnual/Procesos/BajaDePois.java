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

	public List<Integer> getNumerosIdentificadoresDePois() {
		String json = this.servicioRestBajaPois.getPOIs();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<CGP> pois;
		List<Integer> numerosID = new ArrayList<>();
		try {
			pois = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, CGP.class));
			pois.forEach(poi -> numerosID.add(poi.getId()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numerosID;
	}

	@Override
	public void ejecutar() {
		List<Integer> poisABorrar;
		poisABorrar = this.getNumerosIdentificadoresDePois();
		if (poisABorrar != null) {
			for (Integer pid : poisABorrar) {
				this.origenesDeDatos.darDeBajaPOI(pid);
				cantidadElementosAfectados++;
			}
		}
	}
}
