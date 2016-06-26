package grupo2.tpAnual.Procesos;

import java.util.List;

import grupo2.tpAnual.Mapa;

public class ActualizacionLocalesComerciales implements AccionesDeProcesos {

	private List<String> textoPlano;
	private Mapa mapa;

	public ActualizacionLocalesComerciales(List<String> textoPlano, Mapa mapa) {
		this.textoPlano = textoPlano;
		this.mapa = mapa;

	}

	public void aplanarOrigenesDeDatos(){

	}
	@Override
	public void ejecutar() {
		}

}
