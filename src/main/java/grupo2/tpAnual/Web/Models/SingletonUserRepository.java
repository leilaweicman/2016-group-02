package grupo2.tpAnual.Web.Models;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Observers.EnviarMailBusqueda;
import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.Repositorios.ComunaRepository;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.ObserversRepository;
import grupo2.tpAnual.Repositorios.SQLUserRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;
import grupo2.tpAnual.Web.Server;

public class SingletonUserRepository  extends AbstractPersistenceTest implements WithGlobalEntityManager{
	public static UserRepository instance;
	private static EntityManager em;
	
	public static ObserversRepository repo = SingletonObserverRepository.get();
	
	public static ObserverBusqueda notificarDatos = repo.getObserverByName("notificarDatos");
	public static ObserverBusqueda enviarMail = repo.getObserverByName("enviarMail");

	public static ComunaRepository repoComunas = SingletonComunaRepository.get();
	public static Comuna comuna1 = repoComunas.getComunaByNumero(1);
	public static Comuna comuna4 = repoComunas.getComunaByNumero(4);

	public static UserRepository get() {
		
		Usuario admin = new Usuario();
		Usuario terminal = new Usuario();
		Usuario terminal2 = new Usuario();

		admin.setEsAdmin(true);
		admin.setNombre("Administrador");

		terminal.setEsAdmin(false);
		terminal.setNombre("Terminal");
		terminal.setAccionBusqueda(notificarDatos);
		terminal.setComuna(comuna1);
		
		terminal2.setEsAdmin(false);
		terminal2.setNombre("Terminal2");
		terminal2.setAccionBusqueda(enviarMail);
		terminal2.setComuna(comuna4);

		if (instance == null) {
			if (Server.inMemory == true){				
				instance = new MemoryUserRepository();
				instance.saveUser(admin);
				instance.saveUser(terminal);
				instance.saveUser(terminal2);
			}
			else{
				em = PerThreadEntityManagers.getEntityManager();
				em.getTransaction().begin();
				instance = SQLUserRepository.get();
				instance.saveUser(admin);
				instance.saveUser(terminal);
				instance.saveUser(terminal2);				
				em.getTransaction().commit();
			}
		}
		return instance;
	}

}
