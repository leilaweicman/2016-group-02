package grupo2.tpAnual.Reportes;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Map;
import java.util.stream.Collectors;

import grupo2.tpAnual.DatosDeBusqueda;

public class ReporteCantidadDeResultados {
	Integer suma;

	public List<Integer> obtenerReportePorTerminal (List<DatosDeBusqueda> registroBusqueda, String nombreTerminal){
		List<Integer> reporte = new ArrayList<Integer>();
	
		reporte.addAll(registroBusqueda.stream().filter(registro-> registro.getNombre()==nombreTerminal)
				.map(registro->registro.getTotalDeResultados()).collect(Collectors.toList()));		
	
		return reporte;
	}
	
	//Esto es un esqueleto, no esta hecho bien	
	public Map<String, Integer> obtenerReportePorUsuario (List<DatosDeBusqueda> registroBusqueda){
	
		Map<String, Integer> reporte = new HashMap<String, Integer>();				
	
		for(DatosDeBusqueda registro : registroBusqueda){
			Integer cantidadResultados;
			cantidadResultados= this.obtenerReportePorTerminal(registroBusqueda, registro.getNombre()).stream()
					.reduce(0, (a, b) -> a+b );
			
			reporte.put(registro.getNombre(), cantidadResultados);
							
		}
		return reporte;
	}
	
	
}
