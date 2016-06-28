package grupo2.tpAnual.Procesos;


import org.joda.time.LocalDate;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public class ActualizacionLocalesComerciales extends Proceso {
	
	
	public ActualizacionLocalesComerciales(int hora, LocalDate fecha, AccionEnCasoDeFallo configuracion, OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuracion, origenesDeDatos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutarProceso(LogEjecucionProcesos log) {
		// TODO Auto-generated method stub
	}

}
