package grupo2.tpAnual;

import java.util.Random;

import javax.persistence.*;

@MappedSuperclass
public abstract class PersistentEntity {
	@org.mongodb.morphia.annotations.Id
	@Id @GeneratedValue
	private	long id;
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
	}
	
	public void generarId(){
		Random rnd = new Random();
		this.setId(rnd.nextLong());
	}
}
