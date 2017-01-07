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
		Spark.get("/", InicioController::get,engine);
		Spark.get("/home", InicioController::home, engine); //Se inicia con usuario Administrador o Terminal (son los unicos creados) 
		Spark.get("/terminal", TerminalController::inicio, engine);
		Spark.get("/terminal/busqueda", TerminalController::get, engine);
		Spark.get("/terminal/busqueda/",TerminalController::show, engine);
		Spark.get("/admin", AdminController::get, engine);
		Spark.get("/admin/historico", HistoricoConsultasController::get, engine);
		Spark.get("/admin/listar", HistoricoConsultasController::listar, engine);
		
		Spark.get("/admin/pois", AdministracionPoisController::get, engine);
		Spark.get("/admin/pois/lista", AdministracionPoisController::lista, engine);
		Spark.get("/admin/pois/editar/:id", AdministracionPoisController::editar, engine);
		Spark.get("/admin/pois/borrar/:id", AdministracionPoisController::borrar, engine);
		
		Spark.get("/admin/terminal", AdministracionTerminalController::get, engine);
		Spark.get("/admin/terminal/editar/:id", AdministracionTerminalController::editar, engine);
		Spark.post("/admin/terminal/guardar", AdministracionTerminalController::guardar, engine);
		Spark.get("/admin/terminal/eliminar/:id", AdministracionTerminalController::eliminar, engine);
		Spark.get("/admin/terminal/info/:id", AdministracionTerminalController::info, engine);
		Spark.get("/admin/terminal/agregar", AdministracionTerminalController::crear, engine);
		Spark.get("/admin/terminal/filtrar", AdministracionTerminalController::filtrar, engine);

		Spark.get("/admin/pois/editarPut", AdministracionPoisController::editarPut, engine);

	}
}