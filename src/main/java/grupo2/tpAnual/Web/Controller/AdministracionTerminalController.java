package grupo2.tpAnual.Web.Controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		
		int success = Integer.valueOf(req.params("status"));

		UserRepository repo = SingletonUserRepository.get();
		List<Usuario> terminales = repo.getUsauriosTerminal();
		List<Usuario> terminalesFiltradas = new ArrayList<Usuario>();

		int comuna =-1; //si es -1 significa que son todas las comunas
		boolean seleccionada;
		
		ComunaRepository repoComunas = SingletonComunaRepository.get();
		List<Comuna> comunas = repoComunas.getComunas();
		List<Comuna> comunasAMostrar = new ArrayList<Comuna>();
		/*
		 * tengo comunas y comunasAMostrar porque si hago comunas.remove(object) me lo borra del repo
		 * entonces cada vez que filtro por una comuna distinta se me borra del repo
		 */
		
		if (req.queryParams("comuna")!=null && (Integer.valueOf(req.queryParams("comuna"))!=-1)){
			int comunaSeleccionada = Integer.valueOf(req.queryParams("comuna"));
			comuna = comunaSeleccionada;
			seleccionada = true;
			//lo hago asi porque con el repo.getUsuariosByComuna tira un null pointer exception que no entiendo
			terminales.stream().forEach(terminal -> filtrarPorComuna(terminal, comunaSeleccionada, terminalesFiltradas));
			model.put("terminales", terminalesFiltradas);
			comunasAMostrar = comunas.stream().filter(comu -> (comu.getNumeroComuna()!= comunaSeleccionada)).collect(Collectors.toList());
			//comunas.remove(repoComunas.getComunaByNumero(comunaSeleccionada));
			
		} else {
			seleccionada = false;
			model.put("terminales", terminales);
			comunasAMostrar = comunas;
		}
		
		model.put("success", success);
		model.put("comunas", comunasAMostrar);
		model.put("numeroComuna", comuna);
		model.put("seleccionada", seleccionada);
		return new ModelAndView(model, "admin/terminales/administracionTerminal.hbs");
		
	}
	
	public static void filtrarPorComuna(Usuario terminal, int numeroComuna, List<Usuario> filtradas){
		if(terminal.getComuna().getNumeroComuna()==numeroComuna){
			filtradas.add(terminal);
		}
	}
		
	public static ModelAndView crear(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		
		int error = Integer.valueOf(req.params("status"));
	
		ObserversRepository repo = SingletonObserverRepository.get();
		List<ObserverBusqueda> accionesDisponibles = repo.getObservers();
		
		ComunaRepository repoComunas = SingletonComunaRepository.get();
		List<Comuna> comunas = repoComunas.getComunas();
		
		model.put("error", error);

		model.put("comunas", comunas);
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
		
		res.redirect("/admin/terminal/1");
		return null;
	}
	
	public static ModelAndView editar(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		
		int error = Integer.valueOf(req.params("status"));
		
		long id = Long.parseLong(req.params("id"));
				
		UserRepository usuarios = SingletonUserRepository.get();
		
		Usuario user = usuarios.getUsuarioById(id);
		
		ComunaRepository repoComunas = SingletonComunaRepository.get();
		List<Comuna> comunas = repoComunas.getComunas();
		List<Comuna> comunasAMostrar = new ArrayList<Comuna>();
		int comunaUsuario = user.getComuna().getNumeroComuna();
		comunasAMostrar = comunas.stream().filter(comu -> (comu.getNumeroComuna()!= comunaUsuario)).collect(Collectors.toList());
			
		ObserversRepository repo = SingletonObserverRepository.get();
		List<ObserverBusqueda> accionesDisponibles = new ArrayList<ObserverBusqueda>();//repo.getObservers();

		List<ObserverBusqueda> acciones = repo.getObservers();
		List<ObserverBusqueda> accionesUsuario = user.getAccionesBusqueda();
		accionesDisponibles = acciones.stream().filter(action->(!user.tieneObserver(action))).collect(Collectors.toList());
		//comento la sig linea porque el removeAll lo hace al repo y no tiene sentido que haga eso
		//accionesDisponibles.removeAll(accionesUsuario);
		
		model.put("error", error);
		model.put("user", user);
		model.put("comunas", comunasAMostrar);
		model.put("accionesDisponibles", accionesDisponibles);
		model.put("accionesUsuario", accionesUsuario);
		return new ModelAndView(model, "admin/terminales/editarTerminal.hbs");
		
	}

	public static ModelAndView guardar(Request req, Response res) {
		
		UserRepository usuarios = SingletonUserRepository.get();
		Usuario user;

		if(String.valueOf(req.queryParams("id")).equals("")){
			user = new Usuario();
			if (req.queryParams("nombre").isEmpty()){
				res.redirect("/admin/terminal/agregar/1");
				return null;
			}
		} else {
			long id = Long.parseLong(req.queryParams("id"));
			user = usuarios.getUsuarioById(id);
			if (req.queryParams("nombre").isEmpty()){
				res.redirect("/admin/terminal/editar/"+String.valueOf(id)+"/1");
				return null;
			}
		}
		String nombre = req.queryParams("nombre");
		System.out.println("nom "+nombre);
		int numeroComuna = Integer.parseInt(req.queryParams("comuna"));
		
		ComunaRepository comunas = SingletonComunaRepository.get();
		Comuna comuna = comunas.getComunaByNumero(numeroComuna);
		
		ObserversRepository repoAcciones = SingletonObserverRepository.get();
		List<ObserverBusqueda> acciones = repoAcciones.getObservers();
		acciones.stream().forEach(accion-> editarAcciones(accion, user, req));
		
		user.setNombre(nombre);
		user.setComuna(comuna);
		
		usuarios.updateUsuario(user);
	
		res.redirect("/admin/terminal/1");
		return null;
	}

	public static void editarAcciones(ObserverBusqueda observer, Usuario user, Request req){
		String flag = String.valueOf(req.queryParams(String.valueOf(observer.getId()))); 

		if(user.tieneObserver(observer)&& !flag.equals("on")){
			user.quitarAccionBusqueda(observer);
		} else if(!user.tieneObserver(observer) && flag.equals("on")){
			user.setAccionBusqueda(observer);
		}
	}
		
}
