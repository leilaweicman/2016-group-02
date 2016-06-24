package grupo2.tpAnual.Reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import grupo2.tpAnual.DatosDeBusqueda;
import grupo2.tpAnual.RegistrarDatosBusqueda;

public class PorTerminalOUsuario implements Reporte {
	
	public List<Integer> obtenerReportePorTerminal (String nombreTerminal){
		List<Integer> reporte = new ArrayList<Integer>();
		Reporte.registroBusqueda.addAll(Reporte.register.consultarDatos());
		reporte.addAll(registroBusqueda.stream().filter(registro-> registro.getNombre().equals(nombreTerminal))
				.map(registro->registro.getTotalDeResultados()).collect(Collectors.toList()));		
	
		return reporte;
	}

		public Map<String, Integer> obtenerReportePorUsuario (){
			
			Map<String, Integer> reporte = new HashMap<String, Integer>();				
			Reporte.registroBusqueda.addAll(Reporte.register.consultarDatos());
			for(DatosDeBusqueda registro : registroBusqueda){
				Integer cantidadResultados;
				cantidadResultados= this.obtenerReportePorTerminal(registro.getNombre()).stream()
						.reduce(0, (a, b) -> a+b );
				
				reporte.put(registro.getNombre(), cantidadResultados);
								
			}
			return reporte;
		}
}
