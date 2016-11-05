package grupo2.tpAnual.Web;

import grupo2.tpAnual.Web.Controller.AdministracionPoisController;
import grupo2.tpAnual.Web.Controller.BusquedaTerminalController;
import grupo2.tpAnual.Web.Controller.InicioController;
import grupo2.tpAnual.Web.Controller.ResultadoBusquedaController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();
		Spark.staticFiles.location("/public");
//aca van las paginas que vamos creando. son en hbs El servidor levanta, y le dice a Router que ejecute su config. Aca agregamos el controller a cada ruta. Ver en cada controller que se hace 
		Spark.get("/", InicioController::get,engine);
		Spark.get("/terminal", BusquedaTerminalController::get, engine);
		Spark.get("/administrador", BusquedaTerminalController::get, engine);
		Spark.get("/resultadoBusqueda",ResultadoBusquedaController::get, engine);
		Spark.get("/administrarPois", AdministracionPoisController::get, engine);
}
}
