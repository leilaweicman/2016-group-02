package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.Mapa;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsMemory;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Pois.Parada;
import grupo2.tpAnual.Repositorios.SQLUserRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;
import grupo2.tpAnual.Web.Models.SingletonMapa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController {

	public static ModelAndView get(Request req, Response res) {
		return new ModelAndView(null, "busquedaTerminal.hbs");
	}

	public static ModelAndView show(Request req, Response res) {
		Map<String, List<POI>> model = new HashMap<>();
		String palabraBuscada = req.params("palabraBuscada");
		Mapa mapa = SingletonMapa.get();
		List<POI> resultadoBusqueda = new ArrayList<>();
		//resultadoBusqueda = mapa.busquedaRealizadaPorElUsuario(palabraBuscada);
		resultadoBusqueda = Arrays.asList(new Banco("Santander", Point.and(123.4, 223.4)), new Comercio("Zara",Point.and(87.777, 55.435),null), new CGP("Comuna 9", Point.and(255.4, 333.2)));
		model.put("pois",resultadoBusqueda);
		return new ModelAndView(model, "resultadoBusquedaTerminal.hbs");
	}

}
