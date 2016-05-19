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

	/*public List<POI> transformarListaAPOI(List<CGP> listaCGP){
		List<POI> listaPOI = new ArrayList<>();
		return listaPOI;
	}
	
	public POI transformarCGPAPOI(List<CGP> listaCGP){
		POI POI = new ArrayList<>();
		return listaPOI;
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

	/*@Override
	public Object busqueda(String txtABuscar) {
		// NO DARLE BOLA, SINO ROMPIA, HAY Q MIRAR EL Q ESTA COMENTADO
		return true;
	}*/

}
