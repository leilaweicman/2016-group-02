package grupo2.tpAnual.Web.Controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminController {

	public static ModelAndView get(Request req, Response res){
		return new ModelAndView(null, "inicioAdmin.hbs");
	}
}
