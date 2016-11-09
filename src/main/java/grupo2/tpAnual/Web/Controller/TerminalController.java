package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.Mapa;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsMemory;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Pois.Parada;
import grupo2.tpAnual.Web.Models.SingletonMapa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController {
	
	public static ModelAndView get(Request req, Response res) {
		return new ModelAndView(null, "busquedaTerminal.hbs");
}
	
	public static ModelAndView show(Request req, Response res) {
		String palabraBuscada = req.queryParams("palabraBuscada");
		Mapa mapa = SingletonMapa.get();
		List<POI> resultadoBusqueda = new ArrayList<>();
		resultadoBusqueda = mapa.busquedaRealizadaPorElUsuario(palabraBuscada);
		return new ModelAndView(resultadoBusqueda, "resultadoBusquedaTerminal.hbs");
}

}
