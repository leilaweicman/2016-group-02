package grupo2.tpAnual.Reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import grupo2.tpAnual.DatosDeBusqueda;

public class ReporteCantidadDeResultados {
	

	public List<Integer> obtenerReportePorTerminal (List<DatosDeBusqueda> registroBusqueda, String nombreTerminal){
		List<Integer> reporte = new ArrayList<Integer>();
	
		reporte.addAll(registroBusqueda.stream().filter(registro-> registro.getNombre()==nombreTerminal).map(registro->registro.getTotalDeResultados()).collect(Collectors.toList()));
		
	
		return reporte;
	}
		//Esto es un esqueleto, no esta hecho bien	
		public Map<String, Integer> obtenerReportePorUsuario (List<DatosDeBusqueda> registroBusqueda){
		
		Map<String, Integer> reporte = new HashMap<String, Integer>();
		Integer cantidadResultados;

		for(DatosDeBusqueda registro : registroBusqueda){
			
		reporte.put(registro.getNombre(), cantidadResultados);
						
		return reporte;
	}	
	
}
