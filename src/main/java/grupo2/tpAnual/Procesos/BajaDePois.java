package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;

import grupo2.tpAnual.FromJsonToMap;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsMemory;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public class BajaDePois extends Proceso {
	RestServiceBajaPois servicioRestBajaPois;
	public ObjectMapper mapper = new ObjectMapper();

	public BajaDePois(int hora, LocalDate fecha, List<AccionEnCasoDeFallo> configuraciones,
			OrigenesDeDatosPOIsMemory origenesDeDatos) {
		super(hora, fecha, configuraciones, origenesDeDatos);
		this.servicioRestBajaPois = new RestServiceBajaPois();
	}

	public List<Integer> getNumerosIdentificadoresDePois() {
		String json = this.servicioRestBajaPois.getPOIs();
		
		List<Integer> numerosID = new ArrayList<>();
		try {
			numerosID = FromJsonToMap.transformarAMap(json).stream().map((poiMap) -> transformarID(poiMap)).collect(Collectors.toList());

		} catch (Exception e) {
			this.configuracionesFallo.forEach(configuracion -> configuracion.ejecutarConfiguracionPorFallo(this));
			this.setEstadoProceso(false);
		}
		return numerosID;
	}
	
	public int transformarID(Map<String, Object> map){
		int id = (int) map.getOrDefault("id", -1);
		return id;

	}
	@Override
	public void ejecutar() {
		List<Integer> poisABorrar;
		poisABorrar = this.getNumerosIdentificadoresDePois();
		for (Integer pid : poisABorrar) {
			this.origenesDeDatos.darDeBajaPOI(pid);
			cantidadElementosAfectados++;
		}
	}
}
