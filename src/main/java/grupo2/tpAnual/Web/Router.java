package grupo2.tpAnual.Web;

import grupo2.tpAnual.Web.Controller.AdminController;
import grupo2.tpAnual.Web.Controller.AdministracionPoisController;
import grupo2.tpAnual.Web.Controller.HistoricoConsultasController;
import grupo2.tpAnual.Web.Controller.InicioController;
import grupo2.tpAnual.Web.Controller.TerminalController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();
		Spark.staticFiles.location("/public");
		//aca van las paginas que vamos creando. son en hbs El servidor levanta, y le dice a Router que ejecute su config. Aca agregamos el controller a cada ruta. Ver en cada controller que se hace 
		Spark.get("/", InicioController::get,engine);
		Spark.get("/terminal", TerminalController::get, engine);
		Spark.get("/admin", AdminController::get, engine);
		Spark.get("/terminal/resultadosBusqueda",TerminalController::get, engine);
		Spark.get("/admin/pois", AdministracionPoisController::get, engine);
		Spark.get("/admin/historicoConsultas", HistoricoConsultasController::get, engine);
	}
}