package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

@Entity
public class Comuna {
	private Polygon comuna;
	private List<Point> vertices = new ArrayList<Point>();
	private int numeroComuna;

	public Comuna(int numero, List<Point> vertices) {
		this.numeroComuna = numero;
		this.comuna = new Polygon(vertices);
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
