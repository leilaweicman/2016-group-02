package grupo2.tpAnual.Web;

import grupo2.tpAnual.Web.Controller.AdminController;
import grupo2.tpAnual.Web.Controller.AdministracionPoisController;
import grupo2.tpAnual.Web.Controller.AdministracionTerminalController;
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
		Spark.get("/terminal/:palabraBuscada",TerminalController::show, engine);
		Spark.get("/admin", AdminController::get, engine);
		Spark.get("/admin/historico", HistoricoConsultasController::get, engine);
		Spark.get("/admin/listarPorTerminal", HistoricoConsultasController::listarPorTerminal, engine);
		Spark.get("/admin/listarPorCantidad", HistoricoConsultasController::listarPorCantidad, engine);
		/* TODO Descomentar cuando se hagan los metodos en el controller
		 * Spark.get("/admin/historicoConsultas", HistoricoConsultasController::listarPorFecha, engine);
		*/
		Spark.get("/admin/pois", AdministracionPoisController::get, engine);
		Spark.get("/admin/pois/lista", AdministracionPoisController::lista, engine);
		Spark.get("/admin/pois/editar/:id", AdministracionPoisController::editar, engine);
		
		Spark.get("/admin/terminal", AdministracionTerminalController::get, engine);
		//Spark.get("/admin", AdministracionTerminalController::listar, engine);


		Spark.get("/admin/pois/editarPut", AdministracionPoisController::editarPut, engine);

		//TODO ver como hacer para que funcione
		//Spark.put("/admin/pois/editar", AdministracionPoisController::editarPut);
		//Spark.delete("/admin/pois/borrar/:id", AdministracionPoisController::borrar, engine);

	}
}