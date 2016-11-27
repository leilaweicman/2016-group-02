package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import grupo2.tpAnual.Mapa;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Web.Models.SingletonMapa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController implements WithGlobalEntityManager, TransactionalOps {

	public static ModelAndView get(Request req, Response res) {
		return new ModelAndView(null, "busquedaTerminal.hbs");
	}

	public static ModelAndView show(Request req, Response res) {
		Map<String, List<POI>> model = new HashMap<>();
		String palabraBuscada = req.queryParams("palabraBuscada");
		Mapa mapa = SingletonMapa.getMemory();
		List<POI> resultadoBusqueda = new ArrayList<>();
		resultadoBusqueda = mapa.busquedaRealizadaPorElUsuario(palabraBuscada);
		model.put("pois",resultadoBusqueda);
		return new ModelAndView(model, "resultadoBusquedaTerminal.hbs");
	}
}
