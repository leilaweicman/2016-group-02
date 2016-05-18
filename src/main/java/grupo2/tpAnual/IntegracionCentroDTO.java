package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class IntegracionCentroDTO extends Integracion {
	private CentroDTO lugar;

	/*	@Override
	public List<POI> busqueda(String txtABuscar) {
		List<CGP> listaCGP = new ArrayList<>();
		List<POI> listaPOI = new ArrayList<>();
		listaCGP = this.lugar.busqueda(txtABuscar);
		listaPOI= transformarListaAPOI(listaCGP);
		return listaPOI;
	}*/

/*	public List<POI> transformarListaAPOI(List<CGP> listaCGP){
		;
	}*/

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

	@Override
	public Object busqueda(String txtABuscar) {
		// NO DARLE BOLA, SINO ROMPIA, HAY Q MIRAR EL Q ESTA COMENTADO
		return null;
	}

}
