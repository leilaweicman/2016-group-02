package grupo2.tpAnual.Web.Controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.ComunaRepository;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.ObserversRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;
import grupo2.tpAnual.Web.Models.SingletonComunaRepository;
import grupo2.tpAnual.Web.Models.SingletonObserverRepository;
import grupo2.tpAnual.Web.Models.SingletonUserRepository;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionTerminalController {
	
	public static ModelAndView get(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		
		UserRepository repo = SingletonUserRepository.get();
		List<Usuario> terminales = repo.getUsauriosTerminal();
		
		ComunaRepository repoComunas = SingletonComunaRepository.get();
		List<Comuna> comunas = repoComunas.getComunas();
		
		model.put("terminales", terminales);
		model.put("comunas", comunas);
		return new ModelAndView(model, "admin/terminales/administracionTerminal.hbs");
		
	}
	
	public static ModelAndView filtrar(Request req, Response res){
		Map<String, Object> model = new HashMap<>();
		/*
		 * tengo que agarrar el numero de comuna del select de alguna manera
		 * de ultima lo agarro como los checks
		 */
		int numeroComuna =1;
		UserRepository repoUsuarios = SingletonUserRepository.get();
		List<Usuario> terminales = repoUsuarios.getUsauriosTerminal();
		
		terminales.stream().filter(terminal->terminal.getComuna().getNumeroComuna()==numeroComuna);

		model.put("terminales", terminales);
		return new ModelAndView(model, "admin/terminales/administracionTerminal.hbs");
	}
	
	
	public static ModelAndView crear(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
					
		ObserversRepository repo = SingletonObserverRepository.get();
		List<ObserverBusqueda> accionesDisponibles = repo.getObservers();
		
		model.put("accionesDisponibles", accionesDisponibles);
		return new ModelAndView(model, "admin/terminales/editarTerminal.hbs");
		
	}
	
	public static ModelAndView info(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		
		long id = Long.parseLong(req.params("id"));
		
		UserRepository usuarios = SingletonUserRepository.get();
		
		Usuario user = usuarios.getUsuarioById(id);
		
		ObserversRepository repo = SingletonObserverRepository.get();
		List<ObserverBusqueda> accionesUsuario = user.getAccionesBusqueda();
		
		model.put("user", user);
		model.put("accionesUsuario", accionesUsuario);
		return new ModelAndView(model, "admin/terminales/infoTerminal.hbs");
		
	}
	
	public static ModelAndView eliminar(Request req, Response res){
		long id = Long.parseLong(req.params("id"));
		
		UserRepository repoUsuarios = SingletonUserRepository.get();
		Usuario usuario = repoUsuarios.getUsuarioById(id);
		repoUsuarios.deleteUsuario(usuario);
		
		res.redirect("/admin/terminal");
		return null;
	}
	
	public static ModelAndView editar(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		
		long id = Long.parseLong(req.params("id"));
		
		UserRepository usuarios = SingletonUserRepository.get();
		
		Usuario user = usuarios.getUsuarioById(id);
		
		ObserversRepository repo = SingletonObserverRepository.get();
		List<ObserverBusqueda> accionesDisponibles = new ArrayList<ObserverBusqueda>();//repo.getObservers();

		List<ObserverBusqueda> acciones = repo.getObservers();
		List<ObserverBusqueda> accionesUsuario = user.getAccionesBusqueda();
		acciones.stream().forEach(action->meterEnPrueba(action, accionesDisponibles, user));
		//comento la sig linea porque el removeAll lo hace al repo y no tiene sentido que haga eso
		//accionesDisponibles.removeAll(accionesUsuario);
		
		model.put("user", user);
		model.put("accionesDisponibles", accionesDisponibles);
		model.put("accionesUsuario", accionesUsuario);
		return new ModelAndView(model, "admin/terminales/editarTerminal.hbs");
		
	}


	 public static void meterEnPrueba(ObserverBusqueda obs, List<ObserverBusqueda>prueba, Usuario user){
		 //aprox que esta funcion es un filter pero con el filter no funcionaba
		 if(!user.tieneObserver(obs)){
			 prueba.add(obs);
		 }
	 }
	public static ModelAndView guardar(Request req, Response res) {
		
		UserRepository usuarios = SingletonUserRepository.get();
		Usuario user;

		if(req.queryParams("id")!=""){
			long id = Long.parseLong(req.queryParams("id"));
			user = usuarios.getUsuarioById(id);
		} else {
			user = new Usuario();
		}
		String nombre = req.queryParams("nombre");
		int numeroComuna = Integer.parseInt(req.queryParams("comuna"));
		
		ComunaRepository comunas = SingletonComunaRepository.get();
		Comuna comuna = comunas.getComunaByNumero(numeroComuna);
		
		ObserversRepository repoAcciones = SingletonObserverRepository.get();
		List<ObserverBusqueda> acciones = repoAcciones.getObservers();
		System.out.println("size "+String.valueOf(acciones.size()));
		acciones.stream().forEach(accion -> System.out.println("flags: "+String.valueOf((req.queryParams(String.valueOf(accion.getId()))))));	
		acciones.stream().forEach(accion-> editarAcciones(accion, user, req));
		
		user.setNombre(nombre);
		user.setComuna(comuna);
		
		usuarios.updateUsuario(user);
	
		res.redirect("/admin/terminal");
		return null;
	}

	public static void editarAcciones(ObserverBusqueda observer, Usuario user, Request req){
		String flag = String.valueOf(req.queryParams(String.valueOf(observer.getId()))); 

		if(user.tieneObserver(observer)&& !flag.equals("on")){
			user.quitarAccionBusqueda(observer);
			System.out.println(flag);
		} else if(!user.tieneObserver(observer) && flag.equals("on")){
			user.setAccionBusqueda(observer);
			System.out.println(flag);
		}
	}
	
	
	/* PARA NO OLVIDARME
	 * cada href de cada row deberia tener una forma de agarrar el id de la 
	 * terminal para poder editarla o eliminarla
	 * 
	 * no me agarra mi css
	 * 
	 * que onda con los repos, me tira null pointer exception, por eso harcodee
	 * 
	 * para editar, como es otra vista deberia hacer otro controller o desde este 
	 * mismo puedo meter un metodo editar que muestre otro .hbs?
	 * 
	 * ver por qeu si pongo href="/admin/terminal" tira 404 not found cuando en el router esta 
	 * puesto para que redireccione, con ? al final si funciona
	 * 
	 * tiene qeu estar hecho lo del repo para poder testearlo bien porque solo en la bd se crea
	 * el id para poder editarlo 
	 * 
	 * en base a la pregunta anterior, entonces, esto no funcionaria si no hay bd?
	 * 
	 * ver si en el model.put solo puedo mandar listas o si puedo mandar una sola cosa
	 * creo que puedo mandar una sola cosa tipo un usuario, pero no se como tomar sus atributos
	 */
	
}
