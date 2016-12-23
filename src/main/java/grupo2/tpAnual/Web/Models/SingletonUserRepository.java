package grupo2.tpAnual.Web.Models;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.SQLUserRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;
import grupo2.tpAnual.Web.Server;

public class SingletonUserRepository  extends AbstractPersistenceTest implements WithGlobalEntityManager{
	public static UserRepository instance;
	private static EntityManager em;
	

	public static UserRepository get() {
		Usuario admin = new Usuario();
		Usuario terminal = new Usuario();
		Usuario terminal2 = new Usuario();


		admin.setEsAdmin(true);
		admin.setNombre("Administrador");

		terminal.setEsAdmin(false);
		terminal.setNombre("Terminal");
		
		terminal2.setEsAdmin(false);
		terminal2.setNombre("Terminal2");

		if (instance == null) {
			if (Server.inMemory == true){
				terminal.setId(1);
				terminal2.setId(2);				
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
