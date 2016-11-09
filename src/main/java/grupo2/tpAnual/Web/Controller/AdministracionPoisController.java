package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.AccesoriosPois.Direccion;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Web.Models.Mapa;
import grupo2.tpAnual.Web.Models.adminPois;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionPoisController {
	private static adminPois admin;
	public static ModelAndView get(Request req, Response res) {
		//grupo2.tpAnual.Mapa mapa = Mapa.get();
		//mapa.busquedaRealizadaPorElUsuario("");
		admin = new adminPois();		
		return new ModelAndView(admin, "admin/pois/index.hbs");
	}
	
	public static ModelAndView lista(Request req, Response res){
		String nombre = req.queryParams("nombre");
		String tipo = req.queryParams("tipo");
		
		//Hardcode
		Banco prueba = new Banco("Santander", Point.and(-34.664837, -58.385674));
		prueba.setId(4);
		List<POI> lista = new ArrayList();
		lista.add(prueba);
		Map<String, List<POI>> model = new HashMap<>();
		model.put("pois", lista);
		return new ModelAndView(model, "admin/pois/lista.hbs");
	}
	
	public static ModelAndView editar(Request req, Response res){
		String id = req.params("id");
		
		//Hardcode
		Banco prueba = new Banco("Santander", Point.and(-34.664837, -58.385674));
		prueba.setDireccion(new Direccion("calle", "zona"));
		prueba.setId(4);
		return new ModelAndView(prueba, "admin/pois/editar.hbs");
	}
	
	public static void editarPut(Request req, Response res){
		//TODO guardar el poi editado
		res.redirect("/admin/pois");
	}
	
	public static void borrar(Request req, Response res){
		String id = req.queryParams("id");
		res.redirect("/admin/pois");
	}
}
