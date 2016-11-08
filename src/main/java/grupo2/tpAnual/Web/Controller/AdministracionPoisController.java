package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import grupo2.tpAnual.Web.Models.Mapa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionPoisController {
	public static ModelAndView get(Request req, Response res) {
		grupo2.tpAnual.Mapa mapa = Mapa.get();
		mapa.busquedaRealizadaPorElUsuario("");
		return new ModelAndView(new ArrayList<>(), "administracionPois.hbs");
	}
}
