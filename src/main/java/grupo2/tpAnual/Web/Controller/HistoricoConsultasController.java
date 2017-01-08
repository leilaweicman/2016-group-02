package grupo2.tpAnual.Web.Controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
		String desde = req.queryParams("desde");
		String hasta = req.queryParams("hasta");
		
		String fechaDesde = "";
		String fechaHasta = "";
		
		try {
			fechaHasta = java.net.URLDecoder.decode(hasta, "UTF-8");
			fechaDesde = java.net.URLDecoder.decode(desde, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fechaDesde = "";
			fechaHasta= "";
		}
		
		String[] parts = fechaDesde.split("/");
		String mesDesde = parts[0];
		String diaDesde = parts[1];
		String anioDesde = parts[2];
		
		String[] partsHasta = fechaHasta.split("/");
		String mesHasta = partsHasta[0];
		String diaHasta = partsHasta[1];
		String anioHasta = partsHasta[2];
		
		DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
		        FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

		LocalDate dateDesde = LocalDate.parse(diaDesde + "." + mesDesde + "." + anioDesde, germanFormatter);
		LocalDate dateHasta = LocalDate.parse(diaHasta + "." + mesHasta + "." + anioHasta, germanFormatter);
    
		
		//System.out.print(dateHasta);
		    
		int cant = 0;
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
