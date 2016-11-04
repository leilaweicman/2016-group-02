package grupo2.tpAnual.Web.Controller;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class InicioController {

	public static ModelAndView get(Request req, Response res){
		String userType = req.queryParams("userType");
		return new ModelAndView(userType, "inicio.hbs");
	}

}
