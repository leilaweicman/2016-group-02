package grupo2.tpAnual.OrigenesDeDatos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import grupo2.tpAnual.Pois.POI;

@Entity 
@Table(name="origenesDeDatos") 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

public abstract class OrigenesDeDatos {
	
	@Id	
	public Integer id;
	@Transient 
	public abstract List<POI> busqueda(String txtABuscar);
}
