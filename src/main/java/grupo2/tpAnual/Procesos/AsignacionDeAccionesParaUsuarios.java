package grupo2.tpAnual.Procesos;

import org.joda.time.LocalDate;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public class AsignacionDeAccionesParaUsuarios extends Proceso {

	public AsignacionDeAccionesParaUsuarios(int hora, LocalDate fecha, AccionEnCasoDeFallo configuracion, OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuracion, origenesDeDatos);
		
	}

	@Override
	public void ejecutarProceso(LogEjecucionProcesos log) {
		// TODO Auto-generated method stub
	}


	
}
