package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import ServiciosExternos.CentroDTO;
import grupo2.tpAnual.Mapa;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Pois.Parada;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BusquedaTerminalController {
	public static ModelAndView get(Request req, Response res) {
		Mapa mapa;
		String palabraBuscada = req.queryParams("buscando");
		List<POI> resultadoBusqueda = new ArrayList<>();
	//	resultadoBusqueda = mapa.busquedaRealizadaPorElUsuario("buscando");
		resultadoBusqueda = Arrays.asList(new Banco("Santander Rio",Point.and(-34.554, 98.255)), new CGP("Comuna 3", Point.and(54.327, 98.765)), new Parada("Los Amigos S.A",Point.and(-85.254, -56.222),"114"));
		return new ModelAndView(resultadoBusqueda, "busquedaTerminal.hbs");
}

}
