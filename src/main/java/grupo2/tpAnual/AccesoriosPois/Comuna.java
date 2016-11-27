package grupo2.tpAnual.AccesoriosPois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import grupo2.tpAnual.ConverterPoint;

@Entity
@org.mongodb.morphia.annotations.Entity
public class Comuna {
	@Transient
	@org.mongodb.morphia.annotations.Transient

	private Polygon comuna;
	@Transient
	@org.mongodb.morphia.annotations.Transient

	private EntityManager em;
	
	@org.mongodb.morphia.annotations.Transient
	@Embedded
	private List<Point> vertices = new ArrayList<Point>();
	
	@Id
	@org.mongodb.morphia.annotations.Transient
	private int numeroComuna;

	public Comuna(int numero, List<Point> vertices) {
		this.numeroComuna = numero;
		this.vertices = vertices;
		this.comuna = new Polygon(vertices);
	}

	public void agregarVertice(Point vertice) {
		this.vertices.add(vertice);	
		this.comuna = new Polygon(this.vertices);
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
