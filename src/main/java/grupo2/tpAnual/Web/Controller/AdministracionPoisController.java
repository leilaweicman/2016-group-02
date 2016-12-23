package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.Mapa;
import grupo2.tpAnual.AccesoriosPois.Direccion;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Web.Models.SingletonMapa;
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
		List<POI> lista = new ArrayList<POI>();
		Mapa mapa = SingletonMapa.get();
		
		lista = mapa.busquedaRealizadaPorElUsuario(nombre);
		Map<String, List<POI>> model = new HashMap<>();
		model.put("pois", lista);
		return new ModelAndView(model, "admin/pois/lista.hbs");
	}
	
	public static ModelAndView editar(Request req, Response res){
		String id = req.params("id");
		
		POI poi = SingletonMapa.get().buscarPorId(Integer.parseInt(id));
		
		return new ModelAndView(poi, "admin/pois/editar.hbs");
	}
	
	public static ModelAndView editarPut(Request req, Response res){
		String ids = req.queryParams("id");

		Integer id = Integer.parseInt(ids);

		Mapa mapa = SingletonMapa.get();
		POI poi = mapa.buscarPorId(id);
		poi.setNombre(req.queryParams("nombre"));
		Direccion dir = poi.getDireccion();
		dir.setCalle(req.queryParams("direccion"));
		poi.setDireccion(dir);
		poi.setUbicacion(Double.parseDouble(req.queryParams("x")), Double.parseDouble(req.queryParams("y")));

		OrigenesDeDatosPOIs origen = (OrigenesDeDatosPOIs) mapa.getOrigenesDeDatos().get(0);
		origen.darDeBajaPOI(id);
		origen.agregarPOI(poi);
		return new ModelAndView(null, "admin/pois/index.hbs");	
	}
	
	public static ModelAndView borrar(Request req, Response res){
		String id = req.params("id");
		OrigenesDeDatosPOIs origen = (OrigenesDeDatosPOIs) SingletonMapa.get().getOrigenesDeDatos().get(0);
		origen.darDeBajaPOI(Integer.parseInt(id));
		return new ModelAndView(null, "admin/pois/index.hbs");
	}
}
