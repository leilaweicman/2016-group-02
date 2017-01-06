package grupo2.tpAnual.Web.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMongoDB;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;
import grupo2.tpAnual.Repositorios.DatosDeBusquedaRepository;
import grupo2.tpAnual.Web.Models.SingletonDatosBusquedaRepository;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HistoricoConsultasController {
	
	public static ModelAndView get(Request req, Response res) {
		Map<String, List<DatosDeBusqueda>> model = new HashMap<>();
		List<DatosDeBusqueda> datosDeBusqueda = DatosBusquedaRepositoryMongoDB.instancia.consultarDatos();
		
		model.put("datosDebusqueda", datosDeBusqueda);
		
		return new ModelAndView(model, "historicoConsultas/historico.hbs");
		
	}
	
	public static ModelAndView listar(Request req, Response res) {
		
		/* TODO Recibir los parametros 
		 * Ahora solo esta buscando por el nombre 
		 * Fijarnos si hay forma de hacer interseccion entre listas (? o crear un metodo que filtre por las 3 cosas
		 */
		
		Map<String, List<DatosDeBusqueda>> model = new HashMap<>();
				
		String terminal= req.queryParams("terminal");
		String cantidad= req.queryParams("cantidad");

		DatosDeBusquedaRepository repository = SingletonDatosBusquedaRepository.get();
		
		//List<DatosDeBusqueda> datosDeBusqueda = DatosBusquedaRepositoryMongoDB.instancia.obtenerPorNombre(terminal);
		List<DatosDeBusqueda> datosDeBusqueda = repository.obtenerPorNombre(terminal);
		
		model.put("datosDebusqueda", datosDeBusqueda);
		
		return new ModelAndView(model, "historicoConsultas/listar.hbs");
		
		//TODO no aparece el nombre ni la fecha en la lista :(
		
	}
	
	
	/*public static ModelAndView listarPorTerminal(Request req, Response res) {
		Map<String, List<DatosDeBusqueda>> model = new HashMap<>();
		
		String terminal= req.queryParams("terminal");
		
		List<DatosDeBusqueda> datosDeBusqueda = DatosBusquedaRepositoryMongoDB.instancia.obtenerPorNombre(terminal);
		
		model.put("datosDebusqueda", datosDeBusqueda);
		
		return new ModelAndView(model, "historicoConsultas/listarPorTerminal.hbs");
		
	}
	
	public static ModelAndView listarPorCantidad(Request req, Response res) {
		Map<String, List<DatosDeBusqueda>> model = new HashMap<>();
		
		//int cantidad= Integer.parseInt(req.params("cantidad"));
		
		String cant = req.queryParams("cantidad");
		
		double cantidad =  Double.parseDouble(cant);
		
		List<DatosDeBusqueda> datosDeBusqueda = DatosBusquedaRepositoryMongoDB.instancia.cantidadDePois(cantidad);
		
		model.put("datosDebusqueda", datosDeBusqueda);
		
		return new ModelAndView(model, "historicoConsultas/listarPorCantidad.hbs");
		
	}
	
	public static ModelAndView listarPorFecha(Request req, Response res) {
		Map<String, List<DatosDeBusqueda>> model = new HashMap<>();
		
		String fecha= req.queryParams("fecha");
		
		List<DatosDeBusqueda> datosDeBusqueda = DatosBusquedaRepositoryMongoDB.instancia.(fecha);
		
		model.put("datosDebusqueda", datosDeBusqueda);
		
		return new ModelAndView(model, "historicoConsultas/listarPorFecha.hbs");
		
	}*/
}
