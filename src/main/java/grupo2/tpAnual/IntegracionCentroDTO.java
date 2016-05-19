package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class IntegracionCentroDTO extends Integracion {
	private CentroDTO lugar;

	@Override
	public List<POI> busqueda(String txtABuscar, String servicio) {		
		return this.lugar.busqueda(txtABuscar);
	}

	public void setServicios(List<Servicio> serviciosTransformados) {
		serviciosTransformados.add((Servicio) lugar.getServiciosDTO());
	}

	public void setDireccion(Direccion direccionTransformada) {
		direccionTransformada.setCalle(this.lugar.getDomicilio());

	}

	public void setComuna(Comuna comuna) {
		List<Point> listaVertices = new ArrayList<>();
		comuna = new Comuna(this.lugar.getNumeroComuna(), listaVertices);
	}

}
