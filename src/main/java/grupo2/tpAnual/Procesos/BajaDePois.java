package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

import grupo2.tpAnual.POI;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
public class BajaDePois implements AccionesDeProcesos {

	OrigenesDeDatosPOIs origenesDeDatos; 
	StubServicioRESTBajaPOIS servicioRestBajaPois;
	
	public BajaDePois(){
		this.servicioRestBajaPois = new StubServicioRESTBajaPOIS();
		this.origenesDeDatos = new OrigenesDeDatosPOIs();
	}	
	public BajaDePois(OrigenesDeDatosPOIs origenes){
		this.origenesDeDatos = origenes;
		
	}

	
	public List<Integer> getNumerosIdentificadoresDePois(){
		List<Integer> numerosVerificadoresPois = new ArrayList<>();
		return numerosVerificadoresPois;
		
	}
	
	@Override
	public boolean ejecutar() {
		int cantidadElementosAfectados = 0;
		List<Integer> poisABorrar = this.servicioRestBajaPois.getPOIsFueraDeUso().getNumerosIdentificadoresDePois();
		if(poisABorrar!=null){
			for(Integer pid: poisABorrar){
				this.origenesDeDatos.darDeBajaPOI(pid);
				cantidadElementosAfectados = cantidadElementosAfectados + 1;  
			}
		return true;
		}	
	return false;
	}

}
