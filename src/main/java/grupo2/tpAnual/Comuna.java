package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Comuna {
	private Polygon comuna;
	private List<Point> vertices = new ArrayList<Point>();

		

	public void agregarVertice(Point vertice){
		this.vertices.add(vertice);
		this.comuna = new Polygon(vertices);
	}
		
	public Polygon getComuna(){
		return comuna;
	}


	public boolean estaAdentro(Point punto) {
		return (this.comuna.isInside(punto));
	}
	
	
}
