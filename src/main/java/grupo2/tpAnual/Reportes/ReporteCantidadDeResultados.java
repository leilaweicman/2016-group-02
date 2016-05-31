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
			
		/*public Map<String, Integer> obtenerReportePorUsuario (List<DatosDeBusqueda> registroBusqueda){
		Map<String, Integer> reporte = new HashMap<String, Integer>();
		
		for(DatosDeBusqueda registro : registroBusqueda){
			Integer nombre = reporte.get(registro.getFecha().toString()) ;
			if(cantidadXFecha != null){
				reporte.put(registro.getFecha().toString(), ++cantidadXFecha);
			}else{
				reporte.put(registro.getFecha().toString(), 1);
			}
		}
		return reporte;
	}*/		
	
}
