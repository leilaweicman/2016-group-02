package grupo2.tpAnual.Web.Models;

public class Mapa {
	private static grupo2.tpAnual.Mapa instance;

	public grupo2.tpAnual.Mapa get() {
		if (instance == null) {
			//TODO: Hay que pasarle los parametros correspondientes a mapa
			instance = new grupo2.tpAnual.Mapa(null, null);
		}
		return instance;
	}
}
