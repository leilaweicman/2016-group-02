package grupo2.tpAnual.Web.Controller;

import java.time.LocalDate;
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
		
		Map<String, List<DatosDeBusqueda>> model = new HashMap<>();
				
		String terminal= req.queryParams("terminal");
		String cantidad= req.queryParams("cantidad");
		int cant = 15;
		if (! cantidad.isEmpty()){
			 cant = Integer.parseInt (cantidad);
		} 		
		
		DatosDeBusquedaRepository repository = SingletonDatosBusquedaRepository.get();
		
		//List<DatosDeBusqueda> datosDeBusqueda = DatosBusquedaRepositoryMongoDB.instancia.obtenerPorNombre(terminal);

		List<DatosDeBusqueda> datosDeBusqueda = repository.filtrar(terminal, cant, LocalDate.now(), LocalDate.now().minusDays(1));
		
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