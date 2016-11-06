package grupo2.tpAnual.Web.Controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HistoricoConsultasController {
	public static ModelAndView get(Request req, Response res) {
		return new ModelAndView(null, "historicoConsultas.hbs");
	}
}
