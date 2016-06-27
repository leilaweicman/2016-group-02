package grupo2.tpAnual.Procesos;

import java.util.List;

import org.eclipse.xtext.xbase.lib.Pair;

import grupo2.tpAnual.Mapa;
import grupo2.tpAnual.POI;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;

public class ActualizacionLocalesComerciales implements AccionesDeProcesos {
	private List<Pair<String,List<String>>> textoPlano; //Lista de tuplas (nombreFantasia, listaPalabrasClave)
	private OrigenesDeDatos origenesDeDatos; 

	public ActualizacionLocalesComerciales(List<Pair<String,List<String>>> textoPlano, OrigenesDeDatos origenesDeDatos) {
		this.textoPlano = textoPlano;
		this.origenesDeDatos = origenesDeDatos;

	}

	public void aplanarOrigenesDeDatos(){

	}
	
	@Override
	public boolean ejecutar() {
				return true;
		}

}
