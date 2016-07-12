package grupo2.tpAnual.Procesos;

import java.util.List;

import org.joda.time.LocalDate;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;


public class AsignacionDeAccionesParaUsuarios extends Proceso {

	public AsignacionDeAccionesParaUsuarios(int hora, LocalDate fecha, List<AccionEnCasoDeFallo> configuraciones, OrigenesDeDatosPOIs origenesDeDatos) {
		super(hora, fecha, configuraciones, origenesDeDatos);
		
	}

	@Override
	public void ejecutarProceso() {
		// TODO Auto-generated method stub
	}


	
}
