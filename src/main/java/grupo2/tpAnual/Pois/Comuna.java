package grupo2.tpAnual.Pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import grupo2.tpAnual.ConverterPoint;

@Entity
public class Comuna {
	@Transient
	private Polygon comuna;
	@Transient
	private EntityManager em;
	//@OneToMany @Convert(converter = ConverterPoint.class) @JoinColumn
	@Transient
	private List<Point> vertices = new ArrayList<Point>();
	
	@Id
	private int numeroComuna;

	public Comuna(int numero, List<Point> vertices) {
		/*EntityManagerFactory emf =Persistence.createEntityManagerFactory("db");
		em = emf.createEntityManager();
		em.getTransaction().begin();*/
		this.numeroComuna = numero;
		this.comuna = new Polygon(vertices);
		/*em.persist(this);
		em.getTransaction().commit();*/
	}

	public void agregarVertice(Point vertice) {
		this.vertices.add(vertice);
		this.comuna = new Polygon(vertices);
	}

	public int getNumeroComuna() {
		return this.numeroComuna;
	}

	public Polygon getComuna() {
		return comuna;
	}

	public boolean estaAdentro(Point punto) {
		return (this.comuna.isInside(punto));
	}

}
