package grupo2.tpAnual.Web.Controller;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ResultadoBusquedaController {
	
	public static ModelAndView get(Request req, Response res) {

		return new ModelAndView(null, "resultadoBusquedaTerminal.hbs");
}

}
